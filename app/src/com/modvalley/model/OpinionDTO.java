package com.modvalley.model;

import java.util.Date;

public class OpinionDTO {
    private String nickname;
    private int puntuacion;
    private String comentario;
    private Date fecha;

    public OpinionDTO(String nickname, int puntuacion, String comentario, Date fecha) {
        this.nickname = nickname;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public OpinionDTO(String nickname, int puntuacion, String comentario) {
        this.nickname = nickname;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
