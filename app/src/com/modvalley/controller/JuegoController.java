package com.modvalley.controller;

import java.util.ArrayList;

import com.modvalley.dao.VideojuegoDAO;
import com.modvalley.model.*;

public class JuegoControler {
    private VideojuegoDAO juegoDAO = new VideojuegoDAO();

    public ArrayList<Videojuego> getJuegos() {
        return juegoDAO.listarTodos();
    }

    public String obtenerNombreJuego(int idJuego) {
        for (Videojuego juego : juegoDAO.listarTodos()) {
            if (juego.getIdjuego() == idJuego) {
                return juego.getNombre_juego();
            }
        }
        return "Desconocido";
    }

}
