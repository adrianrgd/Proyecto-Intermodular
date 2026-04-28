package com.modvalley.view;

import com.modvalley.Custom;
import com.modvalley.model.*;
import com.modvalley.controller.*;
import com.modvalley.dao.*;

public class EliminarContenido {
    public void mostrar(java.util.Scanner sc, RecursoController recurso,
            Usuario autor, JuegoController juego,
            CategoriaController categoriaCtrl) {

        System.out.println(
                Custom.GRIS + "\n> Contenido para eliminar: " + Custom.RESET);

        java.util.ArrayList<Recurso> misMods = recurso.filtrarPorAutor(autor.getIdUsuario());

        if (misMods.isEmpty()) {
            System.out.println(
                    Custom.ROJO + "> No tienes mods subidos para eliminar." + Custom.RESET);
            return;
        }

        for (Recurso r : misMods) {
            System.out.println(
                    Custom.CYAN + "[" + r.getId() + "] " + r.getNombre() + Custom.RESET);
        }

        System.out.print(Custom.GRIS + "\nID del Mod: " + Custom.ROJO
                + "(0 -> Cancelar)" + Custom.RESET + Custom.GRIS + ": " + Custom.RESET);
        int idEliminar = sc.nextInt();
        sc.nextLine();

        if (idEliminar == 0)
            return;

        Recurso modAEliminar = null;
        for (Recurso r : misMods) {
            if (r.getId() == idEliminar) {
                modAEliminar = r;
                break;
            }
        }

        if (modAEliminar != null) {
            new RecursoDAO().Eliminar(modAEliminar);
            System.out.println(
                    Custom.VERDE + ">> Mod eliminado correctamente." + Custom.RESET);
        } else {
            System.out.println(
                    Custom.ROJO + ">> No se encontró el mod especificado." + Custom.RESET);
        }
    }
}
