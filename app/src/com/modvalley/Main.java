package com.modvalley;

import com.modvalley.model.Usuario;
import com.modvalley.controller.*;
import com.modvalley.view.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UsuarioController usuarioCtrl = new UsuarioController();
        JuegoController juegoCtrl = new JuegoController();
        RecursoController recursoCtrl = new RecursoController();
        InteraccionController interaccionCtrl = new InteraccionController();
        CategoriaController categoriaCtrl = new CategoriaController();

        Login login = new Login();
        MenuUsuario menuUsuario = new MenuUsuario();
        Catalogo catalogo = new Catalogo();
        PerfilMenu perfilMenu = new PerfilMenu();
        GestionarContenido GestionMenu = new GestionarContenido();

        Usuario usuarioLogueado = null;

        while (true) {
            if (usuarioLogueado == null) {
                usuarioLogueado = login.mostrar(sc, usuarioCtrl.obtenerTodos(), usuarioCtrl);
                if (usuarioLogueado == null)
                    break;
            } else {
                int opcion = menuUsuario.mostrar(sc, usuarioLogueado);
                switch (opcion) {
                    case 1:
                        catalogo.mostrar(sc, juegoCtrl, recursoCtrl, interaccionCtrl, usuarioLogueado, categoriaCtrl,
                                usuarioCtrl);
                        break;
                    case 2:
                        GestionMenu.mostrar(sc, recursoCtrl, usuarioLogueado, juegoCtrl, categoriaCtrl);
                        break;
                    case 3:
                        perfilMenu.mostrar(sc, usuarioLogueado, usuarioCtrl, recursoCtrl, juegoCtrl, interaccionCtrl,
                                categoriaCtrl, usuarioLogueado);
                        usuarioLogueado = refrescarSesion(usuarioLogueado, usuarioCtrl);
                        break;
                    case 4:
                        usuarioLogueado = null;
                        break;
                }
            }
        }
    }

    private static Usuario refrescarSesion(Usuario actual, UsuarioController UsuarioCtrl) {
        if (actual == null)
            return null;

        Usuario actualizado = UsuarioCtrl.buscarPorId(actual.getIdUsuario());

        if (actualizado == null) {
            System.out.println(Custom.ROJO + "\n[!] Tu cuenta ya no existe. Cerrando sesión..." + Custom.RESET);
            return null;
        }
        return actualizado;
    }
}