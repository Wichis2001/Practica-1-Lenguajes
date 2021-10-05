/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejadores;
import ventanas.VentanaRecuperacionErrores;
import static manejadores.MenejadorAnalizador.recuperacionErrores;
/**
 *Esta clase me permite manejar mi ventana de recuperacion de errores, incluyendo todos los metodos para su correcto funcionamiento
 * @author luis
 */
public class ManejadorRecuperacionErrores {
    
    /**
     * Este metodo me permite llenar un TextArea de una ventana de Recuperacion de Errores
     * @param ventana
     */
    public void llenarTextArea(VentanaRecuperacionErrores ventana){
        //Recorremos el array de recuperacion de errores
        for(int x=0;x<recuperacionErrores.size();x++){
            //Asignamos el texto obtenido de nuestra recuperacion de errores
            ventana.getTextArea().append(recuperacionErrores.get(x));
            ventana.getTextArea().append("\n");
        }
    }
    
}
