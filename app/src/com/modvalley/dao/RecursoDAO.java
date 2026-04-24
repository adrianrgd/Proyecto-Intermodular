package com.modvalley.dao;

import com.modvalley.config.Conexion;
import com.modvalley.model.Recurso;
import java.sql.*;
import java.util.ArrayList;

public class RecursoDAO {

    // Lista mods por juego
    public ArrayList<Recurso> getRecursos(int idVideojuego) {
        ArrayList<Recurso> lista = new ArrayList<>();
        String sql = "SELECT * FROM RECURSO WHERE id_videojuego = ?";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idVideojuego);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                lista.add(new Recurso(
                        rs.getInt("id_recurso"),
                        rs.getString("nombre_rec"),
                        rs.getString("descripcion"),
                        rs.getString("version"),
                        rs.getInt("num_descargas"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_videojuego"),
                        rs.getInt("id_categoria")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;

    }

    public void incrementarDescarga(int idMod) {
        String sql = "UPDATE RECURSO SET num_descargas = num_descargas + 1 WHERE id_recurso = ?";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idMod);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertar(Recurso r) {
        String sql = "INSERT INTO RECURSO (nombre_rec, descripcion, version, id_usuario, id_videojuego, id_categoria) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, r.getNombre());
            pstmt.setString(2, r.getDescripcion());
            pstmt.setString(3, r.getVersion());
            pstmt.setInt(4, r.getIdUsuario());
            pstmt.setInt(5, r.getIdVideojuego());
            pstmt.setInt(6, r.getIdCategoria());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Recurso> filtrarPorAutor(int idUsuario) {
        ArrayList<Recurso> lista = new ArrayList<>();
        String sql = "SELECT * FROM RECURSO WHERE id_usuario = ?";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idUsuario);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                lista.add(new Recurso(
                        rs.getInt("id_recurso"),
                        rs.getString("nombre_rec"),
                        rs.getString("descripcion"),
                        rs.getString("version"),
                        rs.getInt("num_descargas"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_videojuego"),
                        rs.getInt("id_categoria")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public String obtenerNombreMod(int idMod) {
        String sql = "SELECT nombre_rec FROM RECURSO WHERE id_recurso = ?";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idMod);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nombre_rec");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String obtenerModPorID(int idMod) {
        String sql = "SELECT nombre_rec FROM RECURSO WHERE id_recurso = ?";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idMod);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nombre_rec");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Recurso> filtrarPorJuego(int idVideojuego) {
        ArrayList<Recurso> lista = new ArrayList<>();
        String sql = "SELECT * FROM RECURSO WHERE id_videojuego = ?";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idVideojuego);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                lista.add(new Recurso(
                        rs.getInt("id_recurso"),
                        rs.getString("nombre_rec"),
                        rs.getString("descripcion"),
                        rs.getString("version"),
                        rs.getInt("num_descargas"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_videojuego"),
                        rs.getInt("id_categoria")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public String obtenerNickname(int idUsuario) {
        String sql = "SELECT nickname FROM USUARIO WHERE id_usuario = ?";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idUsuario);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nickname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}