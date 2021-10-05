/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejadores;
import ventanas.VentanaRecuperacionErrores;
import static manejadores.MenejadorAnalizador.recuperacionErrores;
/**
 *
 * @author luis
 */
public class ManejadorRecuperacionErrores {
    
    public void llenarTextArea(VentanaRecuperacionErrores ventana){
        for(int x=0;x<recuperacionErrores.size();x++){
            ventana.getTextArea().append(recuperacionErrores.get(x));
            ventana.getTextArea().append("\n");
        }
    }
    
}
