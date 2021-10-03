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
 *
 * @author luis
 */
public class ManejadorTablaError {
    DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public void llenarTabla(VentanaTablaErrores ventana){
        JTable tabla= ventana.getTable();
        this.setModelo(tabla);
        this.setDatos(tabla);
    }
    
    public void setModelo(JTable tabla){
        String[] columna= {"Nombre Token","Fila","Columna"};
        modelo.setColumnIdentifiers(columna);
        tabla.setModel(modelo);
    }
    
    public void setDatos(JTable tabla){
        Object[] datos= new Object[modelo.getColumnCount()];
        int i=1;
        modelo.setRowCount(0);
        for(Errores error: manejadores.MenejadorAnalizador.errores){
            datos[0]= error.getCadena();
            datos[1]= error.getFila();
            datos[2]= error.getColumna();
            modelo.addRow(datos);
        }
        alinear.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumnModel().getColumn(0).setCellRenderer(alinear);
        tabla.getColumnModel().getColumn(1).setCellRenderer(alinear);
        tabla.getColumnModel().getColumn(2).setCellRenderer(alinear);
        tabla.setModel(modelo);
    }

}
