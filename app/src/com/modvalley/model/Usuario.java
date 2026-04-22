package com.modvalley.model;

public class Usuario {
    private int idUsuario;
    private String nickname;
    private String email;

    public Usuario(int idUsuario, String nickname, String email) {
        this.idUsuario = idUsuario;
        this.nickname = nickname;
        this.email = email;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + idUsuario + " | Nickname: " + nickname + " | Email: " + email;
    }
}
