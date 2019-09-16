/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alelo.model.dao;

import br.com.alelo.bd.connection;
import br.com.alelo.model.bean.Features;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD
 * @author atomic
 */
public class FeaturesDao{
    
    private Connection conn = null;

    public FeaturesDao() {
        conn = connection.getConnection();
    }
    
    public boolean AddFeature(Features feature){
        
        String sql = "INSERT INTO features (feature_name, proj_id) VALUES (?, ?)";
        PreparedStatement stmt = null;
        
        try {
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, feature.getName());
            stmt.setInt(2, feature.getProjId());
            stmt.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            System.err.println("Não foi possível criar a funcionalidade " + feature.getName() + ex);
            return false;  
        } finally {
            connection.closeConnection(conn, stmt);
        }       
        
    }
    
    public List<Features> getAll(){
        
        String sql = "SELECT * FROM features";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Features> features = new ArrayList<>();
        
        try {
            
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                Features feature = new Features();
                feature.setName(rs.getString("feature_name"));
                features.add(feature);
            }
                
        } catch (SQLException ex) {
            System.err.println("Não foi possível obter a lista de funcionalidades");
        } finally {
            connection.closeConnection(conn, stmt, rs);
        }
        
        return features;
    }
}
