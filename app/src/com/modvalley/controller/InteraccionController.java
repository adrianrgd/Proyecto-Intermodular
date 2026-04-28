package com.modvalley.controller;

import java.util.ArrayList;
import com.modvalley.model.*;
import com.modvalley.dao.*;

public class InteraccionController {
    private InteraccionDAO interaccionDAO = new InteraccionDAO();

    public ArrayList<Comentario> comentarios = interaccionDAO.listarComentarios();
    public ArrayList<Valoracion> valoraciones = interaccionDAO.listarValoraciones();

    public void refrescarDatos() {
        this.comentarios = interaccionDAO.listarComentarios();
        this.valoraciones = interaccionDAO.listarValoraciones();
    }

    public void agregarComentario(String comentario, java.util.Date fecha, int idUser, int idMod) {
        Comentario comentarioNuevo = new Comentario(comentario, fecha, idUser, idMod);
        interaccionDAO.agregarComentario(comentarioNuevo);
        comentarios.add(comentarioNuevo);
    }

    public void agregarValoracion(int puntuacion, int idRecurso, int idUsuario) {
        Valoracion valoracionNueva = new Valoracion(puntuacion, idRecurso, idUsuario);
        interaccionDAO.agregarValoracion(valoracionNueva);
        valoraciones.add(valoracionNueva);
    }

    public int obtenerValoracion(int idRecurso) {
        for (Valoracion v : valoraciones) {
            if (v.getIdRecurso() == idRecurso) {
                return v.getPuntuacion();
            }
        }
        return 0;
    }

    public ArrayList<OpinionDTO> obtenerOpiniones(int idMod) {
        return interaccionDAO.obtenerOpinionesPorMod(idMod);
    }

    public void guardarOpinionCompleta(int puntuacion, String comentario, int idUsuario, int idMod) {
        interaccionDAO.insertarValoracion(puntuacion, idUsuario, idMod);
        interaccionDAO.insertarComentario(comentario, idUsuario, idMod);
    }

    public ArrayList<Valoracion> listarValoracionesPorUsuario(int idUsuario) {
        return interaccionDAO.listarValoracionesPorUsuario(idUsuario);
    }
}
