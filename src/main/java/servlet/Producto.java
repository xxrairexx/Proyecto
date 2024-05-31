/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import sql.ConexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author xxrai
 */
@WebServlet(name = "Producto", urlPatterns = { "/ProductInfo" })

public class Producto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConexionBD.conectar();

            String sql = "SELECT nombre, precio, descuento FROM productos WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 2); // Cambia este valor según tu necesidad
            resultSet = statement.executeQuery();

            JSONObject jsonResponse = new JSONObject();

            if (resultSet.next()) {
                String productName = resultSet.getString("nombre");
                double price1 = resultSet.getDouble("precio");
                double price2 = resultSet.getDouble("descuento");

                jsonResponse.put("productName", productName);
                jsonResponse.put("price1", price1);
                jsonResponse.put("price2", price2);
            } else {
                // Si no se encuentra el producto, establece un mensaje de error
                jsonResponse.put("error", "No se encontró el producto con el ID proporcionado.");
            }

            out.print(jsonResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
            // Si ocurre algún error, envía una respuesta de error al cliente
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Se produjo un error al procesar la solicitud.");
            out.print(errorResponse.toString());
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
            }
            out.close();
        }
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
