/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;
import manejadores.MenejadorAnalizador;

/**
 * Esta clase me permite asignar un error a travez de sus atributos y poder manejarlos en el transcurso del programa
 * @author luis
 */
public class Errores {
    private String cadena;
    private int fila;
    private int columna;

    /**
     * Este constructor vacio me permite crear un objeto error sin necesidad de que este tenga atributos
     */
    public Errores() {
        
    }

    /**
     * Este constructor me permite crear un error a travez de los atributos de cadena, fila y columna
     * @param cadena
     * @param fila
     * @param columna
     */
    public Errores(String cadena, int fila, int columna) {
        this.cadena = cadena;
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Este metodo me devuelve la cadena de un error
     * @return
     */
    public String getCadena() {
        return cadena;
    }

    /**
     * Este metodo me permite cambiar la cadena de un texto
     * @param cadena
     */
    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    /**
     * Este metodo me devuelve la fila en la que esta ubicada un error
     * @return
     */
    public int getFila() {
        return fila;
    }

    /**
     * Este metodo me permite cambiar la fila en la que esta ubicada un error
     * @param fila
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * Este metodo me devuelve la columna a la que esta asociada un error
     * @return
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Este metodo me permite cambiar la columna a la que esta asociada un error
     * @param columna
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }    
    
    /**
     * Este metodo me permite verificar si realmente tenemos un error para poder asignarlo al array
     * @param estado
     * @param token
     * @param fila
     * @param columna
     */
    public void verificarError(String estado, String token, int fila, int columna){
        //Verificamos que realmente se trate de un error
        if(estado.equals("Error")){
            //Agregamos el error a nuestro array
            Errores error= new Errores(token, fila, columna);
            MenejadorAnalizador.errores.add(error);
        }
    }
}
