package com.modvalley.model;

public class Valoracion {
    private int puntuacion;
    private int idRecurso;
    private int idUsuario;

    public Valoracion(int puntuacion, int idRecurso, int idUsuario) {
        this.puntuacion = puntuacion;
        this.idRecurso = idRecurso;
        this.idUsuario = idUsuario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Usuario: " + idUsuario + " | Puntuación: " + puntuacion + "/5";
    }
}
