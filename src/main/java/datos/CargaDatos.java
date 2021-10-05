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
import ventanas.VentanaPatrones;
import ventanas.ventanaCarga;

/**
 * Esta clase me permite poder subir el texto que sera empleado a travez de mi analizador lexico.
 * @author luis
 */
public class CargaDatos {

    /**
     * Esta ventana estatica me permite generar una venta estatica de generador de patrones para que este sea instanciada una unica vez en memoria
     */
    public static VentanaPatrones ventanaPatrones= new VentanaPatrones();

    /**
     * Esta ventana estatica me permite poder establecer tan solo una ventana analizador para que esta sea analizada en el transcurso de todo mi progroma
     */
    public static VentanaAnalizador ventana = new VentanaAnalizador();

    /**
     * Esta variable estatica me permite eestablecer cual sera mi archivo a extraer el cual sera empleado para poder guardar los cambios realizados en este
     */
    public static File archivoAProcesar;
    private ventanaCarga ventanaCarga;

    
    /**
     * Este constructor me permite hacer la carga de un archivo y poder aplicarla a la ventana deseada, en este caso a la ventana de carga de datos 
     * @param ventanaCarga
     * @param v
     */
    public CargaDatos(File archivoAProcesar, ventanaCarga ventanaCarga){
        this.archivoAProcesar = archivoAProcesar;
        this.ventanaCarga = ventanaCarga;
    }
    
    /**
     * Este metodo me permite leer el archivo de entrada ingresado por el usuairo y que este pueda ser extraido para su analisis
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
               ventanaPatrones.getArea().append(temporal);
               temporal="";
            }
            //Asignamos los saltos de linea identificados en nuestro texto de entrada
            auxiliar = lector.readLine();
            if(auxiliar!=null){
               ventanaPatrones.getArea().append("\n");
               ventana.getArea().append("\n"); 
            }        
        }       
        //Mostramos la ventana del analizador para que esta pueda ser visible
        ventanaCarga.setVisible(false);       
        ventana.setVisible(true);
        
        //Establecemos que la carga fue exitosa y cerramos el lector
        JOptionPane.showMessageDialog(null, "Datos cargados con éxito.", "CARGA DE DATOS", JOptionPane.INFORMATION_MESSAGE);
        lector.close();
    }
    
    
}
