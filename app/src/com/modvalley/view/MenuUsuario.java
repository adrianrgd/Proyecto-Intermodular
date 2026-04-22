package app.src.com.modvalley.view;

import app.src.com.modvalley.model.Usuario;

import java.util.Scanner;

public class MenuUsuario {
    public int mostrar(Scanner sc, Usuario user) {
        System.out.println("=== Panel de " + user.getNickname() + " ===");
        System.out.println("1. Catalogo");
        System.out.println("2. Subir Contenido");
        System.out.println("3. Cerrar Sesion");
        System.out.println("Opcion: ");
        return sc.nextInt();
    }
}
