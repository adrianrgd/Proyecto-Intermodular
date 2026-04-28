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

    // Validación del ID elegido
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
}