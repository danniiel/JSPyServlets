/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces3.model;

/**
 *
 * @author daniel
 */
public class Publicacion {
    private String indentificador;
    private String titulo;
    private String categoria;
    private String contenido;
    private String estado;

    public Publicacion() {
    }

    public Publicacion(String indentificador, String titulo, String categoria, String contenido, String estado) {
        this.indentificador = indentificador;
        this.titulo = titulo;
        this.categoria = categoria;
        this.contenido = contenido;
        this.estado = estado;
    }

    public String getIndentificador() {
        return indentificador;
    }

    public void setIndentificador(String indentificador) {
        this.indentificador = indentificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
