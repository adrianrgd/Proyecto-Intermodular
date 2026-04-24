package com.modvalley.controller;

import com.modvalley.dao.UsuarioDAO;
import com.modvalley.model.Usuario;
import java.util.ArrayList;

public class UsuarioController {
    public ArrayList<Usuario> usuarios = new ArrayList<>();

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public ArrayList<Usuario> obtenerTodos() {
        return usuarioDAO.obtenerTodos();
    }

    public String obtenerNickname(int idUsuario) {
        Usuario u = usuarioDAO.buscarPorId(idUsuario);

        if (u != null) {
            return u.getNickname();
        }
        return "Desconocido";
    }
}