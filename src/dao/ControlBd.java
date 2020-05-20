/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static javafx.application.Application.launch;


/**
 *
 * @author david
 */
public class ControlBd {

    private SQL_Sentencias sen;
    private String user = "";
    private String pass = "";
    private SQL_Conexion con;

    //Encapsulando User y Pass, de esta forma siempre estarÃ¡ en RAM.
    public static void main(String[] args) {
        ControlBd control = new ControlBd("root", "");
        Usuario usuario = new Usuario("11122213","delverde");
        control.isContrasena(usuario);
    }   
    public ControlBd(String user, String pass) {
        this.user = user;
        this.pass = pass;
        con = new SQL_Conexion(user, pass);
        sen = new SQL_Sentencias(user, pass);

    }

    //¿Existe?
    public boolean ExisteUsuario(Usuario usuario) {
        return sen.existencias(usuario.getCedula(), " from Usuario where cedula=" + usuario.getCedula() + "';");
    }

    //Consultas
    public boolean isContrasena(Usuario usuario) {
        String[] columnas = {"contrasena"};
        Object[][] contrasena = sen.GetTabla(columnas, "Usuario", "select contrasena FROM usuario Where cedula='" + usuario.getCedula() + "';");
        boolean resultado=false;
        if(contrasena[0][0].toString().equals(usuario.getContrasena())) resultado=true;
        return resultado;
    }


    public boolean setUsuario(Usuario usuario) {
        String[] datos = {usuario.getCedula(),usuario.getContrasena(),
        usuario.getNombre(),"0"};
        return sen.insertar(datos, "insert into Usuario(cedula, contrasena,"
                + " nombre, administrador) values(?,?,?,?);");
    }

    //Arqueos
}
