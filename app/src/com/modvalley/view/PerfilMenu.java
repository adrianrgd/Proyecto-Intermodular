package com.modvalley.view;

import com.modvalley.controller.GestionController;
import com.modvalley.model.Usuario;
import com.modvalley.model.Recurso;
import java.util.ArrayList;
import java.util.Scanner;

public class PerfilMenu {
    public void mostrar(Usuario user, GestionController gestion) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n========= PERFIL =========");
        System.out.println("Nickname: " + user.getNickname());
        System.out.println("Email: " + user.getEmail());

        int descargas = gestion.calcularDescargasTotales(user.getIdUsuario());
        ArrayList<Recurso> misMods = gestion.obtenerModsPorAutor(user.getIdUsuario());

        System.out.println("Descargas totales de tus mods: " + descargas);
        System.out.println("Número de mods publicados: " + misMods.size());
        System.out.println("\nMIS PUBLICACIONES:");

        if (misMods.isEmpty()) {
            System.out.println("- Aún no has subido contenido.");
        } else {
            for (Recurso r : misMods) {
                System.out.println("ID: " + r.getId() + " | Nombre: " + r.getNombre() + " | Categoría: "
                        + gestion.categorias[r.getIdCategoria() - 1] + " | Juego: " + r.getIdJuego()
                        + " | Descargas: " + r.getDescargas() + "\n");
            }
        }
        System.out.println("¿Volver al menu principal? (S/N)");
        String opcion = sc.nextLine();
        if (opcion.equalsIgnoreCase("S")) {
            MenuUsuario menuUsuario = new MenuUsuario();
            menuUsuario.mostrar(sc, user);
        } else {
            sc.close();
        }

    }
}