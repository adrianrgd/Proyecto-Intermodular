package com.modvalley.view;

import com.modvalley.model.Usuario;

import java.util.Scanner;

public class MenuUsuario {
    public int mostrar(Scanner sc, Usuario user) {
        System.out.println("=== Panel de " + user.getNickname() + " ===");
        System.out.println("1. Ver catalogo de mods");
        System.out.println("2. Subir contenido");
        System.out.println("3. Perfil");
        System.out.println("4. Cerrar Sesion");
        System.out.print("Opcion: ");
        return sc.nextInt();
    }
}
