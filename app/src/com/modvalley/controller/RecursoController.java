package com.modvalley.controller;

import com.modvalley.dao.RecursoDAO;
import com.modvalley.model.Recurso;
import java.util.ArrayList;

public class RecursoController {
    private RecursoDAO recursoDAO = new RecursoDAO();

    public ArrayList<Recurso> getRecursos(int idVideojuego) {
        return recursoDAO.filtrarPorJuego(idVideojuego);
    }

    public void registrarDescarga(int idMod) {
        recursoDAO.incrementarDescarga(idMod);
    }

    public String obtenerNickname(int idUsuario) {
        return recursoDAO.obtenerNickname(idUsuario);
    }

    public ArrayList<Recurso> filtrarPorAutor(int idUsuario) {
        return recursoDAO.filtrarPorAutor(idUsuario);
    }

    public String nombreMod(int idMod) {
        return recursoDAO.obtenerNombreMod(idMod);
    }

    public String obtenerModPorID(int idMod) {
        return recursoDAO.obtenerModPorID(idMod);
    }

    public void subirMod(Recurso nuevo) {
        recursoDAO.insertar(nuevo);
    }

    public Recurso obtenerModPorId(int idMod) {
        return null;
    }
}
