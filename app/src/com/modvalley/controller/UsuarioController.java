package com.modvalley.controller;

import com.modvalley.Custom;
import com.modvalley.dao.UsuarioDAO;
import com.modvalley.model.Usuario;
import java.util.ArrayList;

public class UsuarioController {
    public ArrayList<Usuario> usuarios = new ArrayList<>();

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public ArrayList<Usuario> obtenerTodos() {
        return usuarioDAO.obtenerTodos();
    }

    public Usuario buscarPorId(int id) {
        return usuarioDAO.buscarPorId(id);
    }

    public String obtenerNickname(int idUsuario) {
        Usuario u = usuarioDAO.buscarPorId(idUsuario);

        if (u == null) {
            return Custom.ROJO + "Usuario Eliminado" + Custom.RESET;
        }
        return u.getNickname();
    }

    public void actualizarNickname(int id, String nuevoNickname) {
        usuarioDAO.ActualizarNickname(id, nuevoNickname);
    }

    public void actualizarFoto(int id, String nuevaFoto) {
        usuarioDAO.ActualizarFoto(id, nuevaFoto);
    }

    public void actualizarBio(int id, String nuevaBio) {
        usuarioDAO.ActualizarBio(id, nuevaBio);
    }

    public void eliminarUsuario(int id) {
        usuarioDAO.eliminarUsuario(id);
    }

    public void refrescarDatos() {
        this.usuarios = usuarioDAO.obtenerTodos();
    }

    public Usuario refrescarUsuario(int id) {
        return usuarioDAO.buscarPorId(id);
    }

    public void registrarUsuario(String nickname, String email, String biografia) {
        usuarioDAO.registrarUsuario(nickname, email, biografia);
    }
}
