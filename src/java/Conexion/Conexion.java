/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lucas
 */
public class Conexion {
    protected Connection cnn = null;
    private PreparedStatement pst = null;
    public ResultSet rs = null;
    private String user = "root";
    private String pass = "";
    private String bd = "juvenils";
    private String url = "jdbc:mysql://localhost:3306/" + bd;

    public boolean Connexion(){
        boolean b = false;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            cnn = DriverManager.getConnection(url, user, pass);
            if (cnn != null) {
                b = true;
            }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return b;
    }
    
    public ResultSet consults(String query) throws SQLException{
        
        if (Connexion()) {
            pst = cnn.prepareStatement(query);
            rs = pst.executeQuery();
            
        }
        
        return rs;
    }
    
    public PreparedStatement sentences (String sentence) throws SQLException{
        
        if (Connexion()) {
            pst = cnn.prepareStatement(sentence);
            pst.executeUpdate(sentence);
            
        }
        return pst;
    }
    
}
