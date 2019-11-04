/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Clases.Usuario;
import Modal.UsuarioMOL;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lucas
 */
public class Registrar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            Usuario usu = new Usuario();
            UsuarioMOL usua = new UsuarioMOL();
            ResultSet rs;
            usu.setUsuario(request.getParameter("Usuario"));
            usu.setContra(request.getParameter("Contra"));
            rs = usua.IncioSeccion(usu);
            HttpSession sesion = request.getSession();
            while (rs.next()) {
                try {
                    sesion.setAttribute("Id", rs.getString("Id"));
                    sesion.setAttribute("Nombre", rs.getString("Nombre"));
                    sesion.setAttribute("Usuario", rs.getString("Usuario"));
                    sesion.setAttribute("Contra", rs.getString("Contra"));
                    sesion.setAttribute("Nrol", rs.getString("Rol"));
                    out.print("ok");
                    out.flush();
                } catch (SQLException ex) {
                    Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
                    out.print("error");
                    out.flush();
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
            out.print("error");
            out.flush();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
