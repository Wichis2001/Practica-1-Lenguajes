/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejadores;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import tokens.Errores;
import ventanas.VentanaTablaErrores;

/**
 * Esta clase me permite manejar mi ventana de tabla de errores, incluyendo todos los metodos para su correcto funcionamiento
 * @author luis
 */
public class ManejadorTablaError {
    //Establecemos los modelos que tendra la JTable que esta asociada a la ventana
    DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
    DefaultTableModel modelo = new DefaultTableModel();
    
    /**
     * Metodo que me permite llenar una tabla asignandole un modelo y los datos
     * @param ventana
     */
    public void llenarTabla(VentanaTablaErrores ventana){
        JTable tabla= ventana.getTable();
        this.setModelo(tabla);
        this.setDatos(tabla);
    }
    
    /**
     * Este metodo me permite modificar el modelo establecido en un JTable
     * @param tabla
     */
    public void setModelo(JTable tabla){
        //Establecemoos las columnas que tendran la tabla
        String[] columna= {"Nombre Token","Fila","Columna"};
        //Asociamos  el modelo a la tabla
        modelo.setColumnIdentifiers(columna);
        tabla.setModel(modelo);
    }
    
    /**
     * Este metodo me permite modificar los datos de una tabla a travez de los datos asignados a mi tabla
     * @param tabla
     */
    public void setDatos(JTable tabla){
        //Establecemos un objeto el cual contendra mis datos
        Object[] datos= new Object[modelo.getColumnCount()];
        int i=1;
        modelo.setRowCount(0);
        //Recorremos el array de errores para extraer sus atributos
        for(Errores error: manejadores.MenejadorAnalizador.errores){
            //Asignamos los datos a travez de los atributos de los datos
            datos[0]= error.getCadena();
            datos[1]= error.getFila();
            datos[2]= error.getColumna();
            //Asignamos las filas
            modelo.addRow(datos);
        }
        //Centramos los atributos obtenidos por la tabla
        alinear.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumnModel().getColumn(0).setCellRenderer(alinear);
        tabla.getColumnModel().getColumn(1).setCellRenderer(alinear);
        tabla.getColumnModel().getColumn(2).setCellRenderer(alinear);
        tabla.setModel(modelo);
    }

}
