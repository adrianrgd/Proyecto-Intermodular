package app.src.com.modvalley.view;

import app.src.com.modvalley.model.Usuario;

public class MenuVista {

    public void mostrarMenuPrincipal(Usuario usuariologeado) {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("Login Exitoso!");

        System.out.println("Bienvenido, " + usuariologeado.getNickname() + "!");
        System.out.println("------------------------------------------------------------\n");
        System.out.println("¿Que quieres hacer ahora?");
        System.out.println("1. Catalogo");
        System.out.println("2. Subir Contenido.");
        System.out.println("3. Cerrar Sesion.");
        System.out.print("Seleccion una opcion: ");

    }
}