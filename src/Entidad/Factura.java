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
public class Factura {
    private String id;
    private String direccion;
    private String fecha;
    private String valor;
    private String libreria_nit;
    private String domicilio_id;
    private String usuario_cedula;

    public Factura(String id, String direccion, String fecha, String valor, String libreria_nit, String domicilio_id, String usuario_cedula) {
        this.id = id;
        this.direccion = direccion;
        this.fecha = fecha;
        this.valor = valor;
        this.libreria_nit = libreria_nit;
        this.domicilio_id = domicilio_id;
        this.usuario_cedula = usuario_cedula;
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getLibreria_nit() {
        return libreria_nit;
    }

    public void setLibreria_nit(String libreria_nit) {
        this.libreria_nit = libreria_nit;
    }

    public String getDomicilio_id() {
        return domicilio_id;
    }

    public void setDomicilio_id(String domicilio_id) {
        this.domicilio_id = domicilio_id;
    }

    public String getUsuario_cedula() {
        return usuario_cedula;
    }

    public void setUsuario_cedula(String usuario_cedula) {
        this.usuario_cedula = usuario_cedula;
    }
    
    
}
