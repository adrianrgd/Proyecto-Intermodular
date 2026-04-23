package com.modvalley.controller;

import java.sql.Date;
import java.util.ArrayList;
import com.modvalley.model.Comentario;
import com.modvalley.model.Valoracion;

public class InteraccionController {
    public ArrayList<Valoracion> valoraciones = new ArrayList<>();
    public ArrayList<Comentario> comentarios = new ArrayList<>();

    public InteraccionController() {
        comentarios.add(new Comentario("El mejor mod que he usado", new Date(0), 1, 101));
        comentarios.add(new Comentario("No esta mal, aunque esperaba mas", new Date(0), 2, 101));
        comentarios.add(new Comentario("Esta muy bien hecho", new Date(0), 1, 102));
        comentarios.add(new Comentario("No funciona", new Date(0), 3, 102));
        valoraciones.add(new Valoracion(5, 101, 1));
        valoraciones.add(new Valoracion(3, 101, 2));
        valoraciones.add(new Valoracion(4, 103, 2));
        valoraciones.add(new Valoracion(1, 104, 2));
    }

    public void agregarComentario(String comentario, java.util.Date fecha, int idUser, int idMod) {
        comentarios.add(new Comentario(comentario, fecha, idUser, idMod));
    }

    public void agregarValoracion(int puntuacion, int idUser, int idMod) {
        valoraciones.add(new Valoracion(puntuacion, idUser, idMod));
    }

    public double obtenerMedia(int idMod) {
        int suma = 0;
        int contador = 0;
        for (Valoracion v : valoraciones) {
            if (v.getIdRecurso() == idMod) {
                suma += v.getPuntuacion();
                contador++;
            }
        }
        return (contador == 0) ? 0.0 : suma / contador;
    }

    public int obtenerValoracion(int idRecurso) {
        for (Valoracion v : valoraciones) {
            if (v.getIdRecurso() == idRecurso) {
                return v.getPuntuacion();
            }
        }
        return 0;
    }
}
