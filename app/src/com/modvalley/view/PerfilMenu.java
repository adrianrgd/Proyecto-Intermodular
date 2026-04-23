package com.modvalley.view;

import com.modvalley.model.*;
import com.modvalley.controller.*;
import com.modvalley.Custom;
import java.util.ArrayList;
import java.util.Scanner;

public class PerfilMenu {

    public void mostrar(Scanner sc, Usuario user, RecursoController recurso, JuegoControler juego,
            InteraccionController interaccion) {
        boolean enPerfil = true;

        while (enPerfil) {
            System.out.println(Custom.GRIS + "\n======" + Custom.CURSIVA
                    + " Perfil de Usuario " + Custom.RESET + Custom.GRIS + "======" + Custom.RESET);
            System.out.println(Custom.AMARILLO + "Nickname: " + user.getNickname() + Custom.RESET);
            System.out.println(Custom.AMARILLO + "Email: " + user.getEmail() + Custom.RESET);
            System.out.println(Custom.GRIS + "==============================\n" + Custom.RESET);
            System.out.println(Custom.VERDE + "1. Ver mis Mods publicados" + Custom.RESET);
            System.out.println(Custom.VERDE + "2. Ver mis Comentarios" + Custom.RESET);
            System.out.println(Custom.VERDE + "3. Ver mis Valoraciones" + Custom.RESET);
            System.out.println(Custom.ROJO + "0. Volver al panel principal" + Custom.RESET);
            System.out.print(Custom.GRIS + "\nSelecciona una opcion: " + Custom.RESET);

            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    mostrarMisMods(user, recurso, juego);
                    pausar(sc);
                    break;
                case 2:
                    mostrarMisComentarios(user, interaccion, recurso);
                    pausar(sc);
                    break;
                case 3:
                    mostrarMisValoraciones(user, interaccion, recurso);
                    pausar(sc);
                    break;
                case 0:
                    enPerfil = false;
                    break;
                default:
                    System.out.println(Custom.ROJO + "Opcion no valida." + Custom.RESET);
                    break;
            }
        }
    }

    private void mostrarMisMods(Usuario user, RecursoController recurso, JuegoControler juego) {
        ArrayList<Recurso> misMods = recurso.filtrarPorAutor(user.getIdUsuario());
        System.out.println(Custom.GRIS + "\n================== MODS PUBLICADOS ===================" + Custom.RESET);
        if (misMods.isEmpty()) {
            System.out.println(Custom.ROJO + "> No has subido ningun mod todavia." + Custom.RESET);
        } else {
            for (Recurso r : misMods) {
                System.out.println(Custom.MAGENTA + "> " + Custom.AMARILLO + "["
                        + juego.obtenerNombreJuego(r.getIdJuego()) + "] " + Custom.RESET + Custom.VERDE
                        + r.getNombre() + Custom.GRIS + " | Descargas: " + Custom.VERDE + r.getDescargas()
                        + Custom.RESET);
            }
            System.out.println(Custom.GRIS + "======================================================" + Custom.RESET);
        }
    }

    private void mostrarMisComentarios(Usuario user, InteraccionController interaccion, RecursoController recurso) {
        System.out.println(Custom.GRIS + "\n=============== MIS COMENTARIOS ===============" + Custom.RESET);
        boolean hayComentarios = false;

        for (Comentario c : interaccion.comentarios) {
            if (c.getIdUsuario() == user.getIdUsuario()) {
                Recurso r = recurso.obtenerModPorId(c.getIdRecurso());
                String nombreMod = (r != null) ? r.getNombre() : "Mod Desconocido";

                System.out.println(Custom.MAGENTA + "> " + Custom.AMARILLO + "[" + nombreMod + "] " + Custom.GRIS
                        + Custom.VERDE + c.getComentario() + Custom.RESET);
                hayComentarios = true;
            }
        }
        System.out
                .println(Custom.GRIS + "===============================================" + Custom.RESET);

        if (!hayComentarios) {
            System.out.println(Custom.ROJO + "> No has comentado en ningún mod." + Custom.RESET);
        }
    }

    private void mostrarMisValoraciones(Usuario user, InteraccionController interaccion, RecursoController recurso) {
        System.out.println(Custom.GRIS + "\n=============== MIS VALORACIONES ===============" + Custom.RESET);
        boolean hayVal = false;
        for (Valoracion v : interaccion.valoraciones) {
            if (v.getIdUsuario() == user.getIdUsuario()) {
                System.out.println(Custom.MAGENTA + "> " + Custom.AMARILLO + "["
                        + recurso.obtenerModPorId(v.getIdRecurso()).getNombre() + "]" + Custom.GRIS
                        + " | Puntuacion: "
                        + Custom.VERDE + v.getPuntuacion() + Custom.RESET);
                hayVal = true;
            }
        }
        System.out.println(Custom.GRIS + "================================================" + Custom.RESET);

        if (!hayVal)
            System.out.println(Custom.ROJO + "> No has valorado ningun mod." + Custom.RESET);
    }

    private void pausar(Scanner sc) {
        System.out.println(Custom.GRIS + "\n[ Pulsa ENTER para volver al Perfil ]" + Custom.RESET);
        sc.nextLine();
    }
}