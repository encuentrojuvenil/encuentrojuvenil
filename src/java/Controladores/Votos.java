/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modal.CursosMOL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class Votos extends HttpServlet {

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
            ResultSet rs, rs2;
            CursosMOL cu = new CursosMOL();
            HttpSession sesion = (HttpSession) request.getSession();
            String Curso = request.getParameter("curso");
            int f = 0, c = 0;
            float r = 0;
            ArrayList cars = new ArrayList();
            rs = cu.NumeroVotante();
            out.print("<div class='row'>");
            try {
                while (rs.next()) {

                    f = rs.getInt("VId") - 1;
                }
                rs = cu.Votantesfaltante(Curso);
                while (rs.next()) {
                    c++;
                    cars.add(rs.getString("Id"));
                    r = r + rs.getInt("Voto");
                }
                rs2 = cu.usuarios();
                out.print("<h3>Personas faltantes por votar</h3>\n");
                for (int i = 0; i <= cars.size(); i++) {
                    while (rs2.next()) {
                            if (rs2.getString("Id") != null) {
                            if (!rs2.getString("curso").equals(Curso)) {                                
                                if (cars.size() == 0 || i<cars.size()) {
                                    if (cars.size() == 0 || !rs2.getString("Id").equals(cars.get(i))) {

                                        out.print("<label>" + rs2.getString("Nombre") + " ,  </label>");
                                    } else {
                                        i = i + 1;
                                    }
                                } else {
                                    out.print("<label>" + rs2.getString("Nombre") + " ,  </label>");
                                }
                            } else {
                                i = i;
                            }
                        }
                    }
                }
                if(cars.size() == 0){
                    while (rs2.next()) {
                         out.print("<label>" + rs2.getString("Nombre") + " ,  </label>");
                    }
                }
                r = (float) (r / f);
                out.print("<label>" + c + " de " + f + " personas</label>");
                out.print("<h3>Voto</h3><br>");
                out.print("<label>" + r + "</label><br><br><br>");

            } catch (SQLException ex) {
                Logger.getLogger(Votos.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.print("</div>");
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
