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
public class Categorias {

    private int id_Categoria;
    private String descripcion;
    private String estado;

    public Categorias() {
    }

    public Categorias(int id_Categoria, String descripcion, String estado) {
        this.id_Categoria = id_Categoria;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Categorias(String descripcion, String estado) {
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId_Categoria() {
        return id_Categoria;
    }

    public void setId_Categoria(int id_Categoria) {
        this.id_Categoria = id_Categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
