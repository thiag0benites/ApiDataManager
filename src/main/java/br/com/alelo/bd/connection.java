/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alelo.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author atomic
 */
public class connection {
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //private static final String URL = "jdbc:mysql://localhost/agenda?useTimezone=true&serverTimezone=UTC&user=root&password=root";
    private static final String URL = "jdbc:mysql://localhost/testapi?useTimezone=true&serverTimezone=UTC&user=root";
    //private static final String USER = "root";
    //private static final String PASS = "";
    
    public static Connection getConnection(){
        
        try{
            
          Class.forName(DRIVER);
          return DriverManager.getConnection(URL);
          
        } catch (ClassNotFoundException | SQLException ex){
            throw new RuntimeException("Error na conexão", ex);
        }
        
    }
    
    public static void closeConnection(Connection conn){
        
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }    
        }
        
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt){
        
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }    
        }
        
        closeConnection(conn);
        
    }
    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs){
        
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }    
        }
        
        closeConnection(conn, stmt);
        
    }

}
