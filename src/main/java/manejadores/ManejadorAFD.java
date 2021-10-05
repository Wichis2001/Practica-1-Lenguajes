/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejadores;
import ventanas.VentanaAFDOptimo;
import static manejadores.MenejadorAnalizador.generacionAFD;
/**
 * Esta clase me permite manejar mi ventana de generacion de automata finito determinasta, incluyendo todos los metodos para su correcto funcionamiento
 * @author luis
 */
public class ManejadorAFD {
    
    /**
     * Este metodo me permite llenar el JTextArea de mi ventana para poder tener las transiciones hechas en mi automata finito determinista
     * @param ventana
     */
    public void llenarTextArea(VentanaAFDOptimo ventana){
        //Recorrremos el arraylist que contiene los movimientos realizados por mi automata determinista
        for(int x=0;x<generacionAFD.size();x++){
            //Agregamos los datos del arraylist al JTextArea
            ventana.getTextArea().append(generacionAFD.get(x));
            ventana.getTextArea().append("\n");
        }
    }
    
}
