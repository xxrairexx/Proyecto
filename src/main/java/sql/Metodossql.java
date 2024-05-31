/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Random;

/**
 *
 * @author xxrai
 */

public class Metodossql {

    private Connection conexion;
    private PreparedStatement sentenciaPreparada;
    private ResultSet resultado;

    public boolean resgitararUsuario(String logiUsua, String passUsua, String nombUsua) {
        boolean registro = false;

        try {
            // Conectar a la base de datos
            conexion = ConexionBD.conectar();
            String consulta = "INSERT INTO usuario (logiUsua, passUsua, nombUsua) VALUES (?, ?, ?)";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            sentenciaPreparada.setString(1, logiUsua);
            sentenciaPreparada.setString(2, passUsua);
            sentenciaPreparada.setString(3, nombUsua);

            int resultadoInsercion = sentenciaPreparada.executeUpdate();

            if (resultadoInsercion > 0) {
                registro = true;
                System.out.println("Se agregro el usurio");
            } else {
                registro = false;
                System.out.println("No se agregro el usuario");
            }

        } catch (SQLException e) {

            System.out.println("Error SQL: " + e.getMessage());
        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
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

        System.out.println("Valor del registro: " + registro);
        return registro;
    }

    public boolean buscarUsuarioRepetidoBD(String logiUsua) {
        boolean usuarioRepetido = false;

        try {
            conexion = ConexionBD.conectar();
            String consulta = "SELECT logiUsua FROM usuario WHERE logiUsua = ?";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            sentenciaPreparada.setString(1, logiUsua);
            resultado = sentenciaPreparada.executeQuery();

            if (resultado.next()) {
                usuarioRepetido = true; // El usuario esta registrado en la BD
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultado != null)
                    resultado.close();
                if (sentenciaPreparada != null)
                    sentenciaPreparada.close();
                if (conexion != null)
                    conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("El usurio esta " + usuarioRepetido);
        return usuarioRepetido;
    }

    public boolean buscarcontrasena(String passUsua) {
        boolean password = false;

        try {
            conexion = ConexionBD.conectar();
            String consulta = "SELECT passUsua FROM usuario WHERE passUsua = ?";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            sentenciaPreparada.setString(1, passUsua);
            resultado = sentenciaPreparada.executeQuery();

            if (resultado.next()) {
                password = true; // El usuario esta registrado en la BD
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultado != null)
                    resultado.close();
                if (sentenciaPreparada != null)
                    sentenciaPreparada.close();
                if (conexion != null)
                    conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("contraseña: " + passUsua);
        System.out.println("El usurio pudo iniciar sesion? " + password);

        return password;
    }

    public int Codigo() {

        int max = 999999;
        int min = 100000;
        Random random = new Random();
        int numero = random.nextInt(max - min + 1) + min;
        System.out.println("el codigo es: " + numero);
        return numero;

    }
    
    public boolean CambiarPassword(String email, String nuevaPassword) {
        boolean cambio = false;
    
        try {
            conexion = ConexionBD.conectar();
            String consulta = "UPDATE usuario SET passUsua = ? WHERE logiUsua = ?";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            sentenciaPreparada.setString(1, nuevaPassword);
            sentenciaPreparada.setString(2, email);
            
            int filasActualizadas = sentenciaPreparada.executeUpdate();
            if (filasActualizadas > 0) {
                cambio = true; // La contraseña se actualizó exitosamente
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sentenciaPreparada != null)
                    sentenciaPreparada.close();
                if (conexion != null)
                    conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("la contraseña cambio: " + cambio);
    
        return cambio;
    }

}
