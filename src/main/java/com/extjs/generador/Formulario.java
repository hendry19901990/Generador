package com.extjs.generador;

import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Hendry Rodriguez
 */
public class Formulario extends javax.swing.JFrame {


	private static final long serialVersionUID = 1L;
	/**
     * Creates new form Formulario
     */
    public Formulario() {
        initComponents();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        artifactId = new javax.swing.JTextField();
        tabla = new javax.swing.JTextField();
        directorioDestino = new javax.swing.JFileChooser();
        createController = new javax.swing.JCheckBox();
        createViewModel = new javax.swing.JCheckBox();
        createViewController = new javax.swing.JCheckBox();
        createStore = new javax.swing.JCheckBox();
        generarBoton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        createGridPanel = new javax.swing.JCheckBox();
        serviciolbl = new javax.swing.JLabel();
        servicio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Genarador Extjs 6");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("ArtifactId");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Prefijo o Tabla");

        artifactId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                artifactIdActionPerformed(evt);
            }
        });

        tabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tablaActionPerformed(evt);
            }
        });

        directorioDestino.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        directorioDestino.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        directorioDestino.setSelectedFiles(null);

        createController.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        createController.setText("Controller");
  /*      
        createController.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createControllerActionPerformed(evt);
            }
        }); 
   */

        createViewModel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        createViewModel.setText("ViewModel");

        createViewController.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        createViewController.setText("ViewController");

        createStore.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        createStore.setText("Store");

        generarBoton.setText("Generar");
        generarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarBotonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Directorio Destino");

        createGridPanel.setText("GridPanel");

        serviciolbl.setText("Servicio");

        servicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(365, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tabla, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(generarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(directorioDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(createController)
                        .addGap(18, 18, 18)
                        .addComponent(createViewModel)
                        .addGap(23, 23, 23)
                        .addComponent(createViewController)
                        .addGap(18, 18, 18)
                        .addComponent(createStore)
                        .addGap(18, 18, 18)
                        .addComponent(createGridPanel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(servicio)
                            .addComponent(artifactId, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(serviciolbl)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(artifactId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(serviciolbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(servicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(directorioDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createController)
                    .addComponent(createViewModel)
                    .addComponent(createViewController)
                    .addComponent(createStore)
                    .addComponent(createGridPanel))
                .addGap(6, 6, 6)
                .addComponent(generarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        directorioDestino.setAcceptAllFileFilterUsed(false);

        directorioDestino.setDialogTitle("Directorio Destino");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void artifactIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_artifactIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_artifactIdActionPerformed

    private void tablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tablaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaActionPerformed


    private void generarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarBotonActionPerformed
        // TODO add your handling code here:
        
        boolean continuar = true;
        
        String artifactoID = artifactId.getText();
	   String table = tabla.getText();
       String service = servicio.getText();
	   String directorioDest = directorioDestino.getCurrentDirectory().toString();
        
        //System.out.println(directorioDest);
        
        if(artifactoID==null || artifactoID.isEmpty()){
            continuar = false;
            JOptionPane.showMessageDialog(null, "!Debe escribir el artifactoID!");
            return;
        }
        
        if(table==null || table.isEmpty()){
            continuar = false;
            JOptionPane.showMessageDialog(null, "!Debe escribir el Nombre de la Tabla!");
            return;
        }

        if(service==null || service.isEmpty()){
            continuar = false;
            JOptionPane.showMessageDialog(null, "!Debe escribir el Nombre del Servicio!");
            return;
        }
                        
        if(directorioDest == null || directorioDest.isEmpty()){
            continuar = false;
            JOptionPane.showMessageDialog(null, "!Debe seleccionar el Escritorio Destino!");
            return;
        }
        
        if(!createStore.isSelected() && !createController.isSelected() && !createViewModel.isSelected() &&
                !createViewController.isSelected() && !createGridPanel.isSelected() ){
            
            continuar = false;
            JOptionPane.showMessageDialog(null, "!Debe seleccionar Al menos un archivo a Generar!");
            return;
            
        }
            
	
	boolean iscreateStore = createStore.isSelected();
	boolean iscreateController = createController.isSelected();
	boolean iscreateViewModel = createViewModel.isSelected();
	boolean iscreateViewController = createViewController.isSelected();
	boolean iscreateGridPanel = createGridPanel.isSelected();
        
        boolean success = false;

        List<String> all_tables = UtilDatabase.all_tables(table.toUpperCase());
        
        if(all_tables != null && all_tables.size()>0){
            
            for(String tabla_name : all_tables){
                
               List<ColumnType> listColumnTypes = UtilDatabase.getListColumnTypes(tabla_name);

                if(listColumnTypes == null || listColumnTypes.size()==0){

                    continuar = false;
                  /*  JOptionPane.showMessageDialog(null, "!Error de DataBase!");
                    return;*/

                }

                if(continuar){

                    Generafile.createAllfiles(artifactoID, tabla_name, directorioDest,
                            iscreateStore, iscreateController, iscreateViewModel, 
                            iscreateViewController, iscreateGridPanel, listColumnTypes, service);


                    success = true;

                }
                
            }
	

        
        }else{
            JOptionPane.showMessageDialog(null, "!No existe esa Tabla o Prefijo en la Database!");   
        }
        
        if(success)
             JOptionPane.showMessageDialog(null, "!Se Crearon los Archivos Satisfactoriamente!");       
       
        
    }//GEN-LAST:event_generarBotonActionPerformed

    private void servicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_servicioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formulario().setVisible(true);
            }
        });
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField artifactId;
    private javax.swing.JCheckBox createController;
    private javax.swing.JCheckBox createGridPanel;
    private javax.swing.JCheckBox createStore;
    private javax.swing.JCheckBox createViewController;
    private javax.swing.JCheckBox createViewModel;
    private javax.swing.JFileChooser directorioDestino;
    private javax.swing.JButton generarBoton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField servicio;
    private javax.swing.JLabel serviciolbl;
    private javax.swing.JTextField tabla;
    // End of variables declaration//GEN-END:variables
}
