package com.modvalley.view;

import java.util.Scanner;

import com.modvalley.Custom;
import com.modvalley.controller.*;
import com.modvalley.model.*;

public class EditarPerfil {
    public void mostrar(Scanner sc, Usuario user, UsuarioController usuarioCtrl,
            RecursoController recurso2, JuegoController juego, InteraccionController interaccion,
            CategoriaController categoriaCtrl) {

        System.out.println(
                Custom.GRIS + "\n" + Custom.GRIS + "========== " + Custom.AMARILLO + "EDITAR PERFIL" + Custom.GRIS
                        + " ==========" + Custom.RESET);
        System.out.println(Custom.VERDE + "1. Editar Nickname" + Custom.RESET);
        System.out.println(Custom.VERDE + "2. Editar Foto de Perfil" + Custom.RESET);
        System.out.println(Custom.VERDE + "3. Editar Biografía" + Custom.RESET);
        System.out.println(Custom.ROJO + "4. Eliminar Cuenta" + Custom.RESET);
        System.out.println(Custom.ROJO + "0. Salir" + Custom.RESET);
        System.out.println(Custom.GRIS + "===========================" + Custom.RESET);
        System.out.print("Opción: ");
        int opt = sc.nextInt();
        sc.nextLine();

        switch (opt) {
            case 1:
                EditarNickname(sc, user, usuarioCtrl);
                break;

            case 2:
                EditarFoto(sc, user, usuarioCtrl);
                break;

            case 3:
                EditarBio(sc, user, usuarioCtrl, interaccion);
                break;

            case 4:
                EliminarCuenta(sc, user, interaccion, usuarioCtrl);
                break;

            default:
                break;
        }

    }

    private void EditarNickname(Scanner sc, Usuario user, UsuarioController usuarioCtrl) {
        System.out.println(Custom.GRIS + "Nickname Actual: " + Custom.AMARILLO + user.getNickname() + Custom.RESET);
        System.out.print(Custom.GRIS + "\n> Nuevo Nickname: " + Custom.RESET);
        String nuevoNickname = sc.nextLine();

        if (nuevoNickname != null && !nuevoNickname.isEmpty()) {
            usuarioCtrl.actualizarNickname(user.getIdUsuario(), nuevoNickname);
            System.out.println(Custom.VERDE + "> Nickname actualizado correctamente." + Custom.RESET);
        } else {
            System.out.println(Custom.ROJO + "> Nickname no válido." + Custom.RESET);
        }
    }

    private void EditarFoto(Scanner sc, Usuario user, UsuarioController usuarioCtrl) {
        System.out.print(Custom.GRIS + "> Nueva Foto: " + Custom.RESET);
        String nuevaFoto = sc.nextLine();

        if (nuevaFoto != null) {
            usuarioCtrl.actualizarFoto(user.getIdUsuario(), nuevaFoto);
            System.out.println(Custom.VERDE + "> Foto actualizada correctamente." + Custom.RESET);
        } else {
            System.out.println(Custom.ROJO + "> Foto no válida." + Custom.RESET);

        }
    }

    private void EditarBio(Scanner sc, Usuario user, UsuarioController usuarioCtrl,
            InteraccionController interaccion) {

        System.out.println(Custom.AMARILLO + "\nBiografía: " + Custom.RESET + user.getBiografia());
        System.out.print(Custom.GRIS + "> Nueva Biografía: " + Custom.RESET);

        String nuevaBio = sc.nextLine();

        if (nuevaBio != null) {
            usuarioCtrl.actualizarBio(user.getIdUsuario(), nuevaBio);
            System.out.println(Custom.VERDE + "> Biografia actualizada correctamente!" + Custom.RESET);
        } else {
            System.out.println(Custom.ROJO + "> Biografia no válida." + Custom.RESET);
        }
    }

    private void EliminarCuenta(Scanner sc, Usuario user, InteraccionController interaccion,
            UsuarioController usuarioCtrl) {
        System.out.println(Custom.ROJO + "¿SEGURO QUE DESEAS ELIMINAR TU CUENTA?" + Custom.RESET);
        System.out.println(
                Custom.GRIS + "Tus mods y comentarios permanecerán, pero tu perfil desaparecerá." + Custom.RESET);
        System.out.print(Custom.GRIS + "> Confirmar (s/n): " + Custom.RESET);
        String confirmacion = sc.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            usuarioCtrl.eliminarUsuario(user.getIdUsuario());
        }

    }

}
