/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alelo.model.dao;
import br.com.alelo.bd.connection;
import br.com.alelo.model.bean.Projects;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD
 * @author atomic
 */
public class ProjectsDao {
    
    private Connection conn = null;

    public ProjectsDao() {
        conn = connection.getConnection();
    }
    
    public boolean AddProject(Projects project){

        String sql = "INSERT INTO projects (proj_author, proj_name) VALUES (?, ?)";
        PreparedStatement stmt = null;
        
        try {
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, project.getAuthor());
            stmt.setString(2, project.getName());
            stmt.executeUpdate();
            getCurrentProjectId(project);
            return true;
            
        } catch (SQLException ex) {
            System.err.println("Não foi possível criar o projeto " + project.getName() + ex);
            return false;  
        } finally {
            connection.closeConnection(conn, stmt);
        }
        
    } 
    
    public List<Projects> getAll(){
        
        String sql = "SELECT * FROM projects";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Projects> projects = new ArrayList<>();
        
        try {
            
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                Projects project = new Projects();
                project.setId(rs.getInt("proj_id"));
                project.setAuthor(rs.getString("proj_author"));
                project.setName(rs.getString("proj_name"));
                projects.add(project);
            }
                
        } catch (SQLException ex) {
            System.err.println("Não foi possível obter a lista de projetos");
        } finally {
            connection.closeConnection(conn, stmt, rs);
        }
        
        return projects;
    }
    
    public void getCurrentProjectId(Projects project){
        
        String sql = "SELECT * FROM projects WHERE proj_name = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, project.getName());
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                project.setId(rs.getInt("proj_id"));
            }
                
        } catch (SQLException ex) {
            System.err.println("Não foi possível obter o id do projeto " + project.getName() + ex);
        } finally {
            connection.closeConnection(conn, stmt, rs);
        }
    }
}
