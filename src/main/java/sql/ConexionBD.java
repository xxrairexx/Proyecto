/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;

/**
 *
 * @author xxrai
 */

public class ConexionBD {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/tienda";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    public static Connection conectar() {
        Connection conexion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return conexion;
    }

  /*   public static void main(String[] args) {
        Connection conexion = null;
        PreparedStatement sentenciaPreparada = null;

        try {
            conexion = conectar();
            if (conexion != null) {
                String consulta = "INSERT INTO usuario (logiUsua, passUsua, nombUsua) VALUES ('Ja123r@gmail.com', 'Jaider', 'Jaider')";
                sentenciaPreparada = conexion.prepareStatement(consulta);
                int i = sentenciaPreparada.executeUpdate();

                if (i > 0) {
                    System.out.println("Se guardó");
                } else {
                    System.out.println("No se pudo guardar");
                }
            } else {
                System.out.println("No se pudo establecer la conexión");
            }
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        } finally {
            try {
                if (sentenciaPreparada != null) {
                    sentenciaPreparada.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    */
}
