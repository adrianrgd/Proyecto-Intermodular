package app.src.com.modvalley;

import app.src.com.modvalley.model.Usuario;
import app.src.com.modvalley.view.MenuVista;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ArrayList de prueba para usuarios (Luego se obtendran de la BBDD)
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario(1, "Juanito_008", "juanelcrack@gmail.com"));
        listaUsuarios.add(new Usuario(2, "Modder67", "modder67@gmail.com"));
        listaUsuarios.add(new Usuario(3, "LuisitoComunica", "luisito@gmail.com"));
        listaUsuarios.add(new Usuario(4, "ElColetas12", "elcoletas@gmail.com"));
        listaUsuarios.add(new Usuario(5, "Paco_Perez", "pacoperez@gmail.com"));

        Usuario usuariologeado = null;

        while (usuariologeado == null) {
            System.out.println("\n=== BIENVENIDO A MODVALLEY ===");

            System.out.println("Selecciona un usuario para iniciar sesion: ");

            for (Usuario u : listaUsuarios) {
                System.out.println(u);
            }
            System.out.println("0 para Salir.");

            System.out.println("Introduce el ID: ");
            int idElejido = sc.nextInt();

            if (idElejido == 0) {
                System.out.println("Cerrando Programa");
                return;
            }

            for (Usuario u : listaUsuarios) {
                if (u.getIdUsuario() == idElejido) {
                    usuariologeado = u;
                    break;
                }
            }

            if (usuariologeado == null) {
                System.out.println("Error! ID no valido.");
            }
        }

        MenuVista vista = new MenuVista();
        vista.mostrarMenuPrincipal(usuariologeado);

        sc.close();
    }

}