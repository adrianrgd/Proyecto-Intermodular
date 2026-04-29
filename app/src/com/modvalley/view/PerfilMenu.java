package com.modvalley.view;

import com.modvalley.model.*;
import com.modvalley.controller.*;
import com.modvalley.Custom;
import java.util.ArrayList;
import java.util.Scanner;

public class PerfilMenu {

    public void mostrar(Scanner sc, Usuario user, UsuarioController usuarioCtrl, RecursoController recurso,
            JuegoController juego, InteraccionController interaccion, CategoriaController categoriaCtrl,
            Usuario usuarioLogueado) {
        boolean enPerfil = true;

        while (enPerfil) {
            System.out.println(Custom.GRIS + "\n--- [ " + Custom.AMARILLO + "PERFIL: " + user.getNickname()
                    + Custom.GRIS + " ] ---" + Custom.RESET);

            System.out.println(Custom.GRIS + "ID: " + Custom.AMARILLO + user.getIdUsuario() + Custom.GRIS +
                    " | " + Custom.VERDE + "Registro: " + user.getFecha_registro() + Custom.GRIS +
                    " | Email: " + Custom.CYAN + user.getEmail() + Custom.RESET);

            String bio = user.getBiografia();
            System.out
                    .println(Custom.GRIS + "Bio: " + Custom.RESET + Custom.CURSIVA + "\"" + bio + "\"" + Custom.RESET);

            System.out.println(
                    Custom.GRIS + "--------------------------------------------------------------------------------"
                            + Custom.RESET);
            System.out.println(Custom.VERDE + "1.Mods" + Custom.RESET + " | " +
                    Custom.VERDE + "2.Valoraciones" + Custom.RESET + " | " +
                    Custom.VERDE + "3.Editar" + Custom.RESET + " | " +
                    Custom.ROJO + "0.Volver" + Custom.RESET);
            System.out.println(
                    Custom.GRIS + "--------------------------------------------------------------------------------"
                            + Custom.RESET);

            System.out.print("Opción: ");
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1:
                    mostrarMisMods(sc, user, recurso, juego);
                    break;
                case 2:
                    mostrarMisValoraciones(user, sc, interaccion, recurso, juego);
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

    private void mostrarMisMods(Scanner sc, Usuario user, RecursoController recurso, JuegoController juego) {
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
        System.out.print(Custom.GRIS + "\n" + "Presiona ENTER para volver al menu!" + Custom.RESET);
        sc.nextLine();
    }

    private void mostrarMisValoraciones(Usuario user, Scanner sc, InteraccionController interaccion,
            RecursoController recurso,
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
        System.out.print(Custom.GRIS + "\n" + "Presiona ENTER para volver al menu!" + Custom.RESET);
        sc.nextLine();
    }
}