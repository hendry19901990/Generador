package com.extjs.generador;

import com.extjs.generador.ColumnType;

import java.util.List;

public class ConstructFileJS {
    
	public static String createViewModel(String modulo, String fileTocreate, String prefix) {
        
		StringBuffer contenido = new StringBuffer();
        String viewModelName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "ViewModel";
        String storeRequire = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "Store";
        String storeName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1);
        
        contenido.append("Ext.define('" + modulo + ".view." + prefix + "." + "model" + "." + viewModelName + "', {");
        contenido.append("\n\t extend: 'Ext.app.ViewModel', ");
        contenido.append("\n\t alias: 'viewmodel." + prefix + "." + "model" + "." + viewModelName + "', ");
        contenido.append("\n\t requires: [ '" + modulo + ".store." + storeRequire + "' ], ");
        contenido.append("\n\n\t  data: { }, ");
        contenido.append("\n\n\t  stores:{ ");
        contenido.append("\n\t\t " + storeName + ": { type:'" + storeRequire + "' } ");
        contenido.append("\n\t  }");
        contenido.append("\n\n});");
        
        return contenido.toString();
    }

    public static String createController(String modulo, String fileTocreate) {
        
    	StringBuffer contenido = new StringBuffer();
        String modelName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "Model";
        String controllerName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "Controller";
        String storeRequire = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "Store";
        
        contenido.append("Ext.define('" + modulo + ".controller." + controllerName + "', {");
        contenido.append("\n\t extend: 'Ext.app.Controller', ");
        contenido.append("\n\t alias: 'controller." + controllerName + "', ");
        contenido.append("\n\n\t init: function(){ ");
        contenido.append("\n\t }");
        contenido.append("\n\n});");
        
        return contenido.toString();
    }

    public static String createStore(String modulo, String fileTocreate, String servicio) {
        
    	StringBuffer contenido = new StringBuffer();
        String storeName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "Store";
        String modelName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "Model";
        String tablaName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1);
        
        contenido.append("Ext.define('" + modulo + ".store." + storeName + "', {");
        contenido.append("\n\t extend: 'Ext.data.Store', ");
        contenido.append("\n\t requires: [ '" + modulo + ".model." + modelName + "' ], ");
        contenido.append("\n\n\t model: '" + modulo + ".model." + modelName + "', ");
        contenido.append("\n\t alias: 'store." + storeName + "', ");
        contenido.append("\n\t storeId: '" + storeName + "', ");
        contenido.append("\n\t pageSize:60, \n\t autoLoad: true,");
        contenido.append("\n\n\t proxy: { ");
        contenido.append("\n \t\t timeout:30000,");
        contenido.append("\n \t\t type: 'ajax', \n \t\t url: " + modulo + ".app.constants.URL_ROOT+'/"+servicio+"/"+tablaName.toLowerCase()+ "/listAll',");
        contenido.append(" \n \t\t reader: { ");
        contenido.append("\n \t\t\t type: 'json'");
        //contenido.append("\n \t\t\t type: 'json', \n \t\t\t root: 'root' ");
        contenido.append("\n \t\t }, ");
        contenido.append("\n \t\t afterRequest: function(request, success){");
        contenido.append("\n \t\t } ");
        contenido.append("\n \t } ");
        contenido.append("\n\n});");
        
        return contenido.toString();
    }

    public static String createModel(String modulo, String fileTocreate, List<ColumnType> list) {
        
    	StringBuffer contenido = new StringBuffer();
        String resultadoFileTocreate = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1);
        contenido.append("Ext.define('" + modulo + ".model." + resultadoFileTocreate + "Model', {");
        contenido.append("\n \t extend: 'Ext.data.Model', ");
        contenido.append("\n \t fields: [");
        
        int i = 0;
        if (list != null && list.size() > 0) {
            for (ColumnType columna : list) {
                String tipo = "'auto'";
                if (columna.getType().equalsIgnoreCase("VARCHAR2") || columna.getType().equalsIgnoreCase("VARCHAR") || 
                		columna.getType().equalsIgnoreCase("CHAR") || columna.getType().equalsIgnoreCase("NCHAR") || 
                		columna.getType().equalsIgnoreCase("NVARCHAR2") || columna.getType().equalsIgnoreCase("LOB") ||
                		columna.getType().equalsIgnoreCase("LONG")) {
                    
                	tipo = "'string'";
                    
                } else if (columna.getType().equalsIgnoreCase("NUMBER") || columna.getType().equalsIgnoreCase("FLOAT") ||
                		columna.getType().equalsIgnoreCase("DECIMAL") || columna.getType().equalsIgnoreCase("Floating-Point") || 
                		columna.getType().equalsIgnoreCase("BINARY_FLOAT") || columna.getType().equalsIgnoreCase("BINARY_DOUBLE")) {
                   
                	tipo = columna.getData_scale() > 0 ? "'float'" : "'int'";
                	
                } else if (columna.getType().equalsIgnoreCase("DATE") || columna.getType().equalsIgnoreCase("TIMESTAMP") || 
                		columna.getType().equalsIgnoreCase("DATE")) {
                    
                	tipo = "'date'";
                	
                }
                
                /*
                if(columna.isIsprimarykey()){
                	contenido.append("\n \t\t {name:'" + columna.getName() + "_pk', type:" + tipo + 
                			", convert: function(v,record){return record.data."+columna.getName()+";} },");
                }*/
                
                contenido.append("\n \t\t {name:'" + columna.getName() + "', type:" + tipo + ", mapping:'" + columna.getName() + "'}");
                
                if (i < (list.size()-1)) {
                    contenido.append(",");
                }
                

                
                ++i;
            }
        }
        
        contenido.append("\n \t ]");
        contenido.append("\n});");
        
        return contenido.toString();
    }

    public static String createWidget(String modulo, String fileTocreate, List<ColumnType> list, String prefix) {
      
    	StringBuffer contenido = new StringBuffer();
        String gridPanelName = fileTocreate + "GridPanel";
        String widgetName = fileTocreate + "WidgetView";
        String formularioName = fileTocreate + "window";
        String viewControllerName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "ViewController";
        String viewModelName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "ViewModel";
        String storeRequire = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1);
        
        contenido.append("Ext.define('" + modulo + ".view." + prefix + ".view." + widgetName + "', {");
        contenido.append("\n\t extend: 'Ext.panel.Panel',");
        contenido.append("\n\t alias: 'widget." + prefix + "." + "view" + "." + widgetName + "', ");
        contenido.append("\n\t id: '" + widgetName + "', ");
        
        contenido.append("\n\n\t requires: [ ");
        contenido.append("\n\t\t 'Ext.toolbar.Paging',");
        contenido.append("\n\t\t 'Ext.ux.ProgressBarPager',");
        contenido.append("\n\t\t '" + modulo + ".view." + prefix + "." + "view" + "." + gridPanelName + "',");
        contenido.append("\n\t\t '" + modulo + ".view." + prefix + "." + "view" + "." + formularioName + "',");
        contenido.append("\n\t\t '" + modulo + ".view." + prefix + "." + "controller" + "." + viewControllerName + "',");
        contenido.append("\n\t\t '" + modulo + ".view." + prefix + "." + "model" + "." + viewModelName + "'");
        contenido.append("\n\t ],");
        
        contenido.append("\n\n\t viewModel: { type: '" + prefix + ".model." + viewModelName + "'},");
        contenido.append("\n\t controller: '" + prefix + ".controller." + viewControllerName + "',");
     //   contenido.append("\n\n\t width: 740, ");
        
        
        contenido.append("\n\n\t items: [{ xtype: '" + gridPanelName + "'} ], ");
        
        contenido.append("\n\n\t dockedItems: [{ ");
        contenido.append("\n\t\t xtype: 'toolbar',");
        contenido.append("\n\t\t dock: 'bottom',");
        contenido.append("\n\t\t ui: 'footer',");
        contenido.append("\n\t\t fixed: true,");
        contenido.append("\n\t\t items: [{");
        contenido.append("\n\t\t\t xtype: 'pagingtoolbar',");
        contenido.append("\n\t\t\t pageSize: 10,");
        contenido.append("\n\t\t\t width: '100%',");
        contenido.append("\n\t\t\t displayInfo: true,");
        contenido.append("\n\t\t\t bind:{ store: '{" + storeRequire + "}' },");
        contenido.append("\n\t\t\t plugins: new Ext.ux.ProgressBarPager()");
        contenido.append("\n\t\t }]");
        contenido.append("\n\t }]");
        
        contenido.append("\n});");
        
        return contenido.toString();
    }

    public static String createGridPanel(String modulo, String fileTocreate, List<ColumnType> list, String prefix) {
        
    	StringBuffer contenido = new StringBuffer();
        String gridPanelName = fileTocreate + "GridPanel";
        String storeRequire = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1);
        String viewControllerName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "ViewController";
        String viewModelName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "ViewModel";
        
        contenido.append("Ext.define('" + modulo + ".view." + prefix + "." + "view" + "." + gridPanelName + "', {");
        contenido.append("\n \t extend: 'Ext.grid.Panel', ");
        contenido.append("\n \t xtype: '" + gridPanelName + "', ");
        contenido.append("\n\n\t requires: [ ");
        contenido.append("\n\t\t 'Ext.data.*',");
        contenido.append("\n\t\t 'Ext.grid.*',");
        contenido.append("\n\t\t 'Ext.util.*',");
        contenido.append("\n\t\t 'Ext.grid.filters.Filters',");
        contenido.append("\n\t\t 'Ext.toolbar.Paging',");
        contenido.append("\n\t\t '" + modulo + ".view." + prefix + "." + "controller" + "." + viewControllerName + "',");
        contenido.append("\n\t\t '" + modulo + ".view." + prefix + "." + "model" + "." + viewModelName + "'");
        contenido.append("\n\t ],");
        contenido.append("\n\n\t viewModel: { type: '" + prefix + ".model." + viewModelName + "'},");
        contenido.append("\n\t controller: '" + prefix + ".controller." + viewControllerName + "',");
        contenido.append("\n\t plugins: 'gridfilters',");
        //contenido.append("\n \t title: '" + gridPanelName + "', ");
        contenido.append("\n \t bind: {   store: '{" + storeRequire + "}' }, ");
        contenido.append("\n \t columns: [  ");
        
        if (list != null) {
        	int i=0;
            for (ColumnType columna : list) {
            	
            	String filter = "";
            	
            	if (columna.getType().equalsIgnoreCase("NUMBER") || columna.getType().equalsIgnoreCase("FLOAT") ||
                		columna.getType().equalsIgnoreCase("DECIMAL") || columna.getType().equalsIgnoreCase("Floating-Point") || 
                		columna.getType().equalsIgnoreCase("BINARY_FLOAT") || columna.getType().equalsIgnoreCase("BINARY_DOUBLE")) {
                   
            		filter = "filter: 'number'";
                	
                } else if (columna.getType().equalsIgnoreCase("DATE") || columna.getType().equalsIgnoreCase("TIMESTAMP") || 
                		columna.getType().equalsIgnoreCase("DATE")) {
                    
                	filter = "xtype: 'datecolumn', filter: { type:'date', fields:{ lt:{ text: 'Antes de'}, gt:{ text:'Despues de'}, eq:{ text: 'En '} } }";
                	
                }else{
                	filter = "filter: {type: 'string'}";
                }
            	
            	String texto = columna.getAlias().substring(0, 1).toUpperCase() + columna.getAlias().substring(1);
            	
                contenido.append("\n \t\t {text:'"+texto+ "', "+filter+",dataIndex:'" + columna.getName() + "', flex: 1}");
                
                if(i < list.size()-1)
                	contenido.append(",");
                
                i++;
            }
            
            /*
            contenido.append("\n \t\t {");
            contenido.append("\n \t\t\t xtype:'actioncolumn', ");
            contenido.append("\n \t\t\t flex: 1, ");
            contenido.append("\n \t\t\t text:'Accion Eliminar', ");
            contenido.append("\n \t\t\t items: [{ ");
            contenido.append("\n \t\t\t\t iconCls: 'fa-remove', ");
            contenido.append("\n \t\t\t\t tooltip: 'Eliminar', ");
            contenido.append("\n \t\t\t\t altText: 'Eliminar', ");
            contenido.append("\n \t\t\t\t handler: 'onDeleteRow' ");
            contenido.append("\n \t\t\t }] ");
            contenido.append("\n \t\t }");
            */
        }
        
        contenido.append("\n \t ], ");
        contenido.append("\n \t tbar: [  ");
        contenido.append("\n\t\t { ");
        contenido.append("\n\t\t\t text:'Agregar',");
        contenido.append("\n\t\t\t listeners:{ click:'newRecord'},");
        contenido.append("\n\t\t\t iconCls : 'x-fa fa-plus-square-o'");
        contenido.append("\n\t\t }, ");
        //contenido.append("\n\t\t  '->', ");
        contenido.append("\n\t\t { ");
        contenido.append("\n\t\t\t text: 'Exportar a Excel', ");
        contenido.append("\n\t\t\t listeners : { click:'exportExcell'}, ");
        contenido.append("\n\t\t\t iconCls : 'x-fa fa-file-excel-o' ");
        contenido.append("\n\t\t } ");
        contenido.append("\n \t ], ");
        
        /*
        contenido.append("\n \t bbar: {  ");
        contenido.append("\n\t\t xtype: 'pagingtoolbar', \n\t\t pageSize: 10, \n\t\t displayInfo: true,\n\t\t bind:{ store: '{" + storeRequire + "}' }");
        contenido.append("\n \t }, ");*/
        
        contenido.append("\n\t listeners: {  itemdblclick: 'onItemSelected' } ");
        contenido.append("\n});");
        
        return contenido.toString();
    }

    public static String createFormulario(String modulo, String fileTocreate, List<ColumnType> list, String prefix) {
    	
        StringBuffer contenido = new StringBuffer();
        String formularioName = fileTocreate + "window";
        String formularioTitle = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1);
       // int heightWindow = list != null ? (list.size() + 1) * 50 : 0;
        String viewControllerName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "ViewController";
        String viewModelName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "ViewModel";
        
        
        contenido.append("Ext.define('" + modulo + ".view." + prefix + "." + "view" + "." + formularioName + "', {");
        contenido.append("\n \t extend: 'Ext.window.Window', ");
        contenido.append("\n \t alias: 'widget." + formularioName + "', ");
        contenido.append("\n\t title: '"+formularioTitle+"', \n\t width: 400,");
        //contenido.append("\n\t height: " + heightWindow + ", ");
        contenido.append("\n\n\t requires: [ ");
        contenido.append("\n\t\t '" + modulo + ".view." + prefix + "." + "controller" + "." + viewControllerName + "',");
        contenido.append("\n\t\t '" + modulo + ".view." + prefix + "." + "model" + "." + viewModelName + "'");
        contenido.append("\n\t ],");
        contenido.append("\n\n\t viewModel: { type: '" + prefix + ".model." + viewModelName + "'},");
        contenido.append("\n\t controller: '" + prefix + ".controller." + viewControllerName + "',");
        contenido.append("\n\n\t items: [ \n\t{");
        contenido.append("\n\t\t xtype: 'form',");
        contenido.append("\n\t\t bodyPadding: 10,");
        
        contenido.append("\n\t\t items:[ ");
        if (list != null) {
        	int i=0;
            for (ColumnType columna : list) {
                String tipo = "'textfield'";
                if (columna.getType().equalsIgnoreCase("VARCHAR2") || columna.getType().equalsIgnoreCase("VARCHAR") || columna.getType().equalsIgnoreCase("CHAR") || columna.getType().equalsIgnoreCase("NCHAR") || columna.getType().equalsIgnoreCase("NVARCHAR2") || columna.getType().equalsIgnoreCase("LOB") || columna.getType().equalsIgnoreCase("LONG")) {
                    tipo = "'textfield'";
                } else if (columna.getType().equalsIgnoreCase("NUMBER") || columna.getType().equalsIgnoreCase("FLOAT") || columna.getType().equalsIgnoreCase("DECIMAL") || columna.getType().equalsIgnoreCase("Floating-Point") || columna.getType().equalsIgnoreCase("BINARY_FLOAT") || columna.getType().equalsIgnoreCase("BINARY_DOUBLE")) {
                    tipo = "'numberfield'";
                } else if (columna.getType().equalsIgnoreCase("DATE") || columna.getType().equalsIgnoreCase("TIMESTAMP") || columna.getType().equalsIgnoreCase("DATE")) {
                    tipo = "'datefield'";
                }
                
                if(columna.isIsprimarykey()){
                	
                	String blanco =  ", disabled: true";
                	contenido.append("\n \t\t\t {name:'" + columna.getName() + "', editable: false, xtype:'textfield'"+ ", anchor:'100%', fieldLabel:'" + columna.getAlias().substring(0, 1).toUpperCase() + columna.getAlias().substring(1) + "'} ");
                	//contenido.append("\n \t\t\t {name:'" + columna.getName() +"_pk'" + blanco + ", xtype:" + tipo + ", anchor:'100%', fieldLabel:'" + columna.getName().substring(0, 1).toUpperCase() + columna.getName().substring(1) + "'} ");
                    
                	
                }else{
               
                	String blanco = (columna.isIsnull())?  " " : ", allowBlank:false, blankText:'Este Campo es Obligatorio'";
                	contenido.append("\n \t\t\t {name:'" + columna.getName() + "'" + blanco + ", xtype:" + tipo + ", anchor:'100%', fieldLabel:'" + columna.getAlias().substring(0, 1).toUpperCase() + columna.getAlias().substring(1) + "'} ");
                
                }
                
                if(i < (list.size()-1))
                	contenido.append(",");
                
                i++;
            }
            
        }
        
        contenido.append("\n\t\t ], ");
        
        contenido.append("\n\n\t\t dockedItems: [{ ");
        contenido.append("\n\t\t\t xtype: 'toolbar',");
        contenido.append("\n\t\t\t dock: 'bottom',");
        contenido.append("\n\t\t\t ui: 'footer',");
        contenido.append("\n\t\t\t fixed: true,");
        contenido.append("\n\t\t\t items: [");
        contenido.append("\n\t\t\t\t {xtype: 'button', text: 'Cancelar', listeners: { click: 'onReset'  } },");
        contenido.append("\n\t\t\t\t {xtype: 'button', text: 'Anular', listeners: { click: 'onDeleteRow'  } },");
        contenido.append("\n\t\t\t\t '->',");
        contenido.append("\n\t\t\t\t {xtype: 'button', text: 'Guardar', listeners: { click: 'onSave'  }}");
        contenido.append("\n\t\t\t ]");
        
        contenido.append("\n\t\t }] ");
        
        /*
        contenido.append("\n\t\t buttons:[ ");
        contenido.append("\n\n \t\t\t {");
        contenido.append("\n\t\t\t\t xtype: 'button',");
        contenido.append("\n\t\t\t\t text: 'Cancelar',");
        contenido.append("\n \t\t\t\t listeners: { click: 'onReset'  }, align: 'left'");
        contenido.append("\n \t\t\t },");
        contenido.append("\n\n \t\t\t {");
        contenido.append("\n\t\t\t\t xtype: 'button',");
        contenido.append("\n\t\t\t\t text: 'Anular',");
        contenido.append("\n \t\t\t\t listeners: { click: 'onDeleteRow'  }, align: 'left'");
        contenido.append("\n \t\t\t },");
        contenido.append("\n \t\t\t  {xtype: 'tbspacer',width:60}, {xtype: 'tbspacer',width:60},");
        contenido.append("\n \t\t\t {");
        contenido.append("\n \t\t\t\t  xtype: 'button',");
        contenido.append("\n \t\t\t\t  text: 'Guardar',");
        contenido.append("\n \t\t\t\t  listeners: { click: 'onSave'  }, align: 'right'");
        contenido.append("\n \t\t\t }");
        contenido.append("\n\t\t ] ");
        */
        
        contenido.append("\n\n\t\t}");
        
        contenido.append("\n\t ]");
        contenido.append("\n\n});");
        
        return contenido.toString();
    }

    public static String createViewController(String modulo, String fileTocreate, List<ColumnType> list, String prefix, String servicio) {
    	
        StringBuffer contenido = new StringBuffer();
        String viewControllerName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "ViewController";
        String formularioName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "window";
        String storeRequire = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1);
        String modelName = modulo + ".model." + fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "Model";
        String storeNameVar = "store" + fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1);
        String tablaName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1);
        
        contenido.append("Ext.define('" + modulo + ".view." + prefix + "." + "controller" + "." + viewControllerName + "', {");
        contenido.append("\n \t extend: 'Ext.app.ViewController', ");
        contenido.append("\n \t alias: 'controller." + prefix + "." + "controller" + "." + viewControllerName + "', ");
        contenido.append("\n\n\t newRecord: function(){ ");
        contenido.append("\n\t\t var ventana = Ext.widget('" + formularioName + "');");
        contenido.append("\n\t\t ventana.controller = this;");
        
        String recordToNew = "";
        String recordToDel = "";
        for(ColumnType col : list){
        	
        	if(col.isIsprimarykey()){
        		recordToNew = col.getName() + " : 0";
        		recordToDel = col.getName() + " : form.findField('"+col.getName()+"').getValue()";
        		break;
        	}
        	
        }
        
        
        contenido.append("\n\t\t var record = Ext.create('"+modelName+"',{"+recordToNew+"}); ");
        contenido.append("\n\t\t ventana.down('form').getForm().loadRecord(record); ");
        contenido.append("\n\t\t ventana.show(); ");
        contenido.append("\n\t }, ");
        
        contenido.append("\n\n\t onItemSelected : function( grid , record , tr , rowIndex , e , eOpts ){ ");
        contenido.append("\n\t\t var ventana = Ext.widget('" + formularioName + "');");
        contenido.append("\n\t\t ventana.controller = this;");
        contenido.append("\n\t\t ventana.down('form').getForm().loadRecord(record); ");
        contenido.append("\n\t\t ventana.show(); ");
        contenido.append("\n\t }, ");
        
        contenido.append("\n\n\t onReset: function(button, e, eOpts) { ");
        contenido.append("\n\t\t var ventana = button.up('toolbar').up('form').up('window');");
        contenido.append("\n\t\t ventana.down('form').getForm().reset();");
        contenido.append("\n\t\t ventana.hide();");
        contenido.append("\n\t }, ");
        
        contenido.append("\n\n\t exportExcell: function(button, e, eOpts) { ");
        contenido.append("\n\n\t }, ");
        
        contenido.append("\n\n\t onDeleteRow: function(button, e, eOpts) {  ");
        contenido.append("\n\n\t\t Ext.Msg.confirm('Eliminar','Estas de Seguro de Continuar?',function(buttonId, value){");
        contenido.append("\n\n\t\t\t if (buttonId === 'yes'){");
        contenido.append("\n\n\t\t\t\t var waitModal = Ext.MessageBox.show({");
        contenido.append("\n\t\t\t\t\t msg: 'Enviando, espere un momento por favor...',");
        contenido.append("\n\t\t\t\t\t progressText: 'Ingresando...',");
        contenido.append("\n\t\t\t\t\t width: 400,");
        contenido.append("\n\t\t\t\t\t wait: { interval: 200 },");
        contenido.append("\n\t\t\t\t\t animateTarget: button");
        contenido.append("\n\t\t\t\t   });");
        contenido.append("\n\n\t\t\t\t var " + storeNameVar + " = this.getViewModel().getStore('" + storeRequire + "');");
        contenido.append("\n\t\t\t\t var ventana = button.up('toolbar').up('form').up('window');");
        contenido.append("\n\t\t\t\t var form = ventana.down('form').getForm();");
        contenido.append("\n\t\t\t\t Ext.Ajax.request({ ");
        contenido.append("\n\t\t\t\t\t url: " + modulo + ".app.constants.URL_ROOT+'/"+servicio+"/"+tablaName.toLowerCase()+ "/delete',  \n\t\t\t\t\t method: 'DELETE',");
        contenido.append("\n\t\t\t\t\t headers: {'Content-Type' : 'application/json' }, \n\t\t\t\t\t params: {"+recordToDel+"},  ");
        //contenido.append("\n\t\t\t\t\t waitMsg:'Espere un momento Por favor..',");
        contenido.append("\n\t\t\t\t\t success: function(resp) { ");
        contenido.append("\n\t\t\t\t\t\t waitModal.hide();");
        contenido.append("\n\t\t\t\t\t\t " + storeNameVar + ".load();  ");
        contenido.append("\n\t\t\t\t\t }, ");
        contenido.append("\n\t\t\t\t\t failure: function(response, opt) {   ");
        contenido.append("\n\t\t\t\t\t\t waitModal.hide();");
        contenido.append("\n\t\t\t\t\t\t Ext.Msg.alert('Error', response.status); ");
        contenido.append("\n\t\t\t\t\t } ");
        contenido.append("\n\t\t\t\t });");
        contenido.append("\n\t\t\t }else{");
        contenido.append("\n\t\t\t\t  return false; ");
        contenido.append("\n\t\t\t }");
        contenido.append("\n\n\t\t }, this);");
        contenido.append("\n\n\t }, ");
        
        contenido.append("\n\n\t onSave: function(button, e, eOpts) { ");
        contenido.append("\n\n\t\t var waitModal = Ext.MessageBox.show({");
        contenido.append("\n\t\t\t msg: 'Enviando, espere un momento por favor...',");
        contenido.append("\n\t\t\t progressText: 'Ingresando...',");
        contenido.append("\n\t\t\t width: 400,");
        contenido.append("\n\t\t\t wait: { interval: 200 },");
        contenido.append("\n\t\t\t animateTarget: button");
        contenido.append("\n\t\t   });");
        contenido.append("\n\n\t\t  var " + storeNameVar + " = this.getViewModel().getStore('" + storeRequire + "');");
        contenido.append("\n\t\t  var ventana = button.up('toolbar').up('form').up('window');");
        contenido.append("\n\t\t  var form = ventana.down('form').getForm();");
        contenido.append("\n\n\t\t if (form.isValid()) {");
        contenido.append("\n\t\t  Ext.Ajax.request({ ");
        contenido.append("\n\t\t\t url :" + modulo + ".app.constants.URL_ROOT+'/"+servicio+"/"+tablaName.toLowerCase()+ "/insert', \n\t\t\t method: 'POST',");
        contenido.append("\n\t\t\t headers: {'Content-Type' : 'application/json' }, \n\t\t\t params:  Ext.encode(form.getValues()), ");
        //contenido.append("\n\t\t\t waitMsg:'Espere un momento Por favor..',");
        contenido.append("\n\t\t\t success: function(form, action) { ");
        contenido.append("\n\t\t\t\t waitModal.hide();");
        contenido.append("\n\t\t\t\t Ext.Msg.alert('Notificacion', 'Se Guardo Satisfactoriamente');");
        contenido.append("\n\t\t\t\t button.up('form').up('window').hide();");
        contenido.append("\n\t\t\t\t " + storeNameVar + ".load();");
        contenido.append("\n\t\t\t }, ");
        contenido.append("\n\t\t\t failure: function(response, opt) {  ");
        contenido.append("\n\t\t\t\t waitModal.hide();");
        contenido.append("\n\t\t\t\t Ext.Msg.alert('Error', response.status); ");
        contenido.append("\n\t\t\t } ");
        contenido.append("\n\t\t  });");
        contenido.append("\n\n\t\t }else{");
        contenido.append("\n\t\t\t Ext.toast({");
        contenido.append("\n\t\t\t\t html: 'Datos de formulario inválido', \n\t\t\t\t align: 'tr',");
        contenido.append("\n\t\t\t\t slideInDuration: 400,\n\t\t\t\t minWidth: 400,");
        contenido.append("\n\t\t\t\t iconCls: 'x-fa fa-bullhorn',\n\t\t\t\t title: 'Notificación Sistema',");
        contenido.append("\n\t\t\t\t closable: true");
        contenido.append("\n\t\t\t });");
        contenido.append("\n\n\t\t }");
        contenido.append("\n\t } ");
        
        contenido.append("\n});");
        
        return contenido.toString();
    }
}