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
 * Esta clase me permite manejar mi ventana que se encargara de analizar lexicamente mi texto ingresado, incluyendo todos los metodos para su correcto funcionamiento
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

    /**
     * Este arraylist estatico me permite almacenar correctamente una lista de los errores encontrados al recorrer mi automata
     */
    public static ArrayList<Errores> errores = new ArrayList<>();

    /**
     * Este arraylist estatico me permite almacenar correctamente una lista de los tokens encontrados al recorrer mi automata
     */
    public static ArrayList<Token> token = new ArrayList<>();

    /**
     * Este arraylist estatico me permite almacenar correctamente una lista de las veces que se repiten mis tokens encontrados al recorrer mi automata
     */
    public static ArrayList<Token> recuentoLexema = new ArrayList<>();

    /**
     * Este arraylist estatico me permite almacenar correctamente una lista de las transiciones que realiza el automamta al momento de recorrerlo
     */
    public static ArrayList<String> generacionAFD = new ArrayList<>();

    /**
     * Este arraylist estatico me permite almacenar correctamente una lista de los plausibles errores recuperados al recorrer mi automata
     */
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

    /**
     * Este metodo me permite poder pintar un area de texta escrita en un input dialog para que esta pueda ser ubicada por el usuario
     * @param textArea
     * @throws BadLocationException
     */
    public void resaltarTexto(JTextArea textArea) throws BadLocationException{
        //Ingresamos el patron a ser buscado en el JTextArea
        String texto = JOptionPane.showInputDialog(null, "Ingresa el patron a buscar...", "BUSQUEDA PATRON", JOptionPane.INFORMATION_MESSAGE);
        //Nos aseguramos que el texto no sea nulo
        if(texto.equals("")){
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            //Agregamos el texto de nuestro text area a nuestra ventana de patrones para poder tener el mismo texto a resaltar
            CargaDatos.ventanaPatrones.getArea().setText(CargaDatos.ventana.getArea().getText());
            //Establecemos nuestras posiciones de inicio
            int guiaCiclo=0;
            int posicionador=0;
            //Definimos nuestro resaltador
            DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.blue);
            Highlighter resaltador = textArea.getHighlighter();
            //Removemos el area resaltada con anterioridad
            resaltador.removeAllHighlights();
            char letra;
            char inicioPalabra;
            char textoArea;
            String analizar=textArea.getText(); 
            String palabra="";
            String palabraCompleta="";
            boolean encontrado=false;
            //Nos aseguramos que los textos tengan el mismo rango para que el texto pueda ser hallado
            if(texto.length()>analizar.length()){
                JOptionPane.showMessageDialog(null, "Esta cadena no fue encontrada", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                //Recorremos el texto a travez d e nuestra guia
                for(int x=guiaCiclo; x < analizar.length(); x++){
                    //Asignamos los valores de inicio
                    inicioPalabra=texto.charAt(0);
                    textoArea= analizar.charAt(x);
                    //Comparamos que ambas cadenas sean iguales
                    if(Character.compare(textoArea, inicioPalabra)==0){
                        //Verificamos que el largo de las cadenas sean validas para poder ser resaltadas
                        if(palabra.length()>analizar.length()-x){
                            JOptionPane.showMessageDialog(null, "Esta cadena no fue encontrada", "ERROR", JOptionPane.ERROR_MESSAGE);
                        } else {
                            //Establecemos el recorrido que tendra el ciclo
                            int recorridoCiclo=x+texto.length();
                            posicionador=x;
                            //Recorremos el ciclo hasta el largo de nuestro texto de entrada
                            for(int h=x;h<recorridoCiclo;h++){
                                //Establecemos nustra palabra completa para poder ser comparada
                                letra= analizar.charAt(h);
                                palabraCompleta= palabraCompleta+letra;
                            }
                            //Verificamos que ambas palabras sean identicas
                            if(palabraCompleta.equals(texto)){
                                //Resaltamos el area seleccionada para nuestro texto
                                resaltador.addHighlight(posicionador, x+texto.length(), highlightPainter);
                                //Establecemos el largo de nuestra guia
                                guiaCiclo= guiaCiclo+texto.length();
                                //Reiniciamos nuestras variables
                                palabraCompleta="";
                                encontrado=true;
                            } else {
                                //Seguimos recorriendo nuestro ciclo y reinicamos nuestra variable
                                guiaCiclo=guiaCiclo+1;
                                palabraCompleta="";
                            }
                        }
                    }
                }
                //Si la palabra no es encontrada mandamos un JOptionPane de que la cadena no fue encontrada
                if(encontrado==false){
                    JOptionPane.showMessageDialog(null, "Esta cadena no fue encontrada", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {    
                    //En caso de que si haya sido encontrada nuestra variable nos trasladamos a la ventana que enseña nuestro patron
                    CargaDatos.ventanaPatrones.setVisible(true);
                    CargaDatos.ventana.setVisible(false);
                }
            }  
        }
    }
    
    /**
     * Este metodo me permite analizar los tokens que estan en mi JTextArea
     * @param textArea
     */
    public void analizarTokens(JTextArea textArea){
        //Extraemos el texto obtenido por nuestro JTextArea
        palabra = textArea.getText();
        //Recorremos nuestra cadena
        while (posicion < palabra.length()) 
            //Llamamos al metodo de getToken() para poder proceder a analizarlo
            getToken();
        /*
         * for (char caracter : palabra.toCharArray()) { System.out.println(caracter); }
         */

    }

    /**
     * Este metodo me maneja la transicion de estados en la cual se conducira mi token
     * @param estadoActual
     * @param caracter
     * @return
     */
    public int getSiguienteEstado(int estadoActual, int caracter) {
        //Establecemos el estado por defecto como -1
        int resultado = -1;
        //Determinamos el valor del estado el cual esta asociado a mi matriz de transicion
        if (caracter >= 0 && caracter <= 5) {
            resultado = matrizTransicion[estadoActual][caracter];
        }
        return resultado;
    }

    /**
     * Este metodo me devuelve el valor entero del caracter a partir del char ingresado en el metodo
     * @param caracter
     * @return
     */
    public int getIntCaracter(char caracter) {
        //Establecemos el error por defecto como -1
        int resultado = -1;
        //Si el caracter es ñ procedemos a identificarlo como error, ya que esta no esta en mi lenguaje
        if ((caracter == 'ñ')||(caracter == 'Ñ')){
            resultado = -1;       
            //Nos trasladamos a uno si viniese un digito nos trasladamos a uno
        } else if (Character.isDigit(caracter)) {
            resultado = 1;
           //Si viniese una letra procedemos a trasladarnos a 0
        }  else if (Character.isLetter(caracter)){
            resultado= 0;
            //Si viniese un simbolo o separador nos trasladamos a 4
        }  else if ((caracter == '(') || (caracter == ')') || (caracter == '[') || (caracter == ']') || (caracter == '{') || (caracter == '}') || (caracter == '.') || (caracter == ',') || (caracter == ':') || (caracter == ';') || (caracter == '+') || (caracter == '-') || (caracter == '*') || (caracter == '/') || (caracter == '%')) {
            resultado = 4;
        }               
        return resultado;
    }

    /**
     * Este metodo me devuelve el estado de aceptacion al cual corresponde mi token
     * @param i
     * @return
     */
    public String getEstadoAceptacion(int i){
        //Establecemos como predeterminado el estado de error
        String res = "Error";
        int indice = 0;
        //Recorremos nuestra array de estados de finalizacion para poder determinar a que estado corresponde nuestro token
        for (int estadoAceptacion : estadosFinalizacion) {
            //Determinamos el estado de acepacion a travez del digito
            if (estadoAceptacion == i){
                //Establecemos la descripcion definida en nuestro estado de aceptacion
                res = descripcionFinalizacion[indice];
                break;
            }
            indice++;
        }
        return res;
    }
    
    /**
     * Este metodo me permite a travez de un token poder definir hacia donde nos dirigiremos a travez de los valores ingresados previamente
     */
    public void getToken() {
        estadoActual = 0;
        boolean esEspacio=false;
        boolean seguirLeyendo = true;
        char tmp;
        String token = "";
        //Determinamos un ciclo que se recorra unicamente antes que haya un salto de linea o un espacio
        while ((seguirLeyendo) && (posicion < palabra.length())) {
            //Hacemos una suma de columna para poder asociarla previamente
            columna++;
            //Determinamos que hay un ingreso de un salto de linea o un espacio
            if ((Character.isSpaceChar(tmp = palabra.charAt(posicion)))||(palabra.charAt(posicion)) == '\n') {
                //Si hay un salto de linea procedemos a hacer una suma de de filas y elliminamos una columna
                if((palabra.charAt(posicion)) == '\n'){
                    fila= fila+1;
                    columna--;
                    esEspacio=true;
                    //Si viniese un espacio procedemos a eliminar una columna agregada de mas
                } else if (Character.isSpaceChar(tmp = palabra.charAt(posicion))){
                    columna--;            
                } 
                //Determinamos un fin de ciclo
                seguirLeyendo = false;  
            } else {
                //Verificamos que no haya un espacio extra de mas
                if(palabra.charAt(posicion) == ' '){
                    esEspacio=true;
                    seguirLeyendo=false;
                }
                // para mi automata
                //Asignamos un estado para nuestro decimal si hay previametne un digito y un punto
                if(estadoActual==2&&palabra.charAt(posicion) == '.'){
                    //Nos trasladamos al estado tres
                    int estadoTemporal = 3;
                    //Lo asignamos a nuestra generacion de un automata finito determinista
                    generacionAFD.add("Estado actual " + estadoActual + " caracter "+ tmp + " transicion a "+estadoTemporal);
                    //Determinamos nuestro Token
                    token+=tmp;
                    estadoActual = estadoTemporal;
                } else {
                    //Asignamos nuestro estado temporal
                    int estadoTemporal = getSiguienteEstado(estadoActual, getIntCaracter(tmp));
                    generacionAFD.add("Estado actual " + estadoActual + " caracter "+ tmp + " transicion a "+estadoTemporal);
                    //Determinamos cual sera nuestro token
                    token+=tmp;
                    estadoActual = estadoTemporal;
                }
                //Asignamos nuestro char a la generacion de un automata determinista
                generacionAFD.add(String.valueOf(tmp));
                //Nos aseguramos que nuestra posicon aun pueda recorrer el ciclo
                if((posicion+1) < palabra.length()){
                    //Verificamos si nuestro estado de aceptacion es -1 para indiar que hay un error y podemos intentar recuperar ese error
                    if(estadoActual==-1&&palabra.charAt(posicion+1) != ' '&&palabra.charAt(posicion+1) != '\n'){
                        //Asignamos el token de error
                        tokenError+=token;
                        //Llamamos al metodo de verificar error
                        this.verificarErrores();
                        //Establecemos nuestro estados y las posiciones
                        estadoActual=-1;
                        posicion=espacioExtra-1;
                        seguirLeyendo=false;
                    } 
                } 
            }
            posicion++;
        }
        //Determinamos que no tengamos un token vacio
        if(token!=("")){
            //Guardamos nuestro token al arraylist de nuestra generacion de un automata finito determinista
            generacionAFD.add("*********Termino en el estado "+ getEstadoAceptacion(estadoActual) + " token actual : "+token);
            generacionAFD.add("\n");
        } 
        //Verificamos que adecuadamente si identifico un espacio y no un token
        if(esEspacio==true&&token.equals("")){
            //No guardamos los datos y reinicamos la variable
            filaTemporal++;
            columna=0;
            esEspacio=false;
            //Verificamos que haya venido un espacio vacio
        } else if(token.equals("")){
            //Hacemos una sumatoria de las columunas
            columna++;
        } else {
            //Nos aseguramos que las filas no sean iguales
            if(filaTemporal!=fila){
                //Asignamos un numero de columna temporal
                columnaTemporal=columna-token.length();
                //Llamamos al metodo de verificacion de errores
                error.verificarError(getEstadoAceptacion(estadoActual), token, (filaTemporal+1),columnaTemporal+1);
                //Llamamos al metodo de agregacion de tokens
                agregarToken.verificarToken(getEstadoAceptacion(estadoActual), token, (filaTemporal+1), columnaTemporal+1);
                filaTemporal++;
                columna=0;
                esEspacio=false;
            } else {
                //Verifiamos que la fila temporal sea cero
                if(filaTemporal==0){
                    //Hacemos una resta de columnas a travez del largo del token
                    columnaTemporal=columna-token.length();
                    //Llamamos al metodo de vericacion de errores
                    error.verificarError(getEstadoAceptacion(estadoActual), token, 1,columnaTemporal+1);
                    //Llamamos al metodo de veriacion de tokens
                    agregarToken.verificarToken(getEstadoAceptacion(estadoActual), token, 1, columnaTemporal+1);
                    columna++;
                } else{
                    //Em caso contracio determinamos la columna temporal a travez de la resta de la columna con el largo del token
                    columnaTemporal=columna-token.length();
                    //Llamamos al metodo de verificacion de Errores
                    error.verificarError(getEstadoAceptacion(estadoActual), token, (filaTemporal+1),columnaTemporal+1);
                    //Agregamos el token
                    agregarToken.verificarToken(getEstadoAceptacion(estadoActual), token, (filaTemporal+1), columnaTemporal+1);
                    columna++;
                }
            }
        }
        //Hacemos la sumatoria de nuestras columnas para asignar las columnas aheridas en el error
        columna=columna+columnaError;
        columnaError=0;
    }   
    
    /**
     * Este metodo me permite manejar de que manera se mestraran los botones a corde de lo enseñado
     */
    public void verificarBotones(){
        if(errores.isEmpty()){
            //Si el array de errores esta limpio procedemos a mostrar los votones de tokens y ocultamos los de errores
            CargaDatos.ventana.getReporteTokens().setVisible(true);
            CargaDatos.ventana.getRecuentoLexemas().setVisible(true);
            CargaDatos.ventana.getReporteErrores().setVisible(false);
            CargaDatos.ventana.getRecuperacionErrores().setVisible(false);
            CargaDatos.ventana.getAFD().setEnabled(true);
        } else{
            //Si el array de errores tiene valores procedemos a mostrar los votones de errores y ocultamos los de tokens
            CargaDatos.ventana.getReporteTokens().setVisible(false);
            CargaDatos.ventana.getRecuentoLexemas().setVisible(false);
            CargaDatos.ventana.getReporteErrores().setVisible(true);
            CargaDatos.ventana.getRecuperacionErrores().setVisible(true);
            CargaDatos.ventana.getAFD().setEnabled(true);
        }
    }
    
    /**
     * Este metodo me ayuda a verificar si hay errores en nuestro texto de entrada para poder verificar si existe una plausible recuperacion de errores
     */
    public void verificarErrores(){
        //Determinamos nuestro espacio extra
        espacioExtra=posicion+1;
        int estadoActual2 = 0;
        boolean esEspacio=false;
        boolean seguirLeyendo = true;
        char tmp;
        String token = "";
        //Determinamos un ciclo que se recorra unicamente antes que haya un salto de linea o un espacio
        while ((seguirLeyendo) && (espacioExtra < palabra.length())) {
            //Hacemos una sumatoria de nuestra columna de error
            columnaError++;
            //Determinamos que hay un ingreso de un salto de linea o un espacio
            if ((Character.isSpaceChar(tmp = palabra.charAt(espacioExtra)))||(palabra.charAt(espacioExtra)) == '\n') {
                if((palabra.charAt(espacioExtra)) == '\n'){
                    //Si determinamos un salto de linea procedemos a hacer una sumatoria de las filas y eliminamos una columna de error
                    fila= fila+1;
                    columnaError--;
                    esEspacio=true;
                //Determinamos que si hay un espacio vacio para poder eliminar una columna de error
                } else if (Character.isSpaceChar(tmp = palabra.charAt(espacioExtra))){
                    columnaError--;            
                }   
                seguirLeyendo = false;  
            } else {
                //Verificamos que hay un espacio vacio para poder salir de ahi
                if(palabra.charAt(espacioExtra) == ' '){
                    esEspacio=true;
                    seguirLeyendo=false;
                }
                //Asignamos un estado para nuestro decimal si hay previametne un digito y un punto
                if(estadoActual2==2&&palabra.charAt(espacioExtra) == '.'){
                    //Nos trasladamos al estado temporal 3
                    int estadoTemporal = 3;
                    //Asignamos a nuestro array la generacion de nuestro automata finito determinista
                    generacionAFD.add("Estado actual " + estadoActual2 + " caracter "+ tmp + " transicion a "+estadoTemporal);
                    token+=tmp;
                    estadoActual2 = estadoTemporal;
                } else {
                    //Determinamos un estado temporal
                    int estadoTemporal = getSiguienteEstado(estadoActual2, getIntCaracter(tmp));
                    //Asignamos a nuestro array la generacion de nuestro automata finito determinista
                    generacionAFD.add("Estado actual " + estadoActual2 + " caracter "+ tmp + " transicion a "+estadoTemporal);
                    token+=tmp;
                    estadoActual2 = estadoTemporal;
                }
                //Asignamos a nuestro array la generacion de nuestro automata finito determinista
                generacionAFD.add(String.valueOf(tmp));
                //Nos aseguramos que nuestra posicon aun pueda recorrer el ciclo
                if((espacioExtra+1) < palabra.length()){
                    //Verificamos si nuestro estado de aceptacion es -1 para indiar que hay un error y podemos intentar recuperar ese error
                    if(estadoActual2==-1&&palabra.charAt(espacioExtra+1) != ' '&&palabra.charAt(espacioExtra+1) != '\n'){
                        //Reiniciamos el estado actual y volvemos a asignar el token de eror
                        estadoActual2=0;
                        if(token!=null){
                            tokenError+=token;
                        }
                        //Reinicamos el token
                        token="";
                    } else if(estadoActual2==-1){
                        seguirLeyendo=false;
                    }    
                }
            }
            espacioExtra++;
        }
        //Nos aseguramos que el token no sea vacio
        if(token!=("")){
            //Asignamos al estado finito determinasta el estado asignado
            generacionAFD.add("*********Termino en el estado "+ getEstadoAceptacion(estadoActual2) + " token actual : "+token);
            String eliminadorNull="";
            //Si en caso viniece un valor nulo procedemos a liminarlo de nuestra estado de aceptacion
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
            //Asignamos en nuestro array de recuperacion de errores el estado que se obtuvo a travez de su analisis
            recuperacionErrores.add(tokenCulminado+" <- Error, en "+eliminadorNull+", pero detecta token "+getEstadoAceptacion(estadoActual2)+" = "+token);
            tokenError="";
            eliminadorNull="";
            generacionAFD.add("\n");
            recuperacionErrores.add("\n");
        }  
    }   
}