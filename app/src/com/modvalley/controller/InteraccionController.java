package com.modvalley.controller;

import java.util.ArrayList;
import com.modvalley.model.Comentario;
import com.modvalley.model.OpinionDTO;
import com.modvalley.model.Valoracion;
import com.modvalley.dao.InteraccionDAO;

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

    public void agregarValoracion(int puntuacion, int idUser, int idMod) {
        Valoracion valoracionNueva = new Valoracion(puntuacion, idMod, idUser, idMod);
        interaccionDAO.agregarValoracion(valoracionNueva);
        valoraciones.add(valoracionNueva);
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

    public ArrayList<OpinionDTO> obtenerOpiniones(int idMod) {
        return interaccionDAO.obtenerOpinionesPorMod(idMod);
    }

    public void guardarOpinionCompleta(int puntuacion, String comentario, int idUsuario, int idMod) {
        interaccionDAO.insertarValoracion(puntuacion, idUsuario, idMod);
        interaccionDAO.insertarComentario(comentario, idUsuario, idMod);
    }
}
