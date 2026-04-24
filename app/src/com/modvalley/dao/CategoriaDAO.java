package com.modvalley.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.modvalley.config.Conexion;
import com.modvalley.model.Categoria;

public class CategoriaDAO {

    public ArrayList<Categoria> obtenerTodas() {
        ArrayList<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIA";

        try (Connection conn = Conexion.conectar();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Categoria(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre_cat")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
