/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;
import datos.CargaDatos;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import manejadores.MenejadorAnalizador;
import tokens.NumeroLinea;

/**
 *
 * @author luis
 */
public class VentanaAnalizador extends javax.swing.JFrame {
    NumeroLinea numeroLinea;
    MenejadorAnalizador manejador = new MenejadorAnalizador();
    /**
     * Creates new form VentanaAnalizador
     */
    public VentanaAnalizador() {
        initComponents();
        this.setLocationRelativeTo(null);
        numeroLinea=new NumeroLinea(jTextArea1);
        jScrollPane1.setRowHeaderView(numeroLinea);
        buscarPatrones.requestFocus();
        reporteErrores.setEnabled(false);
        reporteToken.setEnabled(false);
    }
    
    public JTextArea getArea(){
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        guardarCambios = new javax.swing.JButton();
        reporteErrores = new javax.swing.JButton();
        buscarPatrones = new javax.swing.JButton();
        analizarToken = new javax.swing.JButton();
        reporteToken = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Purisa", 3, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ANALIZADOR LEXICO");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 660, -1));

        jScrollPane1.setBackground(new java.awt.Color(0, 153, 153));
        jScrollPane1.setForeground(new java.awt.Color(51, 51, 51));

        jTextArea1.setBackground(new java.awt.Color(0, 153, 153));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 850, 370));

        guardarCambios.setBackground(new java.awt.Color(0, 153, 153));
        guardarCambios.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        guardarCambios.setForeground(new java.awt.Color(0, 0, 0));
        guardarCambios.setText("Guardar Cambios");
        guardarCambios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guardarCambiosKeyPressed(evt);
            }
        });
        getContentPane().add(guardarCambios, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 600, 180, 90));

        reporteErrores.setBackground(new java.awt.Color(0, 153, 153));
        reporteErrores.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        reporteErrores.setForeground(new java.awt.Color(0, 0, 0));
        reporteErrores.setText("Reporte Errores");
        reporteErrores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                reporteErroresKeyPressed(evt);
            }
        });
        getContentPane().add(reporteErrores, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 170, 90));

        buscarPatrones.setBackground(new java.awt.Color(0, 153, 153));
        buscarPatrones.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        buscarPatrones.setForeground(new java.awt.Color(0, 0, 0));
        buscarPatrones.setText("Buscar Patrones");
        buscarPatrones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarPatronesActionPerformed(evt);
            }
        });
        buscarPatrones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscarPatronesKeyPressed(evt);
            }
        });
        getContentPane().add(buscarPatrones, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 170, 90));

        analizarToken.setBackground(new java.awt.Color(0, 153, 153));
        analizarToken.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        analizarToken.setForeground(new java.awt.Color(0, 0, 0));
        analizarToken.setText("Analizar Tokens");
        analizarToken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizarTokenActionPerformed(evt);
            }
        });
        analizarToken.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                analizarTokenKeyPressed(evt);
            }
        });
        getContentPane().add(analizarToken, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 170, 90));

        reporteToken.setBackground(new java.awt.Color(0, 153, 153));
        reporteToken.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        reporteToken.setForeground(new java.awt.Color(0, 0, 0));
        reporteToken.setText("Reporte Tokens");
        reporteToken.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                reporteTokenKeyPressed(evt);
            }
        });
        getContentPane().add(reporteToken, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 170, 90));

        salir.setBackground(new java.awt.Color(0, 153, 153));
        salir.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        salir.setForeground(new java.awt.Color(0, 0, 0));
        salir.setText("SALIR");
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
        getContentPane().add(salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 600, 170, 90));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoGranja.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarPatronesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarPatronesActionPerformed
        try {
            manejador.resaltarTexto(CargaDatos.ventanaPatrones.getArea());
        } catch (BadLocationException ex) {
            Logger.getLogger(VentanaAnalizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buscarPatronesActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        int response = JOptionPane.showConfirmDialog(this,"¿Quieres salir del Programa?", "SALIR",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (response==JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(this, "Saliendo...");
            //Salimos del programa
            System.exit(0);
        }
    }//GEN-LAST:event_salirActionPerformed

    private void buscarPatronesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarPatronesKeyPressed
        //Verificamos que si al momento de presionar la flecha derecha para poder desplazarnos al boton de analizar tokens
        if (evt.getKeyCode()==KeyEvent.VK_RIGHT){
             analizarToken.requestFocus();
             //Verificamos que si al momento de presionar la flecha abajo para poder desplazarnos al boton de salir
         } else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
             salir.requestFocus();
         }
    }//GEN-LAST:event_buscarPatronesKeyPressed

    private void analizarTokenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_analizarTokenKeyPressed
        //Verificamos que si al momento de presionar la flecha derecha para poder desplazarnos al boton de reporte de tokens
        if (evt.getKeyCode()==KeyEvent.VK_RIGHT){
            reporteToken.requestFocus();
            //Verificamos que si al momento de presionar la flecha abajo para poder desplazarnos al boton de salir
         } else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            salir.requestFocus();
            //Verificamos que si al momento de presionar la flecha izquierda para poder desplazarnos al boton de buscarPatrones
         } else if(evt.getKeyCode()==KeyEvent.VK_LEFT){
             buscarPatrones.requestFocus();
         }
    }//GEN-LAST:event_analizarTokenKeyPressed

    private void reporteTokenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reporteTokenKeyPressed
        //Verificamos que si al momento de presionar la flecha derecha para poder desplazarnos al boton de reporte de errores
        if (evt.getKeyCode()==KeyEvent.VK_RIGHT){
            reporteErrores.requestFocus();
            //Verificamos que si al momento de presionar la flecha abajo para poder desplazarnos al boton de salir
         } else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            salir.requestFocus();
            //Verificamos que si al momento de presionar la flecha izquierda para poder desplazarnos al boton de analizar tokens
         } else if(evt.getKeyCode()==KeyEvent.VK_LEFT){
             analizarToken.requestFocus();
         }
    }//GEN-LAST:event_reporteTokenKeyPressed

    private void reporteErroresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reporteErroresKeyPressed
         //Verificamos que si al momento de presionar la flecha hacia abajo para poder desplazarnos al boton de reporte de SALIR
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            salir.requestFocus();
            //Verificamos que si al momento de presionar la flecha izquierda para poder desplazarnos al boton de reporte de tokens
         } else if(evt.getKeyCode()==KeyEvent.VK_LEFT){
             reporteToken.requestFocus();
         }
    }//GEN-LAST:event_reporteErroresKeyPressed

    private void salirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salirKeyPressed
        //Verificamos que si al momento de presionar la flecha hacia la derecha para poder desplazarnos al boton de guardar cambios
        if (evt.getKeyCode()==KeyEvent.VK_RIGHT){
            guardarCambios.requestFocus();
            //Verificamos que si al momento de presionar la flecha arriba para poder desplazarnos al boton de buscar patrones
         } else if(evt.getKeyCode()==KeyEvent.VK_UP){
            buscarPatrones.requestFocus();
         }
    }//GEN-LAST:event_salirKeyPressed

    private void guardarCambiosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guardarCambiosKeyPressed
       //Verificamos que si al momento de presionar la flecha hacia la izquierda para poder desplazarnos al boton de salir
        if (evt.getKeyCode()==KeyEvent.VK_LEFT){
            salir.requestFocus();
            //Verificamos que si al momento de presionar la flecha arriba para poder desplazarnos al boton de buscar patrones
         } else if(evt.getKeyCode()==KeyEvent.VK_UP){
            buscarPatrones.requestFocus();
         }
    }//GEN-LAST:event_guardarCambiosKeyPressed

    private void analizarTokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizarTokenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_analizarTokenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analizarToken;
    private javax.swing.JButton buscarPatrones;
    private javax.swing.JButton guardarCambios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton reporteErrores;
    private javax.swing.JButton reporteToken;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
