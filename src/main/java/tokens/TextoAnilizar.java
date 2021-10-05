/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;
import java.io.Serializable;

/**
 * Esta clase me permite asignar el texto que estara asociado al texto de entrada 
 * @author luis
 */
public class TextoAnilizar  implements Serializable{
    private String textoAnalizado;

    /**
     * Este constructor me permite asociar un nuevo texto analizado que estara asociado a un texto de entrada
     * @param textoAnalizado
     */
    public TextoAnilizar(String textoAnalizado) {
        this.textoAnalizado = textoAnalizado;
    }

    /**
     * Este metodo me devuelve el texto que esta en el texto de entrada
     * @return
     */
    public String getTextoAnalizado() {
        return textoAnalizado;
    }

    /**
     * Este metodo me permite cambiar el texto asociado a un texto de entrada
     * @param textoAnalizado
     */
    public void setTextoAnalizado(String textoAnalizado) {
        this.textoAnalizado = textoAnalizado;
    }
    
    
}
