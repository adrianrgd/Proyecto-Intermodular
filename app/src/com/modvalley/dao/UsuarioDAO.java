package com.modvalley.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.modvalley.config.Conexion;
import com.modvalley.model.Usuario;
import com.modvalley.Custom;

public class UsuarioDAO {

    // Listado de usuarios para el login
    public ArrayList<Usuario> obtenerTodos() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";

        Connection conn = Conexion.conectar();

        if (conn == null) {
            System.out.println(
                    Custom.ROJO + "Error: No se pudo establecer conexión para listar usuarios." + Custom.RESET);
            return lista;
        }

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nickname"),
                        rs.getString("email"),
                        rs.getDate("fecha_registro"),
                        rs.getString("biografia"),
                        rs.getString("foto_perfil")));
            }
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM USUARIO WHERE id_usuario = ?";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nickname"),
                        rs.getString("email"),
                        rs.getDate("fecha_registro"),
                        rs.getString("biografia"),
                        rs.getString("foto_perfil"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ActualizarNickname(int id, String nuevoNickname) {
        String sql = "UPDATE USUARIO SET nickname = ? WHERE id_usuario = ?";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nuevoNickname);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ActualizarFoto(int id, String nuevaFoto) {
        String sql = "UPDATE USUARIO SET foto_perfil = ? WHERE id_usuario = ?";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nuevaFoto);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ActualizarBio(int id, String nuevaBio) {
        String sql = "UPDATE USUARIO SET biografia = ? WHERE id_usuario = ?";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nuevaBio);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM USUARIO WHERE id_usuario = ?";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                System.out.println(Custom.VERDE + "> Usuario eliminado de la BBDD." + Custom.RESET);
            }
        } catch (SQLException e) {
            System.out.println(Custom.ROJO + "Error SQL: " + e.getMessage() + Custom.RESET);
        }
    }

    public void registrarUsuario(String nickname, String email, String biografia) {
        // Si la bio está vacía, no la incluimos en el INSERT para que MySQL use el
        // DEFAULT
        String sql;
        boolean tieneBio = (biografia != null && !biografia.trim().isEmpty());

        if (tieneBio) {
            sql = "INSERT INTO USUARIO (nickname, email, biografia) VALUES (?, ?, ?)";
        } else {
            sql = "INSERT INTO USUARIO (nickname, email) VALUES (?, ?)";
        }

        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nickname);
            pstmt.setString(2, email);
            if (tieneBio) {
                pstmt.setString(3, biografia);
            }

            pstmt.executeUpdate();
            System.out.println(Custom.VERDE + "> ¡Registro completado con éxito!" + Custom.RESET);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Código de error para duplicados (email)
                System.out.println(Custom.ROJO + "> Error: El email ya está registrado." + Custom.RESET);
            } else {
                e.printStackTrace();
            }
        }
    }
}