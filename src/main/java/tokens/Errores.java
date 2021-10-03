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
public class Errores {
    private String cadena;
    private int fila;
    private int columna;

    public Errores() {
        
    }

    public Errores(String cadena, int fila, int columna) {
        this.cadena = cadena;
        this.fila = fila;
        this.columna = columna;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }    
    
    public void verificarError(String estado, String token, int fila, int columna){
        if(estado.equals("Error")){
            Errores error= new Errores(token, fila, columna);
            MenejadorAnalizador.errores.add(error);
        }
    }
}
