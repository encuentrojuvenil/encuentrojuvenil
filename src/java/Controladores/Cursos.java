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
public class Cursos extends HttpServlet {

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
        HttpSession sesion = (HttpSession) request.getSession();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ResultSet rs, rs2;
            CursosMOL cu = new CursosMOL();
            String id = (String) sesion.getAttribute("Id"), a = null;
            String Rol = (String) sesion.getAttribute("Nrol");
            rs = cu.ConsultaAll(id);
            out.print("<div class='row'>");
            try {
                while (rs.next()) {

                    if (!rs.getString("Usuario").equals((String) sesion.getAttribute("Id"))) {
                        if (rs.getString("Imagen") != null) {
                            rs2 = cu.ConsultarVoto((String) sesion.getAttribute("Id"), rs.getString("Id"));
                            if (!rs2.next()) {
                                out.print("<div class='col-4'>");
                                out.print("<input value='" + rs.getString("Id") + "' disabled=\"\" type=\"hidden\" id=\"IdCurso\">");
                                out.print("<button class='boton' data-toggle='modal' data-target='#exampleModalLong" + rs.getString("Id") + "' ><h>" + rs.getString("Nombre") + "</h>");
                                out.print("</button>");
                                if (Rol.equals("1")) {
                                    out.print("<button  onclick='v(" + rs.getString("Id") + ")' type=\"button\"  class=\"btn btn-primary\">Votos</button>");
                                    out.print("<input type=\"hidden\" value='" + rs.getString("Id") + "' id=\"IdCurso\">");
                                }
                                if (Rol.equals("1")) {
                                    out.print("<div id='pp'>");
                                    out.print("</div>");
                                }

                                out.print("</div>");

                                out.print("<div class=\"modal fade\" id='exampleModalLong" + rs.getString("Id") + "' tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\" aria-hidden=\"true\">");
                                out.print("<div class=\"modal-dialog\" role=\"document\">");
                                out.print("<div class=\"modal-content\">");
                                out.print("<div class=\"modal-header\">");
                                out.print("<h5 class=\"modal-title\" id=\"exampleModalLongTitle\">" + rs.getString("Nombre") + "</h5>");
                                out.print("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                                        + "          <span aria-hidden=\"true\">&times;</span>\n"
                                        + "        </button>");
                                out.print("</div>");
                                out.print("<div class=\"modal-body\">");
                                out.print("<div class=\"form-row\">");

                                out.print("<img class='imaa' src='Imagen/Cursos/" + rs.getString("Imagen") + "'>");
                                out.print("</div>");
                                if (!Rol.equals("1")) {
                                    out.print("<div class=\"form-row\">");
                                    out.print("<div class=\"form-group col-md-8\">");
                                    out.print("<h5>Voto</h5>    ");
                                    out.print("<select class='selectpicker form-control' id='voto'>\n"
                                            + "                <option disabled selected='' value=''>....</option>\n"
                                            + "                <option value='1'>1</option>\n"
                                            + "                <option value='2'>2</option>\n"
                                            + "                <option value='3'>3</option>\n"
                                            + "                <option value='4'>4</option>\n"
                                            + "                <option value='5'>5</option>\n"
                                            + "            </select>");

                                    out.print("</div>");
                                    out.print("</div>");
                                }
                                out.print("</div>");

                                out.print(" <div class=\"modal-footer\">\n"
                                        + "        <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Cerrar</button>\n");
                                if (!Rol.equals("1")) {
                                    out.print("        <button type=\"button\" onclick='votar()' class=\"btn btn-primary\">Votar</button>\n");
                                }
                                out.print("</div>");
                                out.print("</div>");
                                out.print("</div>");
                                out.print("</div>");
                            } else {
                                out.print("<div class='col-4'>");
                                out.print("<button class='boton2' disabled><h7>" + rs.getString("Nombre") + "</h7>");
                                out.print("</button>");
                                out.print("</div>");
                            }
                        } else {
                            out.print("<div class='col-4'>");
                            out.print("<button class='boton2' disabled><h7>" + rs.getString("Nombre") + "</h7>");
                            out.print("</button>");
                            out.print("</div>");
                        }

                    }

                }
                out.print("</div>");
            } catch (SQLException ex) {
                Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
            }
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
