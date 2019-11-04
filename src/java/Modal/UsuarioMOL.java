/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

import Clases.Usuario;
import Conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class UsuarioMOL extends Conexion {

    ResultSet rs;

    public ResultSet IncioSeccion(Usuario usu) {
        String r = "error";
        if (this.Connexion()) {
            String sentencia = "CALL IncioSeccion('" + usu.getUsuario() + "','" + usu.getContra()+"')";
            try {
                rs = consults(sentencia);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioMOL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rs;
    }

}
