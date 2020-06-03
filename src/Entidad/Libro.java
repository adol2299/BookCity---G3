/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

/**
 *
 * @author david
 */
public class Libro {

    private String isbn;
    private String nombre;
    private String editorial;
    private String autor;
    private String precio;
    private String ano_publicacion;
    private String estado;
    private String existencia;

    public Libro(String isbn, String nombre, String editorial, String autor, String precio, String ano_publicacion, String estado, String existencia) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.editorial = editorial;
        this.autor = autor;
        this.precio = precio;
        this.ano_publicacion = ano_publicacion;
        this.estado = estado;
        this.existencia = existencia;
    }

    
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getAno_publicacion() {
        return ano_publicacion;
    }

    public void setAno_publicacion(String ano_publicacion) {
        this.ano_publicacion = ano_publicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getExistencia() {
        return existencia;
    }

    public void setExistencia(String existencia) {
        this.existencia = existencia;
    }

}
