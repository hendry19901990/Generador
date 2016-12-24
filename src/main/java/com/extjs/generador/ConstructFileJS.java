package com.extjs.generador;

import java.util.List;

public class ConstructFileJS {
    
	public static String createViewModel(String modulo, String fileTocreate, String prefix) {
        
		StringBuffer contenido = new StringBuffer();
        String viewModelName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "ViewModel";
        String storeRequire = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "Store";
        String storeName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1);
        String modelName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "Model";
        
        contenido.append("Ext.define('" + modulo + ".view." + prefix + "." + "model" + "." + viewModelName + "', {");
        contenido.append("\n\t extend: 'Ext.app.ViewModel', ");
        contenido.append("\n\t alias: 'viewmodel." + prefix + "." + "model" + "." + viewModelName + "', ");
        contenido.append("\n\t requires: [ ");
        contenido.append("\n\t\t //'" + modulo + ".store." + storeRequire + "',");
        contenido.append("\n\t\t '" + modulo + ".model." + modelName + "',");
        contenido.append("\n\t\t 'Ext.data.proxy.Memory',");
        contenido.append("\n\t\t 'Ext.data.reader.Json'");
        contenido.append("\n\t ],");
        contenido.append("\n\n\t  data: { }, ");
        contenido.append("\n\n\t  stores:{ ");
        contenido.append("\n\t\t //" + storeName + ": { type:'" + storeRequire + "' } ");
        contenido.append("\n\t\t " + storeName + ":{");
        contenido.append("\n\t\t model: '" + modulo + ".model." + modelName + "', ");
        contenido.append("\n\t\t storeId: '" + storeName + "', ");
        contenido.append("\n\t\t remoteSort: true,");
        contenido.append("\n\t\t remoteFilter: true,");
        contenido.append("\n\t\t pageSize: "+modulo+".app.constants.PAGE_SIZE,");
        contenido.append("\n\t\t autoLoad: true,");
        contenido.append("\n\t\t proxy: {");
        contenido.append("\n\t\t\t type: 'memory',");
        contenido.append("\n\t\t\t enablePaging: true,");
        contenido.append("\n\t\t\t reader: {");
        contenido.append("\n\t\t\t\t type: 'json'");
        contenido.append("\n\t\t\t }");
        contenido.append("\n\t\t }");
        contenido.append("\n\t\t }");
        contenido.append("\n\t  }");
        contenido.append("\n\n});");
        
