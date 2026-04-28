package com.modvalley.view;

import com.modvalley.model.*;
import com.modvalley.controller.*;
import com.modvalley.Custom;
import java.util.ArrayList;
import java.util.Scanner;

public class PerfilMenu {

    public void mostrar(Scanner sc, Usuario user, UsuarioController usuarioCtrl, RecursoController recurso,
            JuegoController juego, InteraccionController interaccion, CategoriaController categoriaCtrl) {
        boolean enPerfil = true;

        while (enPerfil) {
            System.out.println(Custom.GRIS + "\n=== Perfil de " + Custom.AMARILLO + user.getNickname() + Custom.GRIS
                    + " ===" + Custom.RESET);
            System.out.println(Custom.VERDE + "1. Mis Mods publicados" + Custom.RESET);
            System.out.println(Custom.VERDE + "2. Mis Valoraciones" + Custom.RESET);
            System.out.println(Custom.VERDE + "3. Editar Perfil" + Custom.RESET);
            System.out.println(Custom.ROJO + "0. Volver al Menu" + Custom.RESET);
            System.out.println(Custom.GRIS + "===========================" + Custom.RESET);
            System.out.print("Opción: ");
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1:
                    mostrarMisMods(user, recurso, juego);
                    break;
                case 2:
                    mostrarMisValoraciones(user, interaccion, recurso, juego);
                    break;
                case 3:
                    new EditarPerfil().mostrar(sc, user, usuarioCtrl, recurso, juego, interaccion, categoriaCtrl);
                    break;
                case 0:
                    enPerfil = false;
                    break;
            }
        }
    }

    private void mostrarMisMods(Usuario user, RecursoController recurso, JuegoController juego) {
        System.out.println(
                Custom.GRIS + "\n" + Custom.GRIS + "Tus Publicaciones:"
                        + Custom.RESET);
        ArrayList<Recurso> misMods = recurso.filtrarPorAutor(user.getIdUsuario());

        if (misMods.isEmpty()) {
            System.out.println(Custom.ROJO + ">> No has publicado nada todavía." + Custom.RESET);
        } else {
            for (Recurso r : misMods) {
                System.out.println(Custom.MAGENTA + "> " + Custom.AMARILLO + "[" + "ID: " + r.getId()
                        + " | " + juego.obtenerNombreJuego(r.getIdVideojuego()) + "] " + Custom.RESET + Custom.VERDE
                        + r.getNombre() + Custom.GRIS + " | Descargas: " + Custom.VERDE
                        + r.getNumDescargas() + Custom.GRIS + " | Subido el " + Custom.VERDE
                        + recurso.obtenerFechaSubidaStr(r.getId()) + Custom.RESET);
            }
        }
    }

    private void mostrarMisValoraciones(Usuario user, InteraccionController interaccion, RecursoController recurso,
            JuegoController juego) {
        System.out.println(
                Custom.GRIS + "\n" + Custom.GRIS + "Tus Valoraciones:" + Custom.RESET);
        ArrayList<Valoracion> misVal = interaccion.listarValoracionesPorUsuario(user.getIdUsuario());

        for (Valoracion v : misVal) {
            System.out.println(Custom.MAGENTA + "> " + Custom.AMARILLO + "[" + "ID: " + v.getIdRecurso()
                    + " | " + juego.obtenerNombreJuego(v.getIdRecurso()) + "] " + Custom.RESET + Custom.VERDE
                    + " Puntuación: "
                    + v.getPuntuacion() + "/5");
        }
    }
}