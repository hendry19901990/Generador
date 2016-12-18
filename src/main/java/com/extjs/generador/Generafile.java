package com.extjs.generador;

import com.extjs.generador.ColumnType;
import com.extjs.generador.ConstructFileJS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Generafile {
	
    public static void createAllfiles(String artifactoID, String tabla, String directorioDestino, 
    		boolean createStore, boolean createController, boolean createViewModel,
    		boolean createViewController, boolean createGridPanel, List<ColumnType> listColumnTypes, String servicio) {
       
    	Pattern p = Pattern.compile("[a-zA-Z]+[_][a-zA-Z]+");
        Matcher m = p.matcher(tabla);
        
        if (m.matches()) {
        	   
            artifactoID  = artifactoID.substring(0, 1).toUpperCase() + artifactoID.substring(1).toLowerCase();
            File folderDirectorioDestino = new File(directorioDestino + File.separator + artifactoID);
            
            if (!folderDirectorioDestino.exists()) {
                folderDirectorioDestino.mkdir();
            }
            
            String[] output = tabla.split("\\_");
            String prefix = output[0];
            String modulo = output[1];
            
            modulo = prefix.substring(0, 1).toUpperCase() + prefix.substring(1).toLowerCase() + modulo.substring(0, 1).toUpperCase() + modulo.substring(1).toLowerCase();
            
            if (createStore) {
                
            	File folderModel = new File(folderDirectorioDestino.getAbsolutePath() + File.separator + "model");
                File folderStore = new File(folderDirectorioDestino.getAbsolutePath() + File.separator + "store");
                
                if (!folderModel.exists()) {
                    folderModel.mkdir();
                }
                
                if (!folderStore.exists()) {
                    folderStore.mkdir();
                }
                
                Generafile.createModelFile(artifactoID, folderModel.getAbsolutePath(), modulo, listColumnTypes);
                Generafile.createStoreFile(artifactoID, folderStore.getAbsolutePath(), modulo, servicio);
            }
            
            if (createController) {
                
            	File folderController = new File(folderDirectorioDestino.getAbsolutePath() + File.separator + "controller");
                
                if (!folderController.exists()) {
                    folderController.mkdir();
                }
                
                Generafile.createControllerFile(artifactoID, folderController.getAbsolutePath(), prefix.toLowerCase());
            }
            
            if (createViewModel) {
            	
            	File folderView = new File(folderDirectorioDestino.getAbsolutePath() + File.separator + "view");
                File folderViewModelPrefix = new File(folderDirectorioDestino.getAbsolutePath() + File.separator + "view" + File.separator + prefix.toLowerCase());
                File folderViewModel = new File(folderDirectorioDestino.getAbsolutePath() + File.separator + "view" + File.separator + prefix.toLowerCase() + File.separator + "model");
                
                if (!folderView.exists()) {
                    folderView.mkdir();
                }
                
                if (!folderViewModelPrefix.exists()) {
                    folderViewModelPrefix.mkdir();
                }
                
                if (!folderViewModel.exists()) {
                    folderViewModel.mkdir();
                }
                
                Generafile.createViewModelFile(artifactoID, folderViewModel.getAbsolutePath(), modulo, prefix.toLowerCase());
            }
            
            if (createViewController) {
            	
            	File folderView = new File(folderDirectorioDestino.getAbsolutePath() + File.separator + "view");
                File folderViewControllerPrefix = new File(folderDirectorioDestino.getAbsolutePath() + File.separator + "view" + File.separator + prefix.toLowerCase());
                File folderViewController = new File(folderDirectorioDestino.getAbsolutePath() + File.separator + "view" + File.separator + prefix.toLowerCase() + File.separator + "controller");
               
                if (!folderView.exists()) {
                    folderView.mkdir();
                }
               
                if (!folderViewControllerPrefix.exists()) {
                    folderViewControllerPrefix.mkdir();
                }
              
                if (!folderViewController.exists()) {
                    folderViewController.mkdir();
                }
                
                Generafile.createViewControllerFile(artifactoID, folderViewController.getAbsolutePath(), modulo, listColumnTypes, prefix.toLowerCase(), servicio);
            }
            
            if (createGridPanel) {
            	
            	File folderView = new File(folderDirectorioDestino.getAbsolutePath() + File.separator + "view");
                File folderViewGridPanelPrefix = new File(folderDirectorioDestino.getAbsolutePath() + File.separator + "view" + File.separator + prefix.toLowerCase());
                File folderGridPanel = new File(folderDirectorioDestino.getAbsolutePath() + File.separator + "view" + File.separator + prefix.toLowerCase() + File.separator + "view");
              
                if (!folderView.exists()) {
                    folderView.mkdir();
                }
                
                if (!folderViewGridPanelPrefix.exists()) {
                    folderView.mkdir();
                }
                
                if (!folderGridPanel.exists()) {
                    folderGridPanel.mkdir();
                }
                
                Generafile.createGridPanelFile(artifactoID, folderGridPanel.getAbsolutePath(), modulo, listColumnTypes, prefix.toLowerCase());
            }
            
            System.out.println("OK!");
        }
    }

    private static void createGridPanelFile(String modulo, String destino, String fileTocreate, List<ColumnType> list, String prefix) {
    	
        String contentFileFormulario = ConstructFileJS.createFormulario(modulo, fileTocreate, list, prefix);
        String contentFileGridPanel = ConstructFileJS.createGridPanel(modulo, fileTocreate, list, prefix);
        String contentFileWidget = ConstructFileJS.createWidget(modulo, fileTocreate, list, prefix);
        
        Writer ficheroFormulario = null;
        Writer ficheroGridPanel = null;
        Writer ficheroWidget = null;
        
        try {
        
        	ficheroFormulario = new BufferedWriter(new OutputStreamWriter(
        			new FileOutputStream(destino + File.separator + fileTocreate + "Formulario.js"), "UTF-8"));
        	
            ficheroFormulario.write(contentFileFormulario);
            
            if (ficheroFormulario != null) {
                ficheroFormulario.close();
            }
            
            ficheroGridPanel =   new BufferedWriter(new OutputStreamWriter(
        			new FileOutputStream(destino + File.separator + fileTocreate + "GridPanel.js"), "UTF-8"));
            
            ficheroGridPanel.write(contentFileGridPanel);
            
            if (ficheroGridPanel != null) {
                ficheroGridPanel.close();
            }
            
            ficheroWidget =  new BufferedWriter(new OutputStreamWriter(
        			new FileOutputStream(destino + File.separator + fileTocreate + "WidgetView.js"), "UTF-8"));
             
            ficheroWidget.write(contentFileWidget);
            
            if (ficheroWidget != null) {
                ficheroWidget.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void createModelFile(String modulo, String destino, String fileTocreate, List<ColumnType> list) {
    	
        String contentFile = ConstructFileJS.createModel(modulo, fileTocreate, list);
        Writer fichero = null;
        
        try {
            fichero =   new BufferedWriter(new OutputStreamWriter(
        			new FileOutputStream(destino + File.separator + fileTocreate + "Model.js"), "UTF-8"));
            
            fichero.write(contentFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void createStoreFile(String modulo, String destino, String fileTocreate, String servicio) {
    	
        String contentFile = ConstructFileJS.createStore(modulo, fileTocreate, servicio);
        Writer fichero = null;
              
        try {
            fichero = new BufferedWriter(new OutputStreamWriter(
        			new FileOutputStream(destino + File.separator + fileTocreate + "Store.js"), "UTF-8"));
            
            fichero.write(contentFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void createControllerFile(String modulo, String destino, String fileTocreate) {
    	
        String contentFile = ConstructFileJS.createController(modulo, fileTocreate);
        String controllerName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "Controller.js";
        File controllerFile = new File(destino + File.separator + controllerName);
        
        if (controllerFile.exists()) {
            return;
        }
        
        Writer fichero = null;
        
        try {
            fichero = new BufferedWriter(new OutputStreamWriter(
        			new FileOutputStream(destino + File.separator + controllerName), "UTF-8"));
             
            fichero.write(contentFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void createViewModelFile(String modulo, String destino, String fileTocreate, String prefix) {
    	
        String contentFile = ConstructFileJS.createViewModel(modulo, fileTocreate, prefix);
        Writer fichero = null;
        
        try {
            fichero = new BufferedWriter(new OutputStreamWriter(
        			new FileOutputStream(destino + File.separator + fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "ViewModel.js"), "UTF-8"));
             
            fichero.write(contentFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void createViewControllerFile(String modulo, String destino, String fileTocreate, List<ColumnType> list, String prefix, String servicio) {
    	
        String contentFile = ConstructFileJS.createViewController(modulo, fileTocreate, list, prefix, servicio);
        Writer fichero = null;
        
        try {
            fichero = new BufferedWriter(new OutputStreamWriter(
        			new FileOutputStream(destino + File.separator + fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "ViewController.js"), "UTF-8"));
             
            fichero.write(contentFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
