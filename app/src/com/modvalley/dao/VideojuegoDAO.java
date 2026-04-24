package com.modvalley.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.modvalley.config.Conexion;
import com.modvalley.model.Videojuego;

public class VideojuegoDAO {
    public ArrayList<Videojuego> listarTodos() {
        ArrayList<Videojuego> lista = new ArrayList<>();
        String sql = "SELECT * FROM VIDEOJUEGO";
        try (Connection conn = Conexion.conectar();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Videojuego(
                        rs.getInt("id_videojuego"),
                        rs.getString("nombre_juego"),
                        rs.getString("genero"),
                        rs.getString("plataforma")));

            }
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return lista;
    }
}
