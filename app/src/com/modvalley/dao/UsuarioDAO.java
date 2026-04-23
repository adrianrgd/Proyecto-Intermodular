package com.modvalley.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.modvalley.config.Conexion;
import com.modvalley.model.Usuario;

public class UsuarioDAO {

    // Para mostrar la lista en el Login
    public ArrayList<Usuario> obtenerTodos() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";

        try (Connection conn = Conexion.conectar();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nickname"),
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Para validar el ID elegido
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM USUARIO WHERE id_usuario = ?";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Usuario(rs.getInt("id_usuario"), rs.getString("nickname"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}