/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

import Clases.Curos;
import Clases.Usuario;
import Conexion.Conexion;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class CursosMOL extends Conexion {

    ResultSet rs;

    public ResultSet ConsultarC(String a) {
        String sentencia;
        if (Connexion()) {
            sentencia = "call ColsutarC()";
            try {
                rs = consults(sentencia);
            } catch (SQLException ex) {
                Logger.getLogger(CursosMOL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rs;
    }
    public ResultSet ConsultarVoto(String a, String b) {
        String sentencia;
        if (Connexion()) {
            sentencia = "call NoVotar("+a+","+b+")";
            try {
                rs = consults(sentencia);
            } catch (SQLException ex) {
                Logger.getLogger(CursosMOL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rs;
    }
    public ResultSet usuarios() {
        String sentencia;
        if (Connexion()) {
            sentencia = "call usuarios()";
            try {
                rs = consults(sentencia);
            } catch (SQLException ex) {
                Logger.getLogger(CursosMOL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rs;
    }
    public ResultSet Votantesfaltante(String a) {
        String sentencia;
        if (Connexion()) {
            sentencia = "call ConsultaVotantesfalatantes("+a+")";
            try {
                rs = consults(sentencia);
            } catch (SQLException ex) {
                Logger.getLogger(CursosMOL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rs;
    }
    public ResultSet NumeroVotante() {
        String sentencia;
        if (Connexion()) {
            sentencia = "call 	ColsutarC()";
            try {
                rs = consults(sentencia);
            } catch (SQLException ex) {
                Logger.getLogger(CursosMOL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rs;
    }
    public ResultSet ConsultaAll(String a) {
        String sentencia;
        if (Connexion()) {
            sentencia = "call consultarcursos()";
            try {
                rs = consults(sentencia);
            } catch (SQLException ex) {
                Logger.getLogger(CursosMOL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rs;
    }
    public ResultSet CursoId(String a) {
        String sentencia;
        if (Connexion()) {
            sentencia = "call CursoId("+a+")";
            try {
                rs = consults(sentencia);
            } catch (SQLException ex) {
                Logger.getLogger(CursosMOL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rs;
    }

    public String Imagen(String a, String b) {
       
        PreparedStatement pst;
        String respuesta = "error";
        String sentencia = "UPDATE cusrsos SET Imagen  = ? WHERE usuario = ?; ";

        if (this.Connexion()) {
            try {
                pst = cnn.prepareStatement(sentencia);
                pst.setString(1,a);
                pst.setString   (2, b);               
                if (pst.executeUpdate() != 0) {
                    pst.close();
                    respuesta = "ok";

                }       

            } catch (SQLException ex) {
                Logger.getLogger(CursosMOL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return respuesta;
    }
     public String RegistrarVoto(Curos c) {
        PreparedStatement pst;      
        String respuesta = "error";
        String sentencia = "INSERT INTO `usuariocurso`(`IdUsuario`, `IdCurso`, `Voto`) VALUES (?,?,?)";

        if (this.Connexion()) {
            try {
                pst = cnn.prepareStatement(sentencia);
                pst.setString(1, c.getUsuario());
                pst.setInt(2, c.getId());
                pst.setString(3, c.getVotar());               
                if (pst.executeUpdate() != 0) {
                    pst.close();
                    respuesta = "ok";

                }

                
               
                

            } catch (SQLException ex) {
                Logger.getLogger(CursosMOL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return respuesta;

    }

    
}
