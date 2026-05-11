package com.modvalley.view;

import com.modvalley.Custom;
import com.modvalley.controller.*;
import com.modvalley.model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Catalogo {

    public void mostrar(Scanner sc, JuegoController juego, RecursoController recurso,
            InteraccionController interaccion, Usuario user, CategoriaController categoriaCtrl,
            UsuarioController usuarioCtrl) {
        boolean enCatalogo = true;
        while (enCatalogo) {
            System.out.println(Custom.GRIS + "\n====== Explorar Juegos ======" + Custom.RESET);
            for (Videojuego v : juego.getJuegos()) {
                System.out.println(Custom.VERDE + v.getIdjuego() + ". " + v.getNombre_juego() + Custom.RESET);
            }
            System.out.println(Custom.GRIS + "=============================" + Custom.RESET);
            System.out.print(Custom.GRIS + "ID Juego " + Custom.ROJO + "(0 > Volver)" + Custom.RESET + ": ");
            int idJuego = sc.nextInt();
            sc.nextLine();

            if (idJuego == 0)
                enCatalogo = false;
            else
                mostrarModsJuego(sc, juego, recurso, interaccion, user, idJuego, categoriaCtrl, usuarioCtrl);
        }
    }

    private void mostrarModsJuego(Scanner sc, JuegoController juego, RecursoController recurso,
            InteraccionController interaccion, Usuario user, int idJuego, CategoriaController categoriaCtrl,
            UsuarioController usuarioCtrl) {
        boolean enMod = true;
        while (enMod) {
            ArrayList<Recurso> listaMods = recurso.getRecursos(idJuego);
            System.out.println(
                    Custom.GRIS + "\n==============================" + Custom.AMARILLO + " Mods para "
                            + Custom.CURSIVA + juego.obtenerNombreJuego(idJuego)
                            + Custom.RESET
                            + Custom.GRIS + " ==================================="
                            + Custom.RESET);

            if (listaMods.isEmpty()) {
                System.out.println(Custom.ROJO + "> No hay contenido." + Custom.RESET);
                return;
            }

            for (Recurso r : listaMods) {
                String autor = usuarioCtrl.obtenerNickname(r.getIdUsuario());
                String notaTxt = (r.getMediaValoracion() == 0) ? "N/C" : String.format("%.1f", r.getMediaValoracion());
                System.out.println(Custom.CYAN + "[" + r.getId() + "] " + r.getNombre() + Custom.RESET +
                        Custom.GRIS + " | Version: " + Custom.CYAN + r.getVersion() + Custom.RESET +
                        Custom.GRIS + " | Autor: " + Custom.CYAN + autor + Custom.RESET +
                        Custom.GRIS + " | Descargas: " + Custom.AMARILLO + r.getNumDescargas()
                        + Custom.RESET +
                        Custom.GRIS + " | Valoracion: " + Custom.AMARILLO + notaTxt
                        + Custom.RESET);
            }
            System.out.println(Custom.GRIS
                    + "==========================================================================================="
                    + Custom.RESET);
            System.out.print(
                    Custom.VERDE + "\nSelecciona un Mod [ID] " + Custom.ROJO + "(0 -> Volver)" + Custom.RESET + ": ");
            int idMod = sc.nextInt();
            sc.nextLine();

            if (idMod != 0)
                menuInteraccionMod(sc, interaccion, usuarioCtrl, user, idMod, recurso);
            else
                enMod = false;
        }
    }

    private void menuInteraccionMod(Scanner sc, InteraccionController interaccion, UsuarioController usuarioCtrl,
            Usuario user, int idMod, RecursoController recurso) {
        boolean enMod = true;
        while (enMod) {
            String nombreMod = recurso.obtenerNombreMod(idMod);
            System.out.println(Custom.GRIS + "\nMod Seleccionado: " + Custom.CYAN
                    + nombreMod + Custom.RESET + Custom.GRIS);
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

                    if (pts > 1 && pts < 5 && !texto.isEmpty()) {
                        interaccion.guardarOpinionCompleta(pts, texto, user.getIdUsuario(), idMod);
                        System.out.println(Custom.VERDE + "> Valoracion Enviada!" + Custom.RESET);
                    } else {
                        System.out.println(Custom.ROJO + "> Datos no validos!" + Custom.RESET);
                    }
                    break;
                case 3:
                    System.out.println(
                            Custom.AMARILLO + "\n> Opiniones de " + recurso.obtenerNombreMod(idMod)
                                    + Custom.RESET);

                    ArrayList<OpinionDTO> opiniones = interaccion.obtenerOpiniones(idMod);

                    if (opiniones.isEmpty()) {
                        System.out.println(Custom.ROJO + "> No hay comentarios" + Custom.RESET);
                    } else {
                        for (OpinionDTO op : opiniones) {
                            System.out.println(Custom.CYAN + op.getNickname() + Custom.RESET +
                                    " | Nota: " + Custom.VERDE + op.getPuntuacion() + "/5" + Custom.RESET +
                                    " | Comentario: " + Custom.GRIS + "\"" + op.getComentario() + "\"" + Custom.RESET
                                    + " | Fecha de Publicacion: "
                                    + Custom.AMARILLO + op.getFecha().toString() + Custom.RESET);
                        }
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
