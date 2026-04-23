package com.modvalley.controller;

import com.modvalley.model.Usuario;
import java.util.ArrayList;

public class UsuarioController {
    public ArrayList<Usuario> usuarios = new ArrayList<>();

    public UsuarioController() {
        usuarios.add(new Usuario(1, "Adrian", "adrian@modvalley.com"));
        usuarios.add(new Usuario(2, "Miguel", "miguel@modvalley.com"));
        usuarios.add(new Usuario(3, "Carlos", "carlos@modvalley.com"));
    }

    public String obtenerNickname(int idUsuario) {
        for (Usuario u : usuarios) {
            if (u.getIdUsuario() == idUsuario) {
                return u.getNickname();
            }
        }
        return "Desconocido";
    }
}