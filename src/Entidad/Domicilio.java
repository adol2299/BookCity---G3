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
public class Domicilio {
    private String direccion;
    private String fecha;
    private float valor;
    private String datos_adicionales;
    private String tipo_entrega;


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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDatos_adicionales() {
        return datos_adicionales;
    }

    public void setDatos_adicionales(String datos_adicionales) {
        this.datos_adicionales = datos_adicionales;
    }

    public String getTipo_entrega() {
        return tipo_entrega;
    }

    public void setTipo_entrega(String tipo_entrega) {
        this.tipo_entrega = tipo_entrega;
    }

    public Domicilio(String direccion, String fecha, float valor, String datos_adicionales, String tipo_entrega) {
        this.direccion = direccion;
        this.fecha = fecha;
        this.valor = valor;
        this.datos_adicionales = datos_adicionales;
        this.tipo_entrega = tipo_entrega;
    }
    
    
    
}
