/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modal.CursosMOL;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Lucas
 */
public class Imagen extends HttpServlet {

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
            CursosMOL M = new CursosMOL();
            String NombreValidar = "";
            String Nombre = "";
            String ruta = "";
            ResultSet rs;
            HttpSession sesion = request.getSession();
            String usuario = (String) sesion.getAttribute("Id");
            rs = M.CursoId(usuario);

            try {
                while (rs.next()) {
                    if (rs.getString("Id") != null || !rs.getString("Id").equals("")) {
                        if (rs.getString("Imagen") == null) {
                            if (ServletFileUpload.isMultipartContent(request)) {
                                try {

                                    List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                                    for (FileItem item : multiparts) {
                                        if (!item.isFormField()) {
                                            NombreValidar = item.getName();
                                            String[] NombreValidar2 = NombreValidar.split("\\.");
                                            if (NombreValidar2[1].equals("PNG") || NombreValidar2[1].equals("jpg")) {
                                                Nombre = "FotoConsurso" + sesion.getAttribute("Id") + ".jpg";
                                                ruta = "C:\\Users\\Lucas\\Documents\\NetBeansProjects\\Encuentro juvenix\\web\\Imagen\\Cursos\\";
                                            }
                                            File archivo = new File(ruta + Nombre);
                                            if (archivo.exists()) {
                                                archivo.delete();
                                            }
                                            try {
                                                item.write(new File(ruta + Nombre));
                                                M.Imagen(Nombre, usuario);
                                                out.print("ok");
                                                out.flush();
                                            } catch (Exception ex) {
                                                Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
                                                out.print("error");
                                                out.flush();
                                            }
                                        }
                                    }

                                } catch (FileUploadException ex) {
                                    Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
                                    out.print("error");
                                    out.flush();
                                }

                            }
                        } else {
                            out.print("error3");
                            out.flush();
                        }
                    } else {
                        out.print("error2");
                        out.flush();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
                out.print("error2");
                out.flush();
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
