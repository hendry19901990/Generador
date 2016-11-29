package com.extjs.generador;

import com.extjs.generador.ColumnType;
import com.extjs.generador.Generafile;
import com.extjs.generador.UtilDatabase;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Formulario extends JFrame {
    private JTextField artifactId;
    private JCheckBox createController;
    private JCheckBox createGridPanel;
    private JCheckBox createStore;
    private JCheckBox createViewController;
    private JCheckBox createViewModel;
    private JFileChooser directorioDestino;
    private JButton generarBoton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JTextField tabla;

    public Formulario() {
        this.initComponents();
    }

    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.artifactId = new JTextField();
        this.tabla = new JTextField();
        this.directorioDestino = new JFileChooser();
        this.createController = new JCheckBox();
        this.createViewModel = new JCheckBox();
        this.createViewController = new JCheckBox();
        this.createStore = new JCheckBox();
        this.generarBoton = new JButton();
        this.jLabel3 = new JLabel();
        this.createGridPanel = new JCheckBox();
        this.setDefaultCloseOperation(3);
        this.setTitle("Genarador Extjs 6");
        this.jLabel1.setFont(new Font("Tahoma", 0, 12));
        this.jLabel1.setText("ArtifactId");
        this.jLabel2.setFont(new Font("Tahoma", 0, 12));
        this.jLabel2.setText("Prefijo o Tabla");
        
        this.artifactId.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent evt) {
                Formulario.this.artifactIdActionPerformed(evt);
            }
        });
        this.tabla.addActionListener(new ActionListener(){

             
            public void actionPerformed(ActionEvent evt) {
                Formulario.this.tablaActionPerformed(evt);
            }
        });
        this.directorioDestino.setFileSelectionMode(1);
        this.directorioDestino.setFont(new Font("Tahoma", 0, 12));
        this.directorioDestino.setSelectedFiles(null);
        this.createController.setFont(new Font("Tahoma", 0, 12));
        this.createController.setLabel("Controller");
        this.createController.addActionListener(new ActionListener(){

           
            public void actionPerformed(ActionEvent evt) {
                Formulario.this.createControllerActionPerformed(evt);
            }
        });
        this.createViewModel.setFont(new Font("Tahoma", 0, 12));
        this.createViewModel.setText("ViewModel");
        this.createViewController.setFont(new Font("Tahoma", 0, 12));
        this.createViewController.setText("ViewController");
        this.createStore.setFont(new Font("Tahoma", 0, 12));
        this.createStore.setText("Store");
        this.generarBoton.setText("Generar");
        this.generarBoton.addActionListener(new ActionListener(){

           
            public void actionPerformed(ActionEvent evt) {
                Formulario.this.generarBotonActionPerformed(evt);
            }
        });
        this.jLabel3.setFont(new Font("Tahoma", 0, 12));
        this.jLabel3.setText("Directorio Destino");
        this.createGridPanel.setText("GridPanel");
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(98, 98, 98).addComponent(this.jLabel1, -2, 68, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jLabel2, -2, 107, -2).addGap(110, 110, 110)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(365, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.tabla, -2, 207, -2).addGap(66, 66, 66)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.generarBoton, -2, 82, -2).addGap(50, 50, 50)))).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(33, 33, 33).addComponent(this.artifactId, -2, 197, -2)).addGroup(layout.createSequentialGroup().addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel3, -2, 143, -2).addComponent(this.directorioDestino, -2, 594, -2))).addGroup(layout.createSequentialGroup().addGap(71, 71, 71).addComponent(this.createController).addGap(18, 18, 18).addComponent(this.createViewModel).addGap(23, 23, 23).addComponent(this.createViewController).addGap(18, 18, 18).addComponent(this.createStore).addGap(18, 18, 18).addComponent(this.createGridPanel))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(28, 28, 28).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1, -2, 22, -2).addComponent(this.jLabel2)).addGap(5, 5, 5).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.artifactId, -2, -1, -2).addComponent(this.tabla, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, 32767).addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.directorioDestino, -2, 294, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.createController).addComponent(this.createViewModel).addComponent(this.createViewController).addComponent(this.createStore).addComponent(this.createGridPanel)).addGap(6, 6, 6).addComponent(this.generarBoton, -2, 31, -2).addContainerGap()));
        this.directorioDestino.setAcceptAllFileFilterUsed(false);
        this.directorioDestino.setDialogTitle("Directorio Destino");
        this.pack();
    }

    private void artifactIdActionPerformed(ActionEvent evt) {
    }

    private void tablaActionPerformed(ActionEvent evt) {
    }

    private void createControllerActionPerformed(ActionEvent evt) {
    }

    private void generarBotonActionPerformed(ActionEvent evt) {
        boolean continuar = true;
        String artifactoID = this.artifactId.getText();
        String table = this.tabla.getText();
        String directorioDest = this.directorioDestino.getCurrentDirectory().toString();
        if (artifactoID == null || artifactoID.isEmpty()) {
            continuar = false;
            JOptionPane.showMessageDialog(null, "!Debe escribir el artifactoID!");
            return;
        }
        if (table == null || table.isEmpty()) {
            continuar = false;
            JOptionPane.showMessageDialog(null, "!Debe escribir el Nombre de la Tabla!");
            return;
        }
        if (directorioDest == null || directorioDest.isEmpty()) {
            continuar = false;
            JOptionPane.showMessageDialog(null, "!Debe seleccionar el Escritorio Destino!");
            return;
        }
        if (!(this.createStore.isSelected() || this.createController.isSelected() || this.createViewModel.isSelected() || this.createViewController.isSelected() || this.createGridPanel.isSelected())) {
            continuar = false;
            JOptionPane.showMessageDialog(null, "!Debe seleccionar Al menos un archivo a Generar!");
            return;
        }
        boolean iscreateStore = this.createStore.isSelected();
        boolean iscreateController = this.createController.isSelected();
        boolean iscreateViewModel = this.createViewModel.isSelected();
        boolean iscreateViewController = this.createViewController.isSelected();
        boolean iscreateGridPanel = this.createGridPanel.isSelected();
        boolean success = false;
        List<String> all_tables = UtilDatabase.all_tables(table.toUpperCase());
        if (all_tables != null && all_tables.size() > 0) {
            for (String tabla_name : all_tables) {
                List<ColumnType> listColumnTypes = UtilDatabase.getListColumnTypes(tabla_name);
                if (listColumnTypes == null || listColumnTypes.size() == 0) {
                    continuar = false;
                }
                if (!continuar) continue;
                Generafile.createAllfiles(artifactoID, tabla_name, directorioDest, iscreateStore, iscreateController, iscreateViewModel, iscreateViewController, iscreateGridPanel, listColumnTypes);
                success = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "!No existe esa Tabla o Prefijo en la Database!");
        }
        if (success) {
            JOptionPane.showMessageDialog(null, "!Se Crearon los Archivos Satisfactoriamente!");
        }
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable(){
            
            public void run() {
                new Formulario().setVisible(true);
            }
        });
    }

}