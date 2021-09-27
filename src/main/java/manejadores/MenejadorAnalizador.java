/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejadores;
import datos.CargaDatos;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author luis
 */
public class MenejadorAnalizador {
    public void resaltarTexto(JTextArea textArea) throws BadLocationException{
        String texto = JOptionPane.showInputDialog(null, "Ingresa el patron a buscar...", "BUSQUEDA PATRON", JOptionPane.INFORMATION_MESSAGE);
        if(texto.equals("")){
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            int guiaCiclo=0;
            int posicionador=0;
            DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.blue);
            Highlighter resaltador = textArea.getHighlighter();
            resaltador.removeAllHighlights();
            char letra;
            char inicioPalabra;
            char textoArea;
            String analizar=textArea.getText(); 
            String palabra="";
            String palabraCompleta="";
            boolean encontrado=false;
            if(texto.length()>analizar.length()){
                JOptionPane.showMessageDialog(null, "Esta cadena no fue encontrada", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                for(int x=guiaCiclo; x < analizar.length(); x++){
                    inicioPalabra=texto.charAt(0);
                    textoArea= analizar.charAt(x);
                    if(Character.compare(textoArea, inicioPalabra)==0){
                        if(palabra.length()>analizar.length()-x){
                            JOptionPane.showMessageDialog(null, "Esta cadena no fue encontrada", "ERROR", JOptionPane.ERROR_MESSAGE);
                        } else {
                            int recorridoCiclo=x+texto.length();
                            posicionador=x;
                            for(int h=x;h<recorridoCiclo;h++){
                                letra= analizar.charAt(h);
                                palabraCompleta= palabraCompleta+letra;
                            }
                            if(palabraCompleta.equals(texto)){
                                resaltador.addHighlight(posicionador, x+texto.length(), highlightPainter);
                                guiaCiclo= guiaCiclo+texto.length();
                                palabraCompleta="";
                                encontrado=true;
                            } else {
                                guiaCiclo=guiaCiclo+1;
                                palabraCompleta="";
                            }
                        }
                    }
                }
                if(encontrado==false){
                    JOptionPane.showMessageDialog(null, "Esta cadena no fue encontrada", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {     
                    CargaDatos.ventanaPatrones.setVisible(true);
                    CargaDatos.ventana.setVisible(false);
                }
            }
            
        }
    }
}
