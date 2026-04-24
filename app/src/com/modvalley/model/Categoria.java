package com.modvalley.model;

public class Categoria {
    private int idCategoria;
    private String nombre_cat;

    public Categoria(int idCategoria, String nombre_cat) {
        this.idCategoria = idCategoria;
        this.nombre_cat = nombre_cat;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre_cat() {
        return nombre_cat;
    }

    public void setNombre_cat(String nombre_cat) {
        this.nombre_cat = nombre_cat;
    }
}
