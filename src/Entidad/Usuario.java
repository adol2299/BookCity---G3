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
public class Usuario {
    private String cedula;
    private String nombre;
    private String contrasena;
    private boolean administrador;

    public Usuario(String cedula, String nombre, String contrasena) {
        this.administrador = false;
        this.cedula = cedula;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public Usuario(String cedula, String contrasena) {
        this.cedula = cedula;
        this.contrasena = contrasena;
    }

    public Usuario() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
    
    
    
}
