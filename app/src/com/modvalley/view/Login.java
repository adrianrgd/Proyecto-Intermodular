package com.modvalley.view;

import com.modvalley.model.Usuario;
import com.modvalley.Custom;
import com.modvalley.controller.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    public Usuario mostrar(Scanner sc, ArrayList<Usuario> listaUsuarios, UsuarioController usuarioCtrl) {

        System.out.println(Custom.GRIS + "==============================" + Custom.RESET);
        System.out.println(
                Custom.GRIS + "    " + Custom.AMARILLO + Custom.CURSIVA + Custom.SUBRAYADO + "BIENVENIDO A MODVALLEY"
                        + Custom.RESET
                        + Custom.GRIS
                        + "    ");
        System.out.println(Custom.GRIS + "==============================" + Custom.RESET);
        System.out.println(Custom.VERDE + "1. Registrarse" + Custom.RESET);
        System.out.println(Custom.VERDE + "2. Seleccionar Usuario" + Custom.RESET);
        System.out.println(Custom.ROJO + "0. Salir" + Custom.RESET);

        System.out.print("" + Custom.GRIS + "\n> Opcion: " + Custom.RESET);
        int opt = sc.nextInt();
        sc.nextLine();
        switch (opt) {
            case 1:
                Registro(sc, usuarioCtrl);
                listaUsuarios.clear();
                listaUsuarios.addAll(usuarioCtrl.obtenerTodos());
                return mostrar(sc, listaUsuarios, usuarioCtrl);
            case 2:
                return SeleccionarUsuario(sc, listaUsuarios, usuarioCtrl);
            case 0:
                System.out.println(Custom.ROJO + "> Saliendo..." + Custom.RESET);
                break;
            default:
                break;
        }
        return null;
    }

    public Usuario SeleccionarUsuario(Scanner sc, ArrayList<Usuario> listaUsuarios, UsuarioController usuarioCtrl) {
        System.out.println("");

        for (Usuario u : listaUsuarios) {
            System.out
                    .println(Custom.AMARILLO + "ID: " + u.getIdUsuario() + Custom.RESET + " | Nickname: "
                            + Custom.CYAN + u.getNickname() + Custom.RESET + " | Email: " + Custom.VERDE + u.getEmail()
                            + Custom.RESET);
        }

        System.out
                .print("\n" + Custom.VERDE + "Introduce el ID del usuario (" + Custom.ROJO + "0 -> Salir" + Custom.VERDE
                        + "): " + Custom.RESET);

        int idElejido = sc.nextInt();
        sc.nextLine();

        if (idElejido == 0) {
            System.out.println(Custom.ROJO + "Saliendo..." + Custom.RESET);
            return null;
        }

        for (Usuario u : listaUsuarios) {
            if (u.getIdUsuario() == idElejido) {
                return u;
            }
        }

        System.out.println(Custom.ROJO + "Error! ID no valido." + Custom.RESET);
        return null;
    }

    public void Registro(Scanner sc, UsuarioController usuarioCtrl) {
        System.out.println(Custom.AMARILLO + Custom.SUBRAYADO + "\n> Registrar Usuario " + Custom.RESET);
        System.out.print(Custom.GRIS + "> Nickname: " + Custom.RESET);
        String nickname = sc.nextLine();
        System.out.print(Custom.GRIS + "> Email: " + Custom.RESET);
        String email = sc.nextLine();
        System.out.print(Custom.GRIS + "> Biografia: " + Custom.RESET);
        String biografia = sc.nextLine();

        usuarioCtrl.registrarUsuario(nickname, email, biografia);
    }

}
