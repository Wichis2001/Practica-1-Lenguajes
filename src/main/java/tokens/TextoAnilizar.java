/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;
import java.io.Serializable;

/**
 *
 * @author luis
 */
public class TextoAnilizar  implements Serializable{
    private String textoAnalizado;

    public TextoAnilizar(String textoAnalizado) {
        this.textoAnalizado = textoAnalizado;
    }

    public String getTextoAnalizado() {
        return textoAnalizado;
    }

    public void setTextoAnalizado(String textoAnalizado) {
        this.textoAnalizado = textoAnalizado;
    }
    
    
}
