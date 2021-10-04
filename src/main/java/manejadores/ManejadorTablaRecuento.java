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
import tokens.Token;
import ventanas.VentanaTablaRecuentoLexemas;

/**
 *
 * @author luis
 */
public class ManejadorTablaRecuento {
    DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public void llenarTabla(VentanaTablaRecuentoLexemas ventana){
        JTable tabla= ventana.getTable();
        this.setModelo(tabla);
        this.setDatos(tabla);
    }
    
    public void setModelo(JTable tabla){
        String[] columna= {"Lexema","Token","Repeticiones"};
        modelo.setColumnIdentifiers(columna);
        tabla.setModel(modelo);
    }
    
    public void setDatos(JTable tabla){
        Object[] datos= new Object[modelo.getColumnCount()];
        int i=1;
        modelo.setRowCount(0);
        for(Token token: manejadores.MenejadorAnalizador.recuentoLexema){
            datos[0]= token.getLexema();
            datos[1]= token.getNombreToken();
            datos[2]= token.getCantidadVeces();
            modelo.addRow(datos);
        }
        alinear.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumnModel().getColumn(0).setCellRenderer(alinear);
        tabla.getColumnModel().getColumn(1).setCellRenderer(alinear);
        tabla.getColumnModel().getColumn(2).setCellRenderer(alinear);
        tabla.setModel(modelo);
    }

}
