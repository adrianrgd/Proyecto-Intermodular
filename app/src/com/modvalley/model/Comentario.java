package com.modvalley.model;

import java.util.Date;

public class Comentario {
    private String comentario;
    private Date fecha;
    private int idUsuario;
    private int idRecurso;

    public Comentario(String comentario, Date fecha, int idUsuario, int idRecurso) {
        this.comentario = comentario;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.idRecurso = idRecurso;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    @Override
    public String toString() {
        return "Comentario: " + comentario + " | Fecha: " + fecha + " | Autor: " + idUsuario;
    }
}