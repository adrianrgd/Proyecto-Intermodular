package com.modvalley.controller;

import com.modvalley.model.Categoria;
import java.util.ArrayList;

public class CategoriaController {
    private com.modvalley.dao.CategoriaDAO categoriaDAO = new com.modvalley.dao.CategoriaDAO();

    public ArrayList<Categoria> obtenerCategorias() {
        return categoriaDAO.obtenerTodas();
    }
}
