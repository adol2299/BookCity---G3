/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entidad.Usuario;
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
    
    public Object[][] getLibrosByIsbn(String isbn){
        String[] columnas={"isbn","nombre","editorial","autor","precio",
        "ano_publicacion","estado","existencia"};
        Object[][] resultado = sen.GetTabla(columnas, "libro", 
                "select * FROM libro Where isbn like '%"+isbn+"%';");
        return resultado;
    }
    public Object[][] getLibrosByNombre(String nombre){
        String[] columnas={"nombre","nombre","editorial","autor","precio",
        "ano_publicacion","estado","existencia"};
        Object[][] resultado = sen.GetTabla(columnas, "libro", 
                "select * FROM libro Where nombre like '%"+nombre+"%';");
        return resultado;
    }
    public Object[][] getLibrosByEditorial(String editorial){
        String[] columnas={"editorial","nombre","editorial","autor","precio",
        "ano_publicacion","estado","existencia"};
        Object[][] resultado = sen.GetTabla(columnas, "libro", 
                "select * FROM libro Where editorial like '%"+editorial+"%';");
        return resultado;
    }
    public Object[][] getLibrosByAutor(String autor){
        String[] columnas={"autor","nombre","editorial","autor","precio",
        "ano_publicacion","estado","existencia"};
        Object[][] resultado = sen.GetTabla(columnas, "libro", 
                "select * FROM libro Where autor like '%"+autor+"%';");
        return resultado;
    }
    public Object[][] getLibrosByPrecio(String precio){
        String[] columnas={"precio","nombre","editorial","autor","precio",
        "ano_publicacion","estado","existencia"};
        Object[][] resultado = sen.GetTabla(columnas, "libro", 
                "select * FROM libro Where precio like '%"+precio+"%';");
        return resultado;
    }
    public Object[][] getLibrosByAno(String ano_publicacion){
        String[] columnas={"ano_publicacion","nombre","editorial","autor","precio",
        "ano_publicacion","estado","existencia"};
        Object[][] resultado = sen.GetTabla(columnas, "libro", 
                "select * FROM libro Where ano_publicacion like '%"+ano_publicacion+"%';");
        return resultado;
    }
    public Object[][] getLibrosByEstado(String estado){
        String[] columnas={"estado","nombre","editorial","autor","precio",
        "ano_publicacion","estado","existencia"};
        Object[][] resultado = sen.GetTabla(columnas, "libro", 
                "select * FROM libro Where estado like '%"+estado+"%';");
        return resultado;
    }
    public Object[][] getLibrosByExistencia(String existencia){
        String[] columnas={"existencia","nombre","editorial","autor","precio",
        "ano_publicacion","estado","existencia"};
        Object[][] resultado = sen.GetTabla(columnas, "libro", 
                "select * FROM libro Where existencia like '%"+existencia+"%';");
        return resultado;
    }
    
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
