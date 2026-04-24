package com.modvalley.model;

public class Recurso {
    private int id;
    private String nombre;
    private String descripcion;
    private String version;
    private int numDescargas;
    private int idVideojuego;
    private int idCategoria;
    private int idUsuario;

    public Recurso(int id, String nombre, String descripcion, String version,
            int numDescargas, int idVideojuego, int idCategoria, int idUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.version = version;
        this.numDescargas = numDescargas;
        this.idVideojuego = idVideojuego;
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public int getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(int idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getNumDescargas() {
        return numDescargas;
    }

    public void setNumDescargas(int numDescargas) {
        this.numDescargas = numDescargas;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Version: " + version + " | NumDescargas: " + numDescargas;
    }

    public void sumarDescarga() {
        this.numDescargas++;
    }
}