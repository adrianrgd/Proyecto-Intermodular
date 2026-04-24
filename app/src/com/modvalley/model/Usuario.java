package com.modvalley.model;

import com.modvalley.Custom;
import java.util.Date;

public class Usuario {
    private int idUsuario;
    private String nickname;
    private String email;
    private Date fecha_registro;
    private String biografia;
    private String foto_perfil;

    public Usuario(int idUsuario, String nickname, String email, Date fecha_registro, String biografia,
            String foto_perfil) {
        this.idUsuario = idUsuario;
        this.nickname = nickname;
        this.email = email;
        this.fecha_registro = fecha_registro;
        this.biografia = biografia;
        this.foto_perfil = foto_perfil;
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

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(String foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    @Override
    public String toString() {
        return Custom.AMARILLO + "ID: " + idUsuario + Custom.RESET + " | Nickname: " + Custom.CYAN + nickname
                + Custom.RESET + " | Email: " + Custom.GRIS + email;
    }
}
