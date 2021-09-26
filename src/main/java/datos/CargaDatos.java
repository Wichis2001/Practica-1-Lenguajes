/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import ventanas.VentanaAnalizador;
import ventanas.ventanaCarga;

/**
 *
 * @author luis
 */
public class CargaDatos {
    private File archivoAProcesar;
    private ventanaCarga ventanaCarga;

    
    /**
     * Este constructor me permite hacer la carga de un archivo y poder aplicarla a la ventana deseada, en este caso a la ventana de carga de datos 
     * @param archivoAProcesar
     * @param ventanaCarga
     */
    public CargaDatos(File archivoAProcesar, ventanaCarga ventanaCarga){
        this.archivoAProcesar = archivoAProcesar;
        this.ventanaCarga = ventanaCarga;
    }
    
    /**
     *
     */
    public void anilizarTexto(){
        try {
            //Llamamos al metodo leer archivo
            leerArchivo();
            //Archivo no encontrado
        } catch (FileNotFoundException e) {
            System.err.println(e);
            //Errores Generales
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.err.println(e);
            //Error en el indice ilegal que posee el arreglo 
        }
    }

    /**
     * Este metodo se encarga de la lectura del archivo de texto y la extraccion de datos para cada objeto respectivamente 
     */
    private void leerArchivo() throws FileNotFoundException, IOException, ArrayIndexOutOfBoundsException{
        //Leemos el texto del archivo
        VentanaAnalizador ventana = new VentanaAnalizador();
        BufferedReader lector = new BufferedReader(new FileReader(this.archivoAProcesar));
        //Usamos esta variable para la lectura de linea por linea
        String auxiliar = lector.readLine();
        String temporal="";
        //Usamos esta variable para determinar la poscion en la que esta ubicada la lnea
        while(auxiliar != null){
            //Con la linea leida separamos los campos
            temporal=temporal+auxiliar;
            //Asignamos los datos obtenidos en el Jtextarea
            if(temporal!=""){
               ventana.getArea().append(temporal);
               temporal="";
            }
           
            auxiliar = lector.readLine();
            if(auxiliar!=null){
               ventana.getArea().append("\n"); 
            }        
        }       
        ventanaCarga.setVisible(false);       
        ventana.setVisible(true);
        
        //Establecemos que la carga fue exitosa y cerramos el lector
        JOptionPane.showMessageDialog(null, "Datos cargados con éxito.", "CARGA DE DATOS", JOptionPane.INFORMATION_MESSAGE);
        lector.close();
    }
    
    
}
