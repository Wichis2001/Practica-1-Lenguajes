/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejadores;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author luis
 */
public class MenejadorAnalizador {
    public void resaltarTexto(JTextArea textArea){
        String texto = JOptionPane.showInputDialog(null, "Ingresa el patron a buscar...", "BUSQUEDA PATRON", JOptionPane.INFORMATION_MESSAGE);
        if(texto.equals("")){
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
