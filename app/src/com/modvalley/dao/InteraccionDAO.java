package com.modvalley.dao;

import com.modvalley.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.modvalley.model.Comentario;
import com.modvalley.model.OpinionDTO;
import com.modvalley.model.Valoracion;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;

public class InteraccionDAO {

    // COMENTARIOS

    public void agregarComentario(Comentario c) {
        String sql = "INSERT INTO COMENTARIO (comentario, fecha, id_usuario, id_recurso) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getComentario());
            pstmt.setDate(2, new java.sql.Date(c.getFecha().getTime()));
            pstmt.setInt(3, c.getIdUsuario());
            pstmt.setInt(4, c.getIdRecurso());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al agregar comentario: " + e.getMessage());
        }
    }

    // VALORACIONES

    public void agregarValoracion(Valoracion v) {
        String sql = "INSERT INTO VALORACION (puntuacion, id_usuario, id_recurso) VALUES (?, ?, ?)";

        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, v.getPuntuacion());
            pstmt.setInt(2, v.getIdUsuario());
            pstmt.setInt(3, v.getIdRecurso());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al agregar valoracion: " + e.getMessage());
        }
    }

    // LEER TODOS

    public ArrayList<Comentario> listarComentarios() {
        ArrayList<Comentario> lista = new ArrayList<>();
        String sql = "SELECT * FROM COMENTARIO";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Comentario(
                        rs.getString("comentario"),
                        rs.getDate("fecha"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_recurso")));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar comentarios: " + e.getMessage());
        }
        return lista;
    }

    public ArrayList<Valoracion> listarValoraciones() {
        ArrayList<Valoracion> lista = new ArrayList<>();
        String sql = "SELECT * FROM VALORACION";
        try {
            Connection conn = Conexion.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                lista.add(new Valoracion(
                        rs.getInt("puntuacion"),
                        rs.getInt("id_recurso"),
                        rs.getInt("id_usuario")));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar valoraciones: " + e.getMessage());
        }
        return lista;
    }

    // OBTENER OPINIONES DE UN MOD

    public ArrayList<OpinionDTO> obtenerOpinionesPorMod(int idMod) {

        ArrayList<OpinionDTO> lista = new ArrayList<>();

        String sql = "SELECT u.nickname, v.puntuacion, c.comentario, c.fecha " + "FROM valoracion v "
                + "JOIN usuario u ON v.id_usuario = u.id_usuario "
                + "LEFT JOIN comentario c ON (v.id_usuario = c.id_usuario AND v.id_recurso = c.id_recurso) "
                + "WHERE v.id_recurso = ?";

        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idMod);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                lista.add(new OpinionDTO(rs.getString("nickname"), rs.getInt("puntuacion"),
                        rs.getString("comentario") != null ? rs.getString("comentario") : "Sin Comentario!",
                        rs.getDate("fecha")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // VALORAR MODS

    public void insertarValoracion(int puntuacion, int idUsuario, int idRecurso) {
        String sql = "INSERT INTO VALORACION (puntuacion, id_usuario, id_recurso) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, puntuacion);
            pstmt.setInt(2, idUsuario);
            pstmt.setInt(3, idRecurso);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarComentario(String comentario, int idUsuario, int idRecurso) {
        String sql = "INSERT INTO COMENTARIO (comentario, id_usuario, id_recurso) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, comentario);
            pstmt.setInt(2, idUsuario);
            pstmt.setInt(3, idRecurso);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Valoracion> listarValoracionesPorUsuario(int idUser) {
        ArrayList<Valoracion> lista = new ArrayList<>();
        String sql = "SELECT * FROM VALORACION WHERE id_usuario = ?";
        try (Connection conn = Conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                lista.add(new Valoracion(
                        rs.getInt("puntuacion"),
                        rs.getInt("id_recurso"),
                        rs.getInt("id_usuario")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
