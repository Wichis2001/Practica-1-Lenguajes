/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;

import manejadores.MenejadorAnalizador;

/**
 * Esta clase me permite establecer los atributos que tendra un token para poder ser analizado
 * @author luis
 */
public class Token {
    private String nombreToken;
    private String lexema;
    private int fila;
    private int columna;
    private int cantidadVeces;

    /**
     * Este constructor me permite crear un token a travez del programa
     * @param nombreToken
     * @param lexema
     * @param fila
     * @param columna
     */
    public Token(String nombreToken, String lexema, int fila, int columna) {
        this.nombreToken = nombreToken;
        this.lexema = lexema;
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Este constructor me permite crear un nuevo recuento de lexemas a travez de los atributos
     * @param lexema
     * @param nombreToken
     * @param cantidadVeces
     */
    public Token(String lexema,String nombreToken,  int cantidadVeces) {
        this.nombreToken = nombreToken;
        this.lexema = lexema;
        this.cantidadVeces = cantidadVeces;
    }
    
    /**
     * Este constructor anonimo me permite establecer un codigo en el transcurso del programa
     */
    public Token() {
    }

    /**
     * Este metodo me devuelve la cantidad de veces que se repite un token
     * @return
     */
    public int getCantidadVeces() {
        return cantidadVeces;
    }

    /**
     * Este metodo me permite cambiar la cantidad de veces de un token
     * @param cantidadVeces
     */
    public void setCantidadVeces(int cantidadVeces) {
        this.cantidadVeces = cantidadVeces;
    }

    /**
     * Este metodo me devuelve el nombre de un token
     * @return
     */
    public String getNombreToken() {
        return nombreToken;
    }

    /**
     * Este metodo me devuelve el lexema de un token
     * @return
     */
    public String getLexema() {
        return lexema;
    }

    /** 
     * Este metodo me devuelve la fila en la cual esta ubicada un token
     * @return
     */
    public int getFila() {
        return fila;
    }

    /**
     * Este metodo me devuelve la columna a la cual esta ubicada un token
     * @return
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Este metodo me permite cambiar el nombre de un token
     * @param nombreToken
     */
    public void setNombreToken(String nombreToken) {
        this.nombreToken = nombreToken;
    }

    /**
     * Este metodo me permite cambiar un lexema
     * @param lexema
     */
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    /**
     * Este metodo me permite cambiar una fila a la cual esta asociada un token
     * @param fila
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * Este metodo me permite cambiar la columna a la cual esta asociada una columna
     * @param columna
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    /**
     * Este metodo me permite asignar un nuevo Token siempre y cuendo este no sea un error con anteriorirdad
     * @param nombreToken
     * @param lexema
     * @param fila
     * @param columna
     */
    public void verificarToken(String nombreToken, String lexema, int fila, int columna){
        //Verificamos que el token no se encuentre en el estado error
        if(nombreToken!=("Error")){
            //Creamos un token y lo guardamos en el array
            Token token = new Token(nombreToken,lexema, fila, columna);
            MenejadorAnalizador.token.add(token);
        }
    }
    
    /**
     * Este metodo me permite determinar un recuento de lexemas para ver cuantas veces se repite cada una de estas
     */
    public void recuentoLexema(){
        boolean datoEncontrado=false;
        //Creamos un nuevo Token el cual estara asociado al primer lugar del array
        Token prueba = new Token(MenejadorAnalizador.token.get(0).getLexema(),MenejadorAnalizador.token.get(0).getNombreToken(), 1);
        //Agregamos al array el recuento de lexemas
        MenejadorAnalizador.recuentoLexema.add(prueba);
        //Verificamos si la cantidad de elementos almacenados es mayor a 1
        if(MenejadorAnalizador.token.size()>1){  
            //Recorremos el array del token 
            for(int y=1;y<MenejadorAnalizador.token.size();y++){
                //Recorremos el array de recuento de lexemas
                for(int x=0;x<MenejadorAnalizador.recuentoLexema.size();x++){
                    //Verificamos que el lexema no este asignado con antelacion a nuestro array
                    if(MenejadorAnalizador.token.get(y).getLexema().equalsIgnoreCase(MenejadorAnalizador.recuentoLexema.get(x).getLexema())){
                       //Aumentamos la cantidad de veces que se asocia al lexema
                        MenejadorAnalizador.recuentoLexema.get(x).setCantidadVeces(MenejadorAnalizador.recuentoLexema.get(x).getCantidadVeces()+1);
                        datoEncontrado=true;
                    }
                }
                if(datoEncontrado==false){
                    //Creamos un nuevo token y lo asignamos al array de recuento de lexema
                    Token recuento = new Token(MenejadorAnalizador.token.get(y).getLexema(),MenejadorAnalizador.token.get(y).getNombreToken(), 1);
                    MenejadorAnalizador.recuentoLexema.add(recuento);
                    datoEncontrado=false;
                } else {
                    datoEncontrado=false;
                }
            } 
        }
            
    }
    
}