        return contenido.toString();
    }

    public static String createController(String modulo, String fileTocreate) {
        
    	StringBuffer contenido = new StringBuffer();
       // String modelName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "Model";
        String controllerName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "Controller";
      //  String storeRequire = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "Store";
        
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
       // String tablaName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1);
        
        contenido.append("Ext.define('" + modulo + ".store." + storeName + "', {");
        contenido.append("\n\t extend: 'Ext.data.Store', ");
        contenido.append("\n\t requires: [ ");
        contenido.append("\n\t\t '" + modulo + ".model." + modelName + "', ");
        contenido.append("\n\t\t 'Ext.data.proxy.Memory',");
        contenido.append("\n\t\t 'Ext.data.reader.Json' ");
        contenido.append("\n\t ], ");
        contenido.append("\n\n\t model: '" + modulo + ".model." + modelName + "', ");
        contenido.append("\n\t alias: 'store." + storeName + "', ");
        contenido.append("\n\t storeId: '" + storeName + "', ");
        contenido.append("\n\t remoteSort: true,");
        contenido.append("\n\t remoteFilter: true,");
        contenido.append("\n\n\t constructor: function(cfg) { ");
        contenido.append("\n\t\t var me = this;");
        contenido.append("\n\t\t cfg = cfg || {};");
        contenido.append("\n\t\t me.callParent([Ext.apply({");
        contenido.append("\n\t\t\t pageSize: "+modulo+".app.constants.PAGE_SIZE,");
        contenido.append("\n\t\t\t storeId: '" + storeName + "', ");
        contenido.append("\n\t\t\t autoLoad: true,");
        contenido.append("\n\t\t\t proxy: {");
        contenido.append("\n\t\t\t\t type: 'memory',");
        contenido.append("\n\t\t\t\t enablePaging: true,");
        contenido.append("\n\t\t\t\t reader: {");
        contenido.append("\n\t\t\t\t\t  type: 'json'");
        contenido.append("\n\t\t\t\t }");
        contenido.append("\n\t\t\t }");
        contenido.append("\n\t\t }, cfg)]);");
        contenido.append("\n\n\t } ");
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
                boolean isDate = false;
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
                	isDate = true;
                	
                }
                
                /*
                if(columna.isIsprimarykey()){
                	contenido.append("\n \t\t {name:'" + columna.getName() + "_pk', type:" + tipo + 
                			", convert: function(v,record){return record.data."+columna.getName()+";} },");
                }*/
                
                
                
                if(isDate){
                	
                	contenido.append("\n \t\t {");
                	contenido.append("\n \t\t\t name:'" + columna.getName() + "', ");
                	contenido.append("\n \t\t\t type:" + tipo + ",");
                	contenido.append("\n \t\t\t mapping:'" + columna.getName() + "',");
                	contenido.append("\n \t\t\t convert: function(value, record){");
                	contenido.append("\n \t\t\t\t  if(value!=null){");
                	contenido.append("\n \t\t\t\t\t return new Date(value);");
                	contenido.append("\n \t\t\t\t  }else{");
                	contenido.append("\n \t\t\t\t\t return value;");
                	contenido.append("\n \t\t\t\t  }");
                	contenido.append("\n \t\t\t } ");
                	contenido.append("\n \t\t }");
                	
                }else{
                	
                	contenido.append("\n \t\t {name:'" + columna.getName() + "', type:" + tipo + ", mapping:'" + columna.getName() + "'}");
                	
                }
                	
                
                
                
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
        contenido.append("\n\t scrollable: true, ");
        
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
       // contenido.append("\n\t\t 'Ext.grid.filters.Filters',");
        contenido.append("\n\t\t 'Ext.toolbar.Paging',");
        contenido.append("\n\t\t 'Ext.ux.ProgressBarPager',");
        contenido.append("\n\t\t '" + modulo + ".view." + prefix + "." + "controller" + "." + viewControllerName + "',");
        contenido.append("\n\t\t '" + modulo + ".view." + prefix + "." + "model" + "." + viewModelName + "'");
        contenido.append("\n\t ],");
        contenido.append("\n\n\t viewModel: { type: '" + prefix + ".model." + viewModelName + "'},");
        contenido.append("\n\t controller: '" + prefix + ".controller." + viewControllerName + "',");
        contenido.append("\n\t plugins: 'gridfilters',");
        contenido.append("\n\t width: '100%', ");
        contenido.append("\n \t bind: {   store: '{" + storeRequire + "}' }, ");
        contenido.append("\n \t columns: [  ");
        
        if (list != null) {
        	int i=0;
            for (ColumnType columna : list) {
            	
            	boolean isDate = false;
            	
            	String filter = "";
            	
            	if (columna.getType().equalsIgnoreCase("NUMBER") || columna.getType().equalsIgnoreCase("FLOAT") ||
                		columna.getType().equalsIgnoreCase("DECIMAL") || columna.getType().equalsIgnoreCase("Floating-Point") || 
                		columna.getType().equalsIgnoreCase("BINARY_FLOAT") || columna.getType().equalsIgnoreCase("BINARY_DOUBLE")) {
                   
            		filter = "filter: 'number'";
                	
                } else if (columna.getType().equalsIgnoreCase("DATE") || columna.getType().equalsIgnoreCase("TIMESTAMP") || 
                		columna.getType().equalsIgnoreCase("DATE")) {
                    
                	filter = "xtype: 'datecolumn',  format: 'd-m-Y H', filter: { type:'date', fields:{ lt:{ text: 'Antes de'}, gt:{ text:'Despues de'}, eq:{ text: 'En '} } }";
                	isDate = true;
                	 
                }else{
                	filter = "filter: {type: 'string'}";
                }
            	
            	String texto = columna.getAlias().substring(0, 1).toUpperCase() + columna.getAlias().substring(1);
            	
            	if(i==0){
            		contenido.append("\n \t\t {xtype: 'rownumberer'},");
            		
            		contenido.append("\n\t\t { ");
            		contenido.append("\n\t\t\t width: 70,");
            		contenido.append("\n\t\t\t xtype: 'widgetcolumn',");
            		contenido.append("\n\t\t\t widget: {");
            		contenido.append("\n\t\t\t\t width: '100%',");
            		contenido.append("\n\t\t\t\t xtype: 'button',");
            		contenido.append("\n\t\t\t\t iconCls: 'x-fa fa-eye',");
            		contenido.append("\n\t\t\t\t style: 'background:#BDBFC1;border-color:transparent;',");
            		contenido.append("\n\t\t\t\t text: '',");
            		contenido.append("\n\t\t\t\t listeners: {");
            		contenido.append("\n\t\t\t\t\t click: {");
            		contenido.append("\n\t\t\t\t\t\t fn: 'onSeeDetailItem',");
            		contenido.append("\n\t\t\t\t\t\t scope: 'controller'");
            		contenido.append("\n\t\t\t\t\t }");
            		contenido.append("\n\t\t\t\t }");
            		contenido.append("\n\t\t\t }");
            		contenido.append("\n\t\t },");
            		
            	}
            	
            	if(isDate){
            		
            		contenido.append("\n \t\t {");
            		contenido.append("\n \t\t\t text:'"+texto+ "', ");
            		contenido.append("\n \t\t\t xtype: 'datecolumn',");
            		contenido.append("\n \t\t\t format: 'Y-m-d H:i:s',");
            		contenido.append("\n \t\t\t filter: {");
            		contenido.append("\n \t\t\t\t type:'date',");
            		contenido.append("\n \t\t\t\t fields:{");
            		contenido.append("\n \t\t\t\t\t lt:{ text: 'Antes de'},");
            		contenido.append("\n \t\t\t\t\t gt:{ text:'Despues de'},");
            		contenido.append("\n \t\t\t\t\t eq:{ text: 'En '}");
            		contenido.append("\n \t\t\t\t }");
            		contenido.append("\n \t\t\t },");
            		contenido.append("\n \t\t\t dataIndex:'" + columna.getName() + "', ");
            		contenido.append("\n \t\t\t flex: 1");
            		contenido.append("\n \t\t }");
            		
            	}else{
            		
            		contenido.append("\n \t\t {text:'"+texto+ "', "+filter+",dataIndex:'" + columna.getName() + "', flex: 1}");
            		
            	}
                
                
                if(i < list.size()-1)
                	contenido.append(",");
                
                i++;
            }
            

        }
        
        contenido.append("\n \t ], ");
        contenido.append("\n \t tbar: [  ");
        contenido.append("\n\t\t { ");
        contenido.append("\n\t\t\t text:'Agregar',");
        contenido.append("\n\t\t\t listeners:{ click:'newRecord'},");
        contenido.append("\n\t\t\t iconCls : 'x-fa fa-plus-square-o'");
        contenido.append("\n\t\t }, ");
        contenido.append("\n\t\t { ");
        contenido.append("\n\t\t\t text: 'Exportar a Excel', ");
        contenido.append("\n\t\t\t listeners : { click:'onExportExcel'}, ");
        contenido.append("\n\t\t\t iconCls : 'x-fa fa-file-excel-o' ");
        contenido.append("\n\t\t }, ");
        contenido.append("\n\t\t '->', ");
        contenido.append("\n\t\t {");
        contenido.append("\n\t\t\t xtype: 'toolbar',");
        contenido.append("\n\t\t\t ui: 'header',");
        contenido.append("\n\t\t\t docked: 'top',");
        contenido.append("\n\t\t\t fixed: true,");
        contenido.append("\n\t\t\t items: [");
        contenido.append("\n\t\t\t\t {");
        contenido.append("\n\t\t\t\t\t xtype: 'pagingtoolbar',");
        contenido.append("\n\t\t\t\t\t pageSize: " + modulo + ".app.constants.PAGE_SIZE,");
        contenido.append("\n\t\t\t\t\t width: '100%',");
        contenido.append("\n\t\t\t\t\t displayInfo: true,");
        contenido.append("\n\t\t\t\t\t bind: { store: '{" + storeRequire + "}' },");
        contenido.append("\n\t\t\t\t\t plugins: new Ext.ux.ProgressBarPager(),");
        contenido.append("\n\t\t\t\t\t doRefresh: function(){");
        contenido.append("\n\t\t\t\t\t\t this.up().up('panel').getController().loadData();");
        contenido.append("\n\t\t\t\t\t }");
       /* contenido.append("\n\t\t\t\t\t listeners: {");
        contenido.append("\n\t\t\t\t\t\t beforechange: {");
        contenido.append("\n\t\t\t\t\t\t\t fn: 'loadData'");
        contenido.append("\n\t\t\t\t\t\t }");
        contenido.append("\n\t\t\t\t\t }");*/
        contenido.append("\n\t\t\t\t }");
        contenido.append("\n\t\t\t ]");
        contenido.append("\n\t\t }");
        contenido.append("\n \t ] ");
        
        
        //contenido.append("\n\t listeners: {  itemclick: 'onItemSelected' } ");
        contenido.append("\n});");
        
        return contenido.toString();
    }

    public static String createFormulario(String modulo, String fileTocreate, List<ColumnType> list, String prefix) {
    	
        StringBuffer contenido = new StringBuffer();
        String formularioName = fileTocreate + "Window";
        String formularioTitle = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1);
        String viewControllerName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "ViewController";
        String viewModelName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "ViewModel";
        
        
        contenido.append("Ext.define('" + modulo + ".view." + prefix + "." + "view" + "." + formularioName + "', {");
        contenido.append("\n \t extend: 'Ext.window.Window', ");
        contenido.append("\n \t alias: 'widget." + formularioName + "', ");
        contenido.append("\n\t title: '"+formularioTitle+"', \n\t width: 400,");
        contenido.append("\n\n\t requires: [ ");
        contenido.append("\n\t\t '" + modulo + ".view." + prefix + "." + "controller" + "." + viewControllerName + "',");
        contenido.append("\n\t\t '" + modulo + ".view." + prefix + "." + "model" + "." + viewModelName + "'");
        contenido.append("\n\t ],");
        //contenido.append("\n\n\t viewModel: { type: '" + prefix + ".model." + viewModelName + "'},");
        //contenido.append("\n\t controller: '" + prefix + ".controller." + viewControllerName + "',");
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
                    tipo = "'datefield', submitFormat: 'Y-m-d H:i:s', format:'d-m-Y'";
                }
                
                if(columna.isIsprimarykey()){
                	
                	//String blanco =  ", disabled: true";
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
        contenido.append("\n\t\t\t\t {xtype: 'button', text: 'Anular', listeners: { click: 'onDelete'  } },");
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
        StringBuffer dataToExportExcell = new StringBuffer();
        StringBuffer constructArrayExcell = new StringBuffer();
        String viewControllerName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "ViewController";
        String formularioName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "Window";
        String storeRequire = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1);
        String modelName = modulo + ".model." + fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1) + "Model";
        String storeNameVar = "store" + fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1);
        String tablaName = fileTocreate.substring(0, 1).toUpperCase() + fileTocreate.substring(1);
        
        contenido.append("Ext.define('" + modulo + ".view." + prefix + "." + "controller" + "." + viewControllerName + "', {");
        contenido.append("\n \t extend: 'Ext.app.ViewController', ");
        contenido.append("\n \t alias: 'controller." + prefix + "." + "controller" + "." + viewControllerName + "', ");
        
        
        /* init */
        contenido.append("\n\n\t /**");
        contenido.append("\n\t  * Metodo inicializador de ViewController");
        contenido.append("\n\t  */");
        contenido.append("\n\t init: function(view){");
        contenido.append("\n\t\t this.loadData();");
        contenido.append("\n\t },");
        /* init */
        
        /* loadData */
        contenido.append("\n\n\t /**");
        contenido.append("\n\t  * Carga datos a grilla por medio de petición ajax(peticiones sin paginado)");
        contenido.append("\n\t  */");
        contenido.append("\n\t loadData: function(){");
        contenido.append("\n\n\t\t var " + storeNameVar + " = this.getViewModel().getStore('" + storeRequire + "');");
        contenido.append("\n\t\t var viewController = this;");
        contenido.append("\n\t\t Ext.Ajax.request({ ");
        contenido.append("\n\t\t\t url: " + modulo + ".app.constants.URL_ROOT+'/GenadminOp/" + storeRequire.toLowerCase() + "/listAll',");
        contenido.append("\n\t\t\t method: 'GET',");
        contenido.append("\n\t\t\t timeout: Genesis.app.constants.TIMEOUT,");
        contenido.append("\n\t\t\t headers: {'Content-Type' : 'application/json' }, ");
        contenido.append("\n\t\t\t params: {}, ");
        contenido.append("\n\t\t\t success: function(response, opt) {  ");
        contenido.append("\n\t\t\t\t response.responseText = Ext.decode(response.responseText);");
        contenido.append("\n\t\t\t\t " + storeNameVar + ".getProxy().setData(response.responseText);");
        contenido.append("\n\t\t\t\t " + storeNameVar + ".load()");
        contenido.append("\n\t\t\t },");
        contenido.append("\n\t\t\t failure: function(response, opt) { ");
        contenido.append("\n\t\t\t\t " + modulo + ".app.getController('BasController').notifyError('No se pudieron cargar los datos');");
        contenido.append("\n\t\t\t }");
        contenido.append("\n\t\t });");
        contenido.append("\n\n\t }, ");
        /* loadData */

        
        /* newRecord */
        contenido.append("\n\n\t /**");
        contenido.append("\n\t  * Crea un nuevo objecto(record) de la entidad trabajada");
        contenido.append("\n\t  */");
        contenido.append("\n\t newRecord: function(){ ");
        contenido.append("\n\n\t\t Ext.WindowMgr.hideAll();");
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
        contenido.append("\n\n\t }, ");
        /* newRecord */
        
        /* onSeeDetailItem */
        contenido.append("\n\n\t /**");
        contenido.append("\n\t  * muestra en ventana el detalle del objeto");
        contenido.append("\n\t  */");
        contenido.append("\n\t onSeeDetailItem : function(button, e, eOpts) {  ");
        contenido.append("\n\n\t\t var record = button.getWidgetRecord();");
        contenido.append("\n\t\t Ext.WindowMgr.hideAll();");
        contenido.append("\n\t\t var ventana = Ext.widget('" + formularioName + "');");
        contenido.append("\n\t\t ventana.controller = this;");
        contenido.append("\n\t\t ventana.down('form').getForm().loadRecord(record);");
        contenido.append("\n\t\t ventana.show();");
        contenido.append("\n\n\t }, ");
        /* onSeeDetailItem */
        
        /* onItemSelected */
        /*
        contenido.append("\n\n\t onItemSelected : function( grid , record , tr , rowIndex , e , eOpts ){ ");
        contenido.append("\n\t\t Ext.WindowMgr.hideAll();");
        contenido.append("\n\t\t var ventana = Ext.widget('" + formularioName + "');");
        contenido.append("\n\t\t ventana.controller = this;");
        contenido.append("\n\t\t ventana.down('form').getForm().loadRecord(record); ");
        contenido.append("\n\t\t ventana.show(); ");
        contenido.append("\n\t }, ");
        */
        /* onItemSelected */
        
        /* onReset */
        contenido.append("\n\n\t /**");
        contenido.append("\n\t  * Limpia los campos de la ventana");
        contenido.append("\n\t  */");
        contenido.append("\n\t onReset: function(button, e, eOpts) { ");
        contenido.append("\n\n\t\t var ventana = button.up('toolbar').up('form').up('window');");
        contenido.append("\n\t\t ventana.down('form').getForm().reset();");
        contenido.append("\n\t\t ventana.hide();");
        contenido.append("\n\n\t }, ");
        /* onReset */
        
        /* onExportExcel */
        contenido.append("\n\n\t /**");
        contenido.append("\n\t  * Exporta los datos visibles de la grilla a un documento en excel(incluye filtros)");
        contenido.append("\n\t  */");
        contenido.append("\n\t onExportExcel: function(button, e, eOpts) { ");
        contenido.append("\n\n\t\t var " + storeNameVar + " = this.getViewModel().getStore('" + storeRequire + "');");
        contenido.append("\n\t\t var waitModal = "+modulo+".app.getController('BasController').createModal('Exportando a Excel...', button);   ");
        
        contenido.append("\n\t\t var dataToVector = [];");
        
        dataToExportExcell.append("\n\t\t var parameters = {");
        dataToExportExcell.append("\n\t\t\t \"title\": \""+tablaName+"\",");
        dataToExportExcell.append("\n\t\t\t \"headers\": [");
        
        int i=0;
        for(ColumnType col : list){
        	dataToExportExcell.append(" \""+col.getAlias()+"\"");
        	
        	constructArrayExcell.append("\n\t\t\t\t\t "+col.getName()+": "  + storeNameVar + ".data.items[i].data."+col.getName());
        	
        	 if(i < (list.size()-1)){
        		 dataToExportExcell.append(",");
        		 constructArrayExcell.append(",");
        	 }
        	
        	i++;
        }
        
        dataToExportExcell.append(" ],");
        dataToExportExcell.append("\n\t\t\t \"data\": dataToVector");
        dataToExportExcell.append("\n\t\t };");
        
        contenido.append("\n\t\t if(" + storeNameVar + ".data.length>0){");
        contenido.append("\n\t\t\t  var pageSize = " + storeNameVar + ".pageSize; ");
        contenido.append("\n\t\t\t  var totalCount = " + storeNameVar + ".totalCount;");
        contenido.append("\n\t\t\t for (var j = 0; j<((totalCount/pageSize)+1); j++) {");
        contenido.append("\n\t\t\t\t " + storeNameVar + ".loadPage(j);");
        contenido.append("\n\t\t\t\t for(var i=0;i<" + storeNameVar + ".data.length;i++){");
        contenido.append("\n\t\t\t\t\t dataToVector.push({"+constructArrayExcell.toString()+" \n\t\t\t\t });");
        contenido.append("\n\t\t\t\t }");
        contenido.append("\n\t\t\t }");
        contenido.append("\n\t\t }");
        
        
        contenido.append(dataToExportExcell.toString());
        
        contenido.append("\n\n\t\t Ext.Ajax.request({ ");
        contenido.append("\n\t\t\t url: " + modulo + ".app.constants.URL_ROOT+'/GenadminOp/util/getExcel',");
        contenido.append("\n\t\t\t method: 'POST',");
        contenido.append("\n\t\t\t params: Ext.encode(parameters),");
        contenido.append("\n\t\t\t headers: { 'Content-Type' : 'application/vnd.ms-excel' },");
        contenido.append("\n\t\t\t success: function(response, opt) { ");
        contenido.append("\n\t\t\t\t waitModal.hide();");   
        contenido.append("\n\t\t\t\t response.responseText = Ext.decode(response.responseText);");
        contenido.append("\n\t\t\t\t var base64File = response.responseText.archivo;");
        contenido.append("\n\t\t\t\t window.location.href = window.location.href = 'data:application/vnd.ms-excel;base64,'+base64File; ");     

        contenido.append("\n\t\t\t\t " + modulo + ".app.getController('BasController').notifySuccess('Descarga exitosa');");
        
        contenido.append("\n\t\t\t },");
        contenido.append("\n\t\t\t failure: function(response, opt) { ");
        contenido.append("\n\t\t\t\t waitModal.hide();");
        
        contenido.append("\n\t\t\t\t " + modulo + ".app.getController('BasController').notifyError('No se pudieron exportar los datos');");
        
        contenido.append("\n\t\t\t }");
        contenido.append("\n\t\t });");
        contenido.append("\n\n\t }, ");
        /* onExportExcel */
        
        /* onDelete */
        contenido.append("\n\n\t /**");
        contenido.append("\n\t  * Cambia el estado de un objecto en especifico a 'ELIMINADO'");
        contenido.append("\n\t  */");
        contenido.append("\n\t onDelete: function(button, e, eOpts) {  ");
        contenido.append("\n\n\t\t Ext.Msg.confirm('Eliminar','¿Seguro que desea continuar?',function(buttonId, value){");
        contenido.append("\n\n\t\t\t if (buttonId === 'yes'){");
        contenido.append("\n\t\t\t\t var waitModal = "+modulo+".app.getController('BasController').createModal('Procesando...', button);");
        contenido.append("\n\t\t\t\t var " + storeNameVar + " = this.getViewModel().getStore('" + storeRequire + "');");
        contenido.append("\n\t\t\t\t var ventana = button.up('toolbar').up('form').up('window');");
        contenido.append("\n\t\t\t\t var form = ventana.down('form').getForm();");
        contenido.append("\n\t\t\t\t var viewController = this;");
        contenido.append("\n\t\t\t\t Ext.Ajax.request({ ");
        contenido.append("\n\t\t\t\t\t url: " + modulo + ".app.constants.URL_ROOT+'/"+servicio+"/"+tablaName.toLowerCase()+ "/delete',  \n\t\t\t\t\t method: 'DELETE',");
        contenido.append("\n\t\t\t\t\t headers: {'Content-Type' : 'application/json' }, \n\t\t\t\t\t params: Ext.encode({"+recordToDel+"}),  ");
        contenido.append("\n\t\t\t\t\t success: function(response, opt) { ");
        contenido.append("\n\t\t\t\t\t\t button.up('form').up('window').hide();");
        contenido.append("\n\t\t\t\t\t\t waitModal.hide();");
        contenido.append("\n\t\t\t\t\t\t viewController.loadData();");
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
        /* onDelete */
        
        /* onSave */
        contenido.append("\n\n\t /**");
        contenido.append("\n\t  * Guarda un nuevo objeto o actualiza uno existente");
        contenido.append("\n\t  */");
        contenido.append("\n\t onSave: function(button, e, eOpts) { ");
        contenido.append("\n\n\t\t  var waitModal = "+modulo+".app.getController('BasController').createModal('Enviando, espere un momento por favor...', button);");
        contenido.append("\n\t\t  var " + storeNameVar + " = this.getViewModel().getStore('" + storeRequire + "');");
        contenido.append("\n\t\t  var ventana = button.up('toolbar').up('form').up('window');");
        contenido.append("\n\t\t  var form = ventana.down('form').getForm();");
        contenido.append("\n\t\t  var viewController = this;");
        contenido.append("\n\n\t\t if (form.isValid()) {");
        contenido.append("\n\t\t  Ext.Ajax.request({ ");
        contenido.append("\n\t\t\t url :" + modulo + ".app.constants.URL_ROOT+'/"+servicio+"/"+tablaName.toLowerCase()+ "/insert', \n\t\t\t method: 'POST',");
        contenido.append("\n\t\t\t headers: {'Content-Type' : 'application/json' }, \n\t\t\t params:  Ext.encode(form.getValues()), ");
        contenido.append("\n\t\t\t success: function(response, opt) { ");
        contenido.append("\n\t\t\t\t waitModal.hide();");
        contenido.append("\n\n\t\t\t\t button.up('form').up('window').hide();");
        contenido.append("\n\n\t\t\t\t " + modulo + ".app.getController('BasController').notifySuccess('Se ha guardado exitosamente');");
        contenido.append("\n\n\t\t\t\t viewController.loadData();");
        contenido.append("\n\t\t\t }, ");
        contenido.append("\n\t\t\t failure: function(response, opt) {  ");
        contenido.append("\n\t\t\t\t waitModal.hide();");
        contenido.append("\n\t\t\t\t Ext.Msg.alert('Error', response.status); ");
        contenido.append("\n\t\t\t } ");
        contenido.append("\n\t\t  });");
        contenido.append("\n\n\t\t }else{");
        contenido.append("\n\t\t\t " + modulo + ".app.getController('BasController').notifyError('Datos de formulario inválido');");
        contenido.append("\n\n\t\t }");
        contenido.append("\n\t } ");
        /* onSave */
        
        contenido.append("\n});");
        
        return contenido.toString();
    }
}