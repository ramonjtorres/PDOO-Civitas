/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import civitas.*;

/**
 *
 * @author ramonjtorres
 */
public class PropiedadPanel extends javax.swing.JPanel {

    TituloPropiedad propiedad;
    
    /**
     * Creates new form PropiedadPanel
     */
    public PropiedadPanel() {
        initComponents();
    }
    
    public void setPropiedad(TituloPropiedad titulo){
    
        String t;
        
        this.propiedad = titulo;
        
        if(this.propiedad.getHipotecado())
            t = "Sí";
        else
            t = "No";
        
        TextoNombre.setText(this.propiedad.getNombre());
        NCasas.setText(Integer.toString(this.propiedad.getNumCasas()));
        NHoteles.setText(Integer.toString(this.propiedad.getNumHoteles()));
        TextoHipotecada.setText(t);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Nombre = new javax.swing.JLabel();
        TextoNombre = new javax.swing.JTextField();
        Casas = new javax.swing.JLabel();
        Hoteles = new javax.swing.JLabel();
        NCasas = new javax.swing.JTextField();
        NHoteles = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TextoHipotecada = new javax.swing.JTextField();

        Nombre.setText("Nombre:");

        TextoNombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TextoNombre.setEnabled(false);
        TextoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextoNombreActionPerformed(evt);
            }
        });

        Casas.setText("Nº Casas:");

        Hoteles.setText("Nº Hoteles:");

        NCasas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        NCasas.setDoubleBuffered(true);
        NCasas.setEnabled(false);

        NHoteles.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        NHoteles.setEnabled(false);
        NHoteles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NHotelesActionPerformed(evt);
            }
        });

        jLabel4.setText("Hipotecada:");

        TextoHipotecada.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TextoHipotecada.setEnabled(false);
        TextoHipotecada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextoHipotecadaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Nombre)
                        .addGap(117, 117, 117)
                        .addComponent(Casas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NCasas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TextoHipotecada, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Hoteles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NHoteles, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Hoteles)
                        .addComponent(NHoteles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Nombre)
                        .addComponent(Casas)
                        .addComponent(NCasas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(TextoHipotecada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TextoHipotecadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextoHipotecadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextoHipotecadaActionPerformed

    private void TextoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextoNombreActionPerformed

    private void NHotelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NHotelesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NHotelesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Casas;
    private javax.swing.JLabel Hoteles;
    private javax.swing.JTextField NCasas;
    private javax.swing.JTextField NHoteles;
    private javax.swing.JLabel Nombre;
    private javax.swing.JTextField TextoHipotecada;
    private javax.swing.JTextField TextoNombre;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
