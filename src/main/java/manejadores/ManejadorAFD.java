/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejadores;
import ventanas.VentanaAFDOptimo;
import static manejadores.MenejadorAnalizador.generacionAFD;
/**
 *
 * @author luis
 */
public class ManejadorAFD {
    
    public void llenarTextArea(VentanaAFDOptimo ventana){
        for(int x=0;x<generacionAFD.size();x++){
            ventana.getTextArea().append(generacionAFD.get(x));
            ventana.getTextArea().append("\n");
        }
    }
    
}
