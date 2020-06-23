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
public class Factura_has_libro {
    private int Factura_id;
    private String Libro_isbn;
    private int cantidad;
    public int getFactura_id() {
        return Factura_id;
    }

    public void setFactura_id(int Factura_id) {
        this.Factura_id = Factura_id;
    }

    public String getLibro_isbn() {
        return Libro_isbn;
    }

    public void setLibro_isbn(String Libro_isbn) {
        this.Libro_isbn = Libro_isbn;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
