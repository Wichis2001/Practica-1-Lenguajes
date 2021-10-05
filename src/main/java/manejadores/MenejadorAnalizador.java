/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejadores;
import datos.CargaDatos;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import jdk.internal.joptsimple.internal.Strings;
import tokens.Errores;
import tokens.Token;


/**
 *
 * @author luis
 */
public class MenejadorAnalizador {
        String tokenError;
        int columnaError=0;
        int columnaTemporal=0;
        Errores error = new Errores();
        Token agregarToken = new Token();
        int filaTemporal=0;
        int espacioExtra;
        public static ArrayList<Errores> errores = new ArrayList<>();
        public static ArrayList<Token> token = new ArrayList<>();
        public static ArrayList<Token> recuentoLexema = new ArrayList<>();
        public static ArrayList<String> generacionAFD = new ArrayList<>();
        public static ArrayList<String> recuperacionErrores = new ArrayList<>();
        String palabra;
        int posicion = 0;
        int estadosFinalizacion[] = new int[4];
        String descripcionFinalizacion[] = new String[4];
        int estadoActual = 0;
        int fila=0;
        int columna=0;
        // filas s0 --> 0, s1 -> 1, s2 -> 2, , s3 -> 3, s4 -> 4, s5 -> 5
        // \Letra --> 0
        // \Digito --> 1
        // \punto --> 2
        // \agrupacion --> 3
        // \puntuacion --> 4
        // \aritmetico --> 5
        // error --> -1
        // no pertenece a mi alfabeto -1
        int matrizTransicion[][] = new int[6][6]; 
        {
            matrizTransicion[0][0]= 1; //Traslado al estado 1 ya que viene una letra 
            matrizTransicion[0][1]= 2; //Traslado al estado 2 ya que viene un digito
            matrizTransicion[0][2]= -1; //Error ya que nuestro token no puede comenzar con -1
            matrizTransicion[0][3]= 4; //Traslado al estado 4 ya que viene un signo de agrupación
            matrizTransicion[0][4]= 4; //Traslado al estado 4 ya que viene un signo de puntuacion
            matrizTransicion[0][5]= 4; //Traslado al estado 4 ya que viene un operador aritmetico
                       
            matrizTransicion[1][0]= 1; //Permenecemos en el estado 1 ya que hay una letra para poder construir un identificador 
            matrizTransicion[1][1]= 1; //Permenecemos en el estado 1 ya que hay una letra para poder construir un identificador 
            matrizTransicion[1][2]= -1; //Error ya que dentro de un identificador no deberia de haber un punto
            matrizTransicion[1][3]= -1; //Error ya que dentro de un identificador no deberia de haber un signo de agrupacion
            matrizTransicion[1][4]= -1; //Error ya que dentro de un identificador no deberia de haber un signo de puntuacion
            matrizTransicion[1][5]= -1; //Error ya que dentro de un identificador no deberia de haber un signo de agrupacion
            
            matrizTransicion[2][0]= -1; //Error, ya que se esperaba poder culminar el digito o un punto
            matrizTransicion[2][1]= 2; //Permanecemos en el estado dos, ya que hay nuevamente un digito
            matrizTransicion[2][2]= 3; //Nos dirigimos al estado tres, ya que hay un signo de puntuacion para poder construir un decimal
            matrizTransicion[2][3]= -1; //Error, ya que no se esperaba un signo de agrupacion
            matrizTransicion[2][4]= -1; //Error, ya que no se esperaba un signo de puntuación
            matrizTransicion[2][5]= -1; //Error, ya que no se esperaba un operador aritmetico
            
            matrizTransicion[3][0]= -1; //Error, ya que no se esperaba una letra
            matrizTransicion[3][1]= 5; //Nos dirigmos al estado 5, ya que corroboramos que pudimos culminar nuestro número decimal
            matrizTransicion[3][2]= -1; //Error, ya que no esperaba un punto
            matrizTransicion[3][3]= -1; //Error, ya que no se esperaba un signo de agrupacion
            matrizTransicion[3][4]= -1; //Error, ya que no se esperaba un signo de puntuación
            matrizTransicion[3][5]= -1; //Error, ya que no se esperaba un operador aritmetico
            
            matrizTransicion[4][0]= -1; //Error, ya que no se esperaba una letra
            matrizTransicion[4][1]= -1; //Error, ya que no esperaba un digito
            matrizTransicion[4][2]= -1; //Error, ya que no esperaba un punto
            matrizTransicion[4][3]= -1; //Error, ya que no se esperaba un signo de agrupacion
            matrizTransicion[4][4]= -1; //Error, ya que no se esperaba un signo de puntuación
            matrizTransicion[4][5]= -1; //Error, ya que no se esperaba un operador aritmetico
            
            matrizTransicion[5][0]= -1; //Error, ya que no se esperaba una letra
            matrizTransicion[5][1]= 5;  //Permanecemos en el estado 5, ya que tenemos nuevamente un digito
            matrizTransicion[5][2]= -1; //Error, ya que no esperaba un punto
            matrizTransicion[5][3]= -1; //Error, ya que no se esperaba un signo de agrupacion
            matrizTransicion[5][4]= -1; //Error, ya que no se esperaba un signo de puntuación
            matrizTransicion[5][5]= -1; //Error, ya que no se esperaba un operador aritmetico
            
            //Tenemos un identificador
            estadosFinalizacion[0]=1;
            descripcionFinalizacion[0]="Identificador";
            //Tenemos un digito
            estadosFinalizacion[1]=2;
            descripcionFinalizacion[1]="Digito";
            //Tenemos un decimal
            estadosFinalizacion[2]=5;
            descripcionFinalizacion[2]="Decimal";
            //Tenemos un simbolo
            estadosFinalizacion[3]=4;
            descripcionFinalizacion[3]="Simbolo";
        }   

            
    public void resaltarTexto(JTextArea textArea) throws BadLocationException{
        String texto = JOptionPane.showInputDialog(null, "Ingresa el patron a buscar...", "BUSQUEDA PATRON", JOptionPane.INFORMATION_MESSAGE);
        if(texto.equals("")){
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            CargaDatos.ventanaPatrones.getArea().setText(CargaDatos.ventana.getArea().getText());
            int guiaCiclo=0;
            int posicionador=0;
            DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.blue);
            Highlighter resaltador = textArea.getHighlighter();
            resaltador.removeAllHighlights();
            char letra;
            char inicioPalabra;
            char textoArea;
            String analizar=textArea.getText(); 
            String palabra="";
            String palabraCompleta="";
            boolean encontrado=false;
            if(texto.length()>analizar.length()){
                JOptionPane.showMessageDialog(null, "Esta cadena no fue encontrada", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                for(int x=guiaCiclo; x < analizar.length(); x++){
                    inicioPalabra=texto.charAt(0);
                    textoArea= analizar.charAt(x);
                    if(Character.compare(textoArea, inicioPalabra)==0){
                        if(palabra.length()>analizar.length()-x){
                            JOptionPane.showMessageDialog(null, "Esta cadena no fue encontrada", "ERROR", JOptionPane.ERROR_MESSAGE);
                        } else {
                            int recorridoCiclo=x+texto.length();
                            posicionador=x;
                            for(int h=x;h<recorridoCiclo;h++){
                                letra= analizar.charAt(h);
                                palabraCompleta= palabraCompleta+letra;
                            }
                            if(palabraCompleta.equals(texto)){
                                resaltador.addHighlight(posicionador, x+texto.length(), highlightPainter);
                                guiaCiclo= guiaCiclo+texto.length();
                                palabraCompleta="";
                                encontrado=true;
                            } else {
                                guiaCiclo=guiaCiclo+1;
                                palabraCompleta="";
                            }
                        }
                    }
                }
                if(encontrado==false){
                    JOptionPane.showMessageDialog(null, "Esta cadena no fue encontrada", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {     
                    CargaDatos.ventanaPatrones.setVisible(true);
                    CargaDatos.ventana.setVisible(false);
                }
            }  
        }
    }
    
    public void analizarTokens(JTextArea textArea){
        palabra = textArea.getText();
        while (posicion < palabra.length()) 
            getToken();
        /*
         * for (char caracter : palabra.toCharArray()) { System.out.println(caracter); }
         */

    }

     public int getSiguienteEstado(int estadoActual, int caracter) {
        int resultado = -1;
        if (caracter >= 0 && caracter <= 5) {
            resultado = matrizTransicion[estadoActual][caracter];
        }
        return resultado;
    }


    //alfabeto
    public int getIntCaracter(char caracter) {
        int resultado = -1;
        if ((caracter == 'ñ')||(caracter == 'Ñ')){
            resultado = -1;       
        } else if (Character.isDigit(caracter)) {
            resultado = 1;
           
        }  else if (Character.isLetter(caracter)){
            resultado= 0;
        }  else if ((caracter == '(') || (caracter == ')') || (caracter == '[') || (caracter == ']') || (caracter == '{') || (caracter == '}') || (caracter == '.') || (caracter == ',') || (caracter == ':') || (caracter == ';') || (caracter == '+') || (caracter == '-') || (caracter == '*') || (caracter == '/') || (caracter == '%')) {
            resultado = 4;
        } 
               
        return resultado;
    }

    public String getEstadoAceptacion(int i){
        String res = "Error";
        int indice = 0;
        for (int estadoAceptacion : estadosFinalizacion) {
            
            if (estadoAceptacion == i){
                res = descripcionFinalizacion[indice];
                break;
            }
            indice++;
        }

        return res;
    }
    
    public void getToken() {
        estadoActual = 0;
        boolean esEspacio=false;
        boolean seguirLeyendo = true;
        char tmp;
        String token = "";

        while ((seguirLeyendo) && (posicion < palabra.length())) {
            columna++;
            if ((Character.isSpaceChar(tmp = palabra.charAt(posicion)))||(palabra.charAt(posicion)) == '\n') {
                if((palabra.charAt(posicion)) == '\n'){
                    fila= fila+1;
                    columna--;
                    esEspacio=true;
                } else if (Character.isSpaceChar(tmp = palabra.charAt(posicion))){
                    columna--;            
                } 
                        
                seguirLeyendo = false;  
            } else {
                
                
                if(palabra.charAt(posicion) == ' '){
                    esEspacio=true;
                    seguirLeyendo=false;
                }
                // para mi automata
                if(estadoActual==2&&palabra.charAt(posicion) == '.'){
                    int estadoTemporal = 3;
                    generacionAFD.add("Estado actual " + estadoActual + " caracter "+ tmp + " transicion a "+estadoTemporal);
                    token+=tmp;
                    estadoActual = estadoTemporal;
                } else {
                    int estadoTemporal = getSiguienteEstado(estadoActual, getIntCaracter(tmp));
                    generacionAFD.add("Estado actual " + estadoActual + " caracter "+ tmp + " transicion a "+estadoTemporal);
                    token+=tmp;
                    estadoActual = estadoTemporal;
                }
                generacionAFD.add(String.valueOf(tmp));
                if((posicion+1) < palabra.length()){
                    if(estadoActual==-1&&palabra.charAt(posicion+1) != ' '&&palabra.charAt(posicion+1) != '\n'){
                        tokenError+=token;
                        this.verificarErrores();
                        estadoActual=-1;
                        posicion=espacioExtra-1;
                        seguirLeyendo=false;
                    } 
                } 
            }
            
            posicion++;
        }
        if(token!=("")){
            generacionAFD.add("*********Termino en el estado "+ getEstadoAceptacion(estadoActual) + " token actual : "+token);
            generacionAFD.add("\n");
        } 
        
        if(esEspacio==true&&token.equals("")){
            //No guardamos los datos y reinicamos la variable
            filaTemporal++;
            columna=0;
            esEspacio=false;
        } else if(token.equals("")){
            columna++;
        } else {
            if(filaTemporal!=fila){
                columnaTemporal=columna-token.length();
                error.verificarError(getEstadoAceptacion(estadoActual), token, (filaTemporal+1),columnaTemporal+1);
                agregarToken.verificarToken(getEstadoAceptacion(estadoActual), token, (filaTemporal+1), columnaTemporal+1);
                filaTemporal++;
                columna=0;
                esEspacio=false;
            } else {
                if(filaTemporal==0){
                    columnaTemporal=columna-token.length();
                    error.verificarError(getEstadoAceptacion(estadoActual), token, 1,columnaTemporal+1);
                    agregarToken.verificarToken(getEstadoAceptacion(estadoActual), token, 1, columnaTemporal+1);
                    columna++;
                } else{
                    columnaTemporal=columna-token.length();
                    error.verificarError(getEstadoAceptacion(estadoActual), token, (filaTemporal+1),columnaTemporal+1);
                    agregarToken.verificarToken(getEstadoAceptacion(estadoActual), token, (filaTemporal+1), columnaTemporal+1);
                    columna++;
                }
            }
        }
        columna=columna+columnaError;
        columnaError=0;
    }   
    
    public void verificarBotones(){
        if(errores.isEmpty()){
            CargaDatos.ventana.getReporteTokens().setVisible(true);
            CargaDatos.ventana.getRecuentoLexemas().setVisible(true);
            CargaDatos.ventana.getReporteErrores().setVisible(false);
            CargaDatos.ventana.getRecuperacionErrores().setVisible(false);
            CargaDatos.ventana.getAFD().setEnabled(true);
        } else{
            CargaDatos.ventana.getReporteTokens().setVisible(false);
            CargaDatos.ventana.getRecuentoLexemas().setVisible(false);
            CargaDatos.ventana.getReporteErrores().setVisible(true);
            CargaDatos.ventana.getRecuperacionErrores().setVisible(true);
            CargaDatos.ventana.getAFD().setEnabled(true);
        }
    }
    
    public void verificarErrores(){
        espacioExtra=posicion+1;
        int estadoActual2 = 0;
        boolean esEspacio=false;
        boolean seguirLeyendo = true;
        char tmp;
        String token = "";

        while ((seguirLeyendo) && (espacioExtra < palabra.length())) {
            columnaError++;
            if ((Character.isSpaceChar(tmp = palabra.charAt(espacioExtra)))||(palabra.charAt(espacioExtra)) == '\n') {
                if((palabra.charAt(espacioExtra)) == '\n'){
                    fila= fila+1;
                    columnaError--;
                    esEspacio=true;
                } else if (Character.isSpaceChar(tmp = palabra.charAt(espacioExtra))){
                    columnaError--;            
                }   
                seguirLeyendo = false;  
            } else {
                if(palabra.charAt(espacioExtra) == ' '){
                    esEspacio=true;
                    seguirLeyendo=false;
                }
                // para mi automata
                if(estadoActual2==2&&palabra.charAt(espacioExtra) == '.'){
                    int estadoTemporal = 3;
                    generacionAFD.add("Estado actual " + estadoActual2 + " caracter "+ tmp + " transicion a "+estadoTemporal);
                    token+=tmp;
                    estadoActual2 = estadoTemporal;
                } else {
                    int estadoTemporal = getSiguienteEstado(estadoActual2, getIntCaracter(tmp));
                    generacionAFD.add("Estado actual " + estadoActual2 + " caracter "+ tmp + " transicion a "+estadoTemporal);
                    token+=tmp;
                    estadoActual2 = estadoTemporal;
                }
                generacionAFD.add(String.valueOf(tmp));
                if((espacioExtra+1) < palabra.length()){
                    if(estadoActual2==-1&&palabra.charAt(espacioExtra+1) != ' '&&palabra.charAt(espacioExtra+1) != '\n'){
                        estadoActual2=0;
                        if(token!=null){
                            tokenError+=token;
                        }
                        token="";
                    } else if(estadoActual2==-1){
                        seguirLeyendo=false;
                    }    
                }
            }
            espacioExtra++;
        }
        if(token!=("")){
            generacionAFD.add("*********Termino en el estado "+ getEstadoAceptacion(estadoActual2) + " token actual : "+token);
            String eliminadorNull="";
            if(tokenError.length() >  4){
                for (int i = 0; i < 4; i++) {
                    eliminadorNull=eliminadorNull+tokenError.charAt(i);
                }
                if(eliminadorNull.equals("null")){
                    eliminadorNull="";
                    for (int i = 4; i < tokenError.length(); i++) {
                        eliminadorNull=eliminadorNull+tokenError.charAt(i);
                    }
                    
                } else {
                    eliminadorNull="";
                    eliminadorNull=tokenError;
                }  
            } else{
                eliminadorNull="";
                eliminadorNull=tokenError;
            }
            String tokenCulminado= eliminadorNull+token;          
            recuperacionErrores.add(tokenCulminado+" <- Error, en "+eliminadorNull+", pero detecta token "+getEstadoAceptacion(estadoActual2)+" = "+token);
            tokenError="";
            eliminadorNull="";
            generacionAFD.add("\n");
            recuperacionErrores.add("\n");
        }  
    }   
}