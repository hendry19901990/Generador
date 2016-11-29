package com.extjs.generador;

import com.extjs.generador.ColumnType;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilDatabase {
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@10.0.0.94:1521:KPITALUP";
    private static final String DB_USER = "genesis";
    private static final String DB_PASSWORD = "Akc123456";

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
        ArrayList<ColumnType> list = null;
        Connection dbConnection = null;
        Statement statement = null;
        String selectTableSQL = "select COLUMN_NAME, DATA_TYPE, DATA_LENGTH, DATA_SCALE, NULLABLE from ALL_TAB_COLUMNS where TABLE_NAME = upper('" + tabla + "')";
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
                ColumnType columnType = new ColumnType(name, type, data_length, data_scale, isnull);
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