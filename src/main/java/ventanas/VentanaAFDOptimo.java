/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;
import datos.CargaDatos;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import manejadores.MenejadorAnalizador;
import tokens.NumeroLinea;

/**
 * Esta ventana me perite establecer un automata finito determinista a travez de los datos ingresados en el mismo
 * @author luis
 */
public class VentanaAFDOptimo extends javax.swing.JFrame {
    NumeroLinea numeroLinea;
    MenejadorAnalizador manejador = new MenejadorAnalizador();
    /**
     * Creates new form VentanaAnalizador
     */
    public VentanaAFDOptimo() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.jTextArea1.setEditable(false);
    }
    
    /**
     * Este metodo me devuelve el JTextArea que esta establecida en esta ventana
     * @return
     */
    public JTextArea getTextArea(){
        return this.jTextArea1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        salir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Purisa", 3, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("GENERACIÓN AFD OPTIMO");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 780, -1));

        salir.setBackground(new java.awt.Color(0, 153, 153));
        salir.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        salir.setForeground(new java.awt.Color(0, 0, 0));
        salir.setText("Regresar");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        salir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                salirKeyPressed(evt);
            }
        });
        getContentPane().add(salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 490, 170, 90));

        jScrollPane1.setBackground(new java.awt.Color(0, 153, 153));
        jScrollPane1.setForeground(new java.awt.Color(51, 51, 51));

        jTextArea1.setBackground(new java.awt.Color(0, 153, 153));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 860, 370));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoGranja.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        int response = JOptionPane.showConfirmDialog(this,"¿Quieres Regresar a la ventana Anterior?", "REGRESANDO",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (response==JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(this, "Regresando...");
            //Salimos del programa
            this.setVisible(false);
            CargaDatos.ventana.setVisible(true);
        }
    }//GEN-LAST:event_salirActionPerformed

    private void salirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salirKeyPressed

    }//GEN-LAST:event_salirKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
