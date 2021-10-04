/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;

import manejadores.MenejadorAnalizador;

/**
 *
 * @author luis
 */
public class Token {
    private String nombreToken;
    private String lexema;
    private int fila;
    private int columna;
    private int cantidadVeces;

    public Token(String nombreToken, String lexema, int fila, int columna) {
        this.nombreToken = nombreToken;
        this.lexema = lexema;
        this.fila = fila;
        this.columna = columna;
    }

    public Token(String lexema,String nombreToken,  int cantidadVeces) {
        this.nombreToken = nombreToken;
        this.lexema = lexema;
        this.cantidadVeces = cantidadVeces;
    }
    

    public Token() {
    }

    public int getCantidadVeces() {
        return cantidadVeces;
    }

    public void setCantidadVeces(int cantidadVeces) {
        this.cantidadVeces = cantidadVeces;
    }

    public String getNombreToken() {
        return nombreToken;
    }

    public String getLexema() {
        return lexema;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setNombreToken(String nombreToken) {
        this.nombreToken = nombreToken;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    public void verificarToken(String nombreToken, String lexema, int fila, int columna){
        if(nombreToken!=("Error")){
            Token token = new Token(nombreToken,lexema, fila, columna);
            MenejadorAnalizador.token.add(token);
        }
    }
    
    public void recuentoLexema(){
        boolean datoEncontrado=false;
        Token prueba = new Token(MenejadorAnalizador.token.get(0).getLexema(),MenejadorAnalizador.token.get(0).getNombreToken(), 1);
        MenejadorAnalizador.recuentoLexema.add(prueba);
        if(MenejadorAnalizador.token.size()>1){  
            for(int y=1;y<MenejadorAnalizador.token.size();y++){
                for(int x=0;x<MenejadorAnalizador.recuentoLexema.size();x++){
                    if(MenejadorAnalizador.token.get(y).getLexema().equalsIgnoreCase(MenejadorAnalizador.recuentoLexema.get(x).getLexema())){
                        MenejadorAnalizador.recuentoLexema.get(x).setCantidadVeces(MenejadorAnalizador.recuentoLexema.get(x).getCantidadVeces()+1);
                        datoEncontrado=true;
                    }
                }
                if(datoEncontrado==false){
                    Token recuento = new Token(MenejadorAnalizador.token.get(y).getLexema(),MenejadorAnalizador.token.get(y).getNombreToken(), 1);
                    MenejadorAnalizador.recuentoLexema.add(recuento);
                    datoEncontrado=false;
                } else {
                    datoEncontrado=false;
                }
            } 
        }
        
        
        
        System.err.print(MenejadorAnalizador.recuentoLexema.size());
        System.err.print("exuti");
            
    }
    
}
