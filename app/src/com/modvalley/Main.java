package app.src.com.modvalley;

import app.src.com.modvalley.model.Usuario;
import app.src.com.modvalley.controller.GestionController;
import app.src.com.modvalley.view.Login;
import app.src.com.modvalley.view.MenuUsuario;
import app.src.com.modvalley.view.Catalogo;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestionController gestion = new GestionController();

        Login login = new Login();
        MenuUsuario menuUsuario = new MenuUsuario();
        Catalogo catalogo = new Catalogo();

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
                        System.out.println("Funcionalidad de subida...");
                        break;
                    case 3:
                        usuarioLogueado = null;
                        System.out.println("Sesión cerrada.");
                        break;
                }
            }
        }
        sc.close();
    }
}