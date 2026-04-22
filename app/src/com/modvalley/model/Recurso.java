package com.modvalley.model;

public class Recurso {
    private int id;
    private String nombre;
    private String descripcion;
    private String version;
    private int idJuego;
    private int idCategoria;
    private int idAutor;
    private int descargas;

    public Recurso(int id, String nombre, String descripcion, String version, int idJuego, int idCategoria,
            int idAutor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.version = version;
        this.idJuego = idJuego;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
        this.descargas = 0;
    }

    public void sumarDescarga() {
        this.descargas++;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Autor: " + idAutor + " | Version: " + version
                + " | Categoria: " + idCategoria + " | Descargas: " + descargas;
    }

}