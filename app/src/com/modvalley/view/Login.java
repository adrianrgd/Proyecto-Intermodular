package com.modvalley.view;

import com.modvalley.model.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    public Usuario mostrar(Scanner sc, ArrayList<Usuario> listaUsuarios) {
        System.out.println("=== BIENVENIDO A MODVALLEY ===");
        System.out.println("Selecciona un usuario para iniciar sesion: ");

        for (Usuario u : listaUsuarios) {
            System.out.println(u);
        }
        System.out.println("Introduce '0' para Salir.");

        System.out.print("Introduce el ID del usuario: ");
        int idElejido = sc.nextInt();

        if (idElejido == 0) {
            System.out.println("Cerrando Programa!");
            return null;
        }

        for (Usuario u : listaUsuarios) {
            if (u.getIdUsuario() == idElejido) {
                return u;
            }
        }

        System.out.println("Error! ID no valido.");
        return null;
    }
}
