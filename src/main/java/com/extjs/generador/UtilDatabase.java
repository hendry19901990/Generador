package com.extjs.generador;

import com.extjs.generador.ColumnType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilDatabase {
	/*
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@10.0.0.94:1521:KPITALUP";
    private static final String DB_USER = "genesis";
    private static final String DB_PASSWORD = "Akc123456";
    */

    public static List<String> all_tables(String prefix_tabla) {
        ArrayList<String> list = null;
        Connection dbConnection = null;
        Statement statement = null;
        Pattern p = Pattern.compile("[a-zA-Z]+[_][a-zA-Z]+");
        Matcher m = p.matcher(prefix_tabla);
        String selectTableSQL = m.matches() ? "select TABLE_NAME name from ALL_ALL_TABLES where TABLE_NAME='" + prefix_tabla + "'" : "select TABLE_NAME name from ALL_ALL_TABLES where TABLE_NAME like '" + prefix_tabla + "%'";
        try {
            dbConnection = UtilDatabase.getDBConnection();
            if (dbConnection == null) {
                return null;
            }
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<String>();
                }
                list.add(rs.getString("name"));
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        catch (SQLException e) {
            list = null;
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static List<ColumnType> getListColumnTypes(String tabla) {
    	
    	List<ColumnType> list = null;
    	List<ColumnType> listResult = null;
        Connection dbConnection = null;
        Statement statement = null;
        String selectTableSQL = "select COLUMN_ID, COLUMN_NAME, DATA_TYPE, DATA_LENGTH, DATA_SCALE, NULLABLE from ALL_TAB_COLUMNS where TABLE_NAME = upper('" + tabla + "') ORDER BY COLUMN_ID";
       
        try {
            
        	dbConnection = UtilDatabase.getDBConnection();
            
            if (dbConnection == null) {
                return null;
            }
            
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            
            while (rs.next()) {
            	
                if (list == null) {
                    list = new ArrayList<ColumnType>();
                }
                
                String name = rs.getString("COLUMN_NAME");
                String type = rs.getString("DATA_TYPE");
                String data_lengthString = rs.getString("DATA_LENGTH");
                String data_scaleString = rs.getString("DATA_SCALE");
                int data_length = data_lengthString != null ? Integer.parseInt(data_lengthString) : 0;
                int data_scale = data_scaleString != null ? Integer.parseInt(data_scaleString) : 0;
                String nullable = rs.getString("NULLABLE");
                boolean isnull = !nullable.equalsIgnoreCase("N") && !nullable.contains("N");
                ColumnType columnType = new ColumnType(rs.getInt("COLUMN_ID"), name, type, data_length, data_scale, isnull, false);
                list.add(columnType);
                
            }
            
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        catch (SQLException e) {
            list = null;
            System.out.println(e.getMessage());
        }
        
        listResult = getListColumnTypesWithPrimaryKey(list, tabla);
        
        return listResult;
    }
    
    private static List<ColumnType> getListColumnTypesWithPrimaryKey(List<ColumnType> lista, String tabla) {
    	
    	if(lista == null || lista.size()==0){
    		return null;
    	}else{
    		
    		Map<String,ColumnType> existenteLista = new HashMap<String,ColumnType>();
    		
    		for(ColumnType col : lista)
    			existenteLista.put(col.getName(), col);
    		
    		List<ColumnType> listPK = getListPrimaryKey(tabla);
    		
    		if(listPK != null){
    			
    			for(ColumnType col : listPK){
    				
    				if(existenteLista.containsKey(col.getName())){
    					ColumnType colAUX = existenteLista.get(col.getName());
    					colAUX.setIsprimarykey(true);
    					existenteLista.put(col.getName(), colAUX);
    					break;
    				}
    				
    			}
    			
    		}
    		
    		List<GmaAlias> listaAlias = listAlias(tabla);
    		
    		if(listaAlias != null){
    			
    			for(GmaAlias gm : listaAlias){
    				
    				if(existenteLista.containsKey(gm.getAliacamp().toLowerCase())){
    					
    					ColumnType colAUX = existenteLista.get(gm.getAliacamp().toLowerCase());
    					colAUX.setAlias(gm.getAliaalia().toLowerCase());
    					existenteLista.put(gm.getAliacamp().toLowerCase(), colAUX);
    					
    				}
    				
    			}
    			
    		}
    		
        	List<ColumnType> list = new ArrayList<ColumnType>(existenteLista.values()); 
        	
        	Collections.sort(list, new Comparator<ColumnType>() {
        		
        		public int compare(ColumnType colA, ColumnType colB) {
        			
        			return colA.getColumn_id().compareTo(colB.getColumn_id());
        			
        		}
        		
        	});
        	
        	return list;
    		
    	}
    	

    }
    
    private static List<ColumnType> getListPrimaryKey(String tabla) {
    	
    	List<ColumnType> list = null;
        Connection dbConnection = null;
        Statement statement = null;
        String selectTableSQL = " SELECT cols.table_name, cols.column_name column_name, cols.position, cons.status, cons.owner ";
        selectTableSQL += " FROM all_constraints cons, all_cons_columns cols WHERE cons.constraint_type = 'P' AND cons.constraint_name = cols.constraint_name ";
        selectTableSQL += " AND cons.owner = cols.owner AND cols.table_name= '" + tabla.toUpperCase() + "' ";
        selectTableSQL += " ORDER BY cols.table_name, cols.position ";
        
        try {
            dbConnection = UtilDatabase.getDBConnection();
            if (dbConnection == null) {
                return null;
            }
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<ColumnType>();
                }
                String name = rs.getString("column_name");
                boolean isnull = false;
                ColumnType columnType = new ColumnType(0,name, null, 0, 0, isnull, true);
                list.add(columnType);
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        catch (SQLException e) {
            list = null;
            System.out.println(e.getMessage());
        }
        
        return list;
    	
    }
    
    private static List<GmaAlias> listAlias(String tabla){
    	
    	 List<GmaAlias> listaAlias = null;
    	 String[] output = tabla.split("\\_");
         String prefix = output[0];
         String modulo = output[1];
         
         String urlTo = "http://10.0.0.131/GenadminOp/gmaalias/findByAliatabl/" + ( prefix.toLowerCase()+modulo.toLowerCase());
         
         try{
           
        	 URL obj = new URL(urlTo);
 		     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 		     con.setRequestMethod("GET");
 		     
 		    BufferedReader in = new BufferedReader(
 			        new InputStreamReader(con.getInputStream()));
 		     
 		     if(con.getResponseCode() == 200){
 		    	String inputLine;
 				StringBuffer response = new StringBuffer();

 				while ((inputLine = in.readLine()) != null) {
 					response.append(inputLine);
 				}
 				
 				System.out.println(response.toString());
 				Gson gson = new Gson(); 
 	 		    listaAlias = gson.fromJson(response.toString(), new TypeToken<List<GmaAlias>>(){}.getType());
 	 		   
 		     }
 		     
 		    in.close();
 		   
         }catch(Exception e){
        	 System.out.println(e.getMessage());
         }
    	
    	return listaAlias;
    	
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@10.0.0.94:1521:KPITALUP", "genesis", "Akc123456");
            return dbConnection;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return dbConnection;
        }
    }
}