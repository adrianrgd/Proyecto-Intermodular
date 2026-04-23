package com.modvalley;

import com.modvalley.model.Usuario;
import com.modvalley.controller.*;
import com.modvalley.view.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Inicialización de Controladores
        InteraccionController interaccion = new InteraccionController();
        JuegoControler juego = new JuegoControler();
        RecursoController recurso = new RecursoController();
        UsuarioController usuarioCtrl = new UsuarioController();

        // Inicialización de Vistas
        Login login = new Login();
        MenuUsuario menuUsuario = new MenuUsuario();
        Catalogo catalogo = new Catalogo();
        PerfilMenu perfilMenu = new PerfilMenu();
        SubirContenidoMenu subirContenidoMenu = new SubirContenidoMenu();

        Usuario usuarioLogueado = null;

        while (true) {
            if (usuarioLogueado == null) {
                // Pasamos la lista de usuarios del controlador
                usuarioLogueado = login.mostrar(sc, usuarioCtrl.usuarios);
                if (usuarioLogueado == null)
                    break;
            } else {
                int opcion = menuUsuario.mostrar(sc, usuarioLogueado);
                switch (opcion) {
                    case 1:
                        catalogo.mostrar(sc, juego, recurso, interaccion, usuarioCtrl, usuarioLogueado);
                        break;
                    case 2:
                        subirContenidoMenu.mostrar(sc, recurso, usuarioLogueado, juego);
                        break;
                    case 3:
                        perfilMenu.mostrar(sc, usuarioLogueado, recurso, juego, interaccion);
                        break;
                    case 4:
                        usuarioLogueado = null; // Cierra sesión y vuelve al Login
                        System.out.println(Custom.AMARILLO + "Sesion cerrada satisfactoriamente." + Custom.RESET);
                        break;
                    default:
                        System.out.println(Custom.ROJO + "Opcion no valida" + Custom.RESET);
                        break;
                }
            }
        }
        sc.close();
    }
}