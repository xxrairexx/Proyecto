/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import sql.Metodossql;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author xxrai
 */
public class Recuperar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Metodossql metodos = new Metodossql();
        HttpSession session = request.getSession();
        String emaiRecu = request.getParameter("emaiRecu");
        String mensaje = "";
        String tipoMensaje = "";
        int codigoGenerado = obtenerCodigoGenerado(session); // Obtener el código generado de la sesión
        boolean generarCodigo = request.getParameter("accion") != null
                && request.getParameter("accion").equals("generarCodigo");

        if (metodos.buscarUsuarioRepetidoBD(emaiRecu)) {

            if (generarCodigo) {
                codigoGenerado = metodos.Codigo();
                mensaje = "El código fue generado";
                tipoMensaje = "success";
                session.setAttribute("mensaje", mensaje);
                session.setAttribute("tipoMensaje", tipoMensaje);
                session.setAttribute("codigoGenerado", codigoGenerado);
                response.sendRedirect("Recuperar.html");
                return;
            }

            String codiConfStr = request.getParameter("codiConf");
            int codiConf = Integer.parseInt(codiConfStr);
            String passNew = request.getParameter("passNew");
            String passConfirm = request.getParameter("passConfirm");

            if (codiConfStr != null && passNew != null && passConfirm != null) {

                if (codigoGenerado != 0 && codiConf == codigoGenerado) {

                    if (passNew.equals(passConfirm)) {

                        metodos.CambiarPassword(emaiRecu, passConfirm);
                        mensaje = "La contraseña fue cambiada exitosmente!";
                        tipoMensaje ="success";

                    } else {
                        mensaje = "La contraseña no coincide";
                        tipoMensaje = "error";
                    }
                } else {
                    mensaje = "El código de confirmación es incorrecto, generalo nuevamente";
                    tipoMensaje = "error";
                }

            } else {

                mensaje = "Porfavor rellene todos los campos para poder cambia su contraseña";
                tipoMensaje = "error";

            }

        } else {

            mensaje = "El usuario no está registrado";
            tipoMensaje = "error";
        }   

        session.setAttribute("mensaje", mensaje);
        session.setAttribute("tipoMensaje", tipoMensaje);
        response.sendRedirect("Recuperar.html");
    }

    // Método para obtener el código generado de la sesión
    private int obtenerCodigoGenerado(HttpSession session) {
        Integer codigoGenerado = (Integer) session.getAttribute("codigoGenerado");
        return codigoGenerado != null ? codigoGenerado : 0;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
