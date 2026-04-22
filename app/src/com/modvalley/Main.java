package com.modvalley;

import com.modvalley.model.Usuario;
import com.modvalley.controller.GestionController;
import com.modvalley.view.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestionController gestion = new GestionController();

        Login login = new Login();
        MenuUsuario menuUsuario = new MenuUsuario();
        Catalogo catalogo = new Catalogo();
        PerfilMenu perfilMenu = new PerfilMenu();
        SubirContenidoMenu subirContenidoMenu = new SubirContenidoMenu();

        // Datos de prueba (Luego vendrán de la BBDD)
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "Adrian", "adrian@modvalley.com"));

        Usuario usuarioLogueado = null;

        while (true) {
            if (usuarioLogueado == null) {
                usuarioLogueado = login.mostrar(sc, usuarios);
                if (usuarioLogueado == null)
                    break; // Salida del programa
            } else {
                int opcion = menuUsuario.mostrar(sc, usuarioLogueado);
                switch (opcion) {
                    case 1:
                        catalogo.mostrar(sc, gestion);
                        break;
                    case 2:
                        subirContenidoMenu.mostrar(sc, gestion, usuarioLogueado);
                        break;
                    case 3:
                        perfilMenu.mostrar(usuarioLogueado, gestion);
                    case 4:
                        usuarioLogueado = null;
                        System.out.println("Sesión cerrada.");
                        break;
                }
            }
        }
        sc.close();
    }
}