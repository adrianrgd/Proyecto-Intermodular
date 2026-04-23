package com.modvalley.view;

import com.modvalley.model.*;
import com.modvalley.controller.*;
import com.modvalley.Custom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Catalogo {

    public void mostrar(Scanner sc, JuegoControler juego, RecursoController recurso, InteraccionController interaccion,
            UsuarioController usuarioCtrl, Usuario user) {
        boolean enCatalogo = true;
        while (enCatalogo) {
            System.out.println(Custom.GRIS + "\n====== Explorar Juegos ======" + Custom.RESET);
            for (Videojuego v : juego.juegos) {
                System.out.println(Custom.VERDE + v.getIdjuego() + ". " + v.getNombre_juego() + Custom.RESET);
            }
            System.out.println(Custom.GRIS + "=============================" + Custom.RESET);
            System.out.print(Custom.GRIS + "ID Juego " + Custom.ROJO + "(0 > Volver)" + Custom.RESET + ": ");
            int idJuego = sc.nextInt();
            sc.nextLine();

            if (idJuego == 0)
                enCatalogo = false;
            else
                mostrarModsJuego(sc, juego, recurso, interaccion, usuarioCtrl, user, idJuego);
        }
    }

    private void mostrarModsJuego(Scanner sc, JuegoControler juego, RecursoController recurso,
            InteraccionController interaccion, UsuarioController usuarioCtrl,
            Usuario user, int idJuego) {
        ArrayList<Recurso> filtrados = recurso.filtrarPorJuego(idJuego);
        System.out.println(
                Custom.GRIS + "\n==============================" + Custom.AMARILLO + " Mods para "
                        + Custom.CURSIVA + juego.obtenerNombreJuego(idJuego)
                        + Custom.RESET
                        + Custom.GRIS + " ==================================="
                        + Custom.RESET);

        if (filtrados.isEmpty()) {
            System.out.println(Custom.ROJO + "> No hay contenido." + Custom.RESET);
            return;
        }

        for (Recurso r : filtrados) {
            String autor = usuarioCtrl.obtenerNickname(r.getIdAutor());
            double media = interaccion.obtenerMedia(r.getId());
            System.out.println(Custom.CYAN + "[" + r.getId() + "] " + r.getNombre() + Custom.RESET +
                    Custom.GRIS + " | Version: " + Custom.CYAN + r.getVersion() + Custom.RESET +
                    Custom.GRIS + " | Autor: " + Custom.CYAN + autor + Custom.RESET +
                    Custom.GRIS + " | Descargas: " + Custom.AMARILLO + r.getDescargas() + Custom.RESET +
                    Custom.GRIS + " | Valoracion: " + Custom.AMARILLO + String.format("%.1f", media) + Custom.RESET);
        }
        System.out.println(Custom.GRIS
                + "==========================================================================================="
                + Custom.RESET);
        System.out.print(
                Custom.VERDE + "\nSelecciona un Mod [ID] " + Custom.ROJO + "(0 -> Volver)" + Custom.RESET + ": ");
        int idMod = sc.nextInt();
        sc.nextLine();

        if (idMod != 0) // cuando haya bbd hay que cambiarlo para que consulte si existe el id
            menuInteraccionMod(sc, recurso, interaccion, user, idMod, usuarioCtrl);
    }

    private void menuInteraccionMod(Scanner sc, RecursoController recurso, InteraccionController interaccion,
            Usuario user, int idMod, UsuarioController usuarioCtrl) {
        boolean enMod = true;
        while (enMod) {
            System.out.println(Custom.GRIS + "\nMod Seleccionado: " + Custom.CYAN
                    + recurso.obtenerModPorId(idMod).getNombre() + Custom.RESET + Custom.GRIS);
            System.out.println(Custom.AMARILLO + "1. Descargar" + Custom.GRIS + " | " + Custom.AMARILLO
                    + "2. Valorar" + Custom.GRIS + " | " + Custom.AMARILLO + "3. Ver Opiniones" + Custom.GRIS + " | "
                    + Custom.ROJO + "0. Volver");
            System.out.print(Custom.GRIS + "Opcion: " + Custom.RESET);
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1:
                    recurso.registrarDescarga(idMod);
                    System.out.println(Custom.VERDE + ">> Descargado!" + Custom.RESET);
                    break;
                case 2:
                    System.out.print(Custom.AMARILLO + "\n> Comentario: " + Custom.RESET);
                    String texto = sc.nextLine();
                    if (texto.isEmpty()) {
                        System.out.println(Custom.ROJO + "> El comentario no puede estar vacio" + Custom.RESET);
                        break;
                    }
                    System.out.print(Custom.AMARILLO + "\n> Nota (1-5): " + Custom.RESET);
                    int pts = sc.nextInt();
                    if (pts >= 1 && pts <= 5) {
                        interaccion.agregarValoracion(pts, user.getIdUsuario(), idMod);
                        interaccion.agregarComentario(texto, new Date(), user.getIdUsuario(), idMod);
                        System.out.println(Custom.VERDE + ">> ¡Guardado en memoria!" + Custom.RESET);
                    }

                    if (pts < 1 || pts > 5) {
                        System.out.println(Custom.ROJO + "> Nota no valida" + Custom.RESET);
                        break;
                    }
                    System.out.println(Custom.VERDE + ">> Valoracion enviada!" + Custom.RESET);
                    break;
                case 3:
                    System.out.println(
                            Custom.AMARILLO + "\n> Opiniones de " + recurso.obtenerModPorId(idMod).getNombre()
                                    + Custom.RESET);
                    boolean hayDatos = false;

                    for (Valoracion v : interaccion.valoraciones) {
                        if (v.getIdRecurso() == idMod) {
                            hayDatos = true;
                            String nick = usuarioCtrl.obtenerNickname(v.getIdUsuario());
                            String textoComentario = "Sin comentario";

                            for (Comentario c : interaccion.comentarios) {
                                if (c.getIdRecurso() == idMod && c.getIdUsuario() == v.getIdUsuario()) {
                                    textoComentario = c.getComentario();
                                    break;
                                }
                            }

                            System.out.println(Custom.CYAN + nick + Custom.RESET +
                                    " | Nota: " + Custom.VERDE + v.getPuntuacion() + "/5" + Custom.RESET +
                                    " | Comentario: " + Custom.GRIS + "\"" + textoComentario + "\"" + Custom.RESET);
                        }
                    }

                    if (!hayDatos) {
                        System.out.println(Custom.ROJO + "> No hay opiniones registradas todavía." + Custom.RESET);
                    }

                    System.out.println(Custom.GRIS + "\n[ Presiona ENTER para continuar ]" + Custom.RESET);
                    sc.nextLine();
                    break;
                case 0:
                    enMod = false;
                    break;
            }
        }
    }
}
