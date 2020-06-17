/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entidad.Factura;
import Entidad.Libro;
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
//        ControlBd control = new ControlBd("root", "");
//        System.out.println("heyy");
//        Factura factura= new Factura("sasa",  "sasa", "sasa","sasa","sasa","sasa");
//        control.setFactura(factura);
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
    
    public Object[][] getLibrosBy(String filter,String busqueda){
        if(filter == "Año") filter="ano_publicacion";
        String[] columnas={"isbn","nombre","editorial","autor","precio",
        "ano_publicacion","estado","existencia"};
        Object[][] resultado = sen.GetTabla(columnas, "libro", 
                "select * FROM libro Where "+filter.toLowerCase()+" like '%"+busqueda+"%';");
        return resultado;
    }
    
  
    public boolean isContrasena(Usuario usuario) {
        String[] columnas = {"contrasena"};
        Object[][] contrasena = sen.GetTabla(columnas, "Usuario", "select contrasena FROM usuario Where cedula='" + usuario.getCedula() + "';");
        boolean resultado=false;
        if(contrasena[0][0].toString().equals(usuario.getContrasena())) resultado=true;
        return resultado;
    }
    //Metodo para insertar nueva Factura
    public boolean setFactura(Factura factura) {
        String[] datos = {factura.getDireccion(),
        factura.getFecha(),factura.getValor(), String.valueOf(factura.getDomicilio_id()),
        factura.getLibreria_nit(),factura.getUsuario_cedula()};
        return sen.insertarFactura(datos, "insert into Factura(direccion, fecha,"
                + "valor, Domicilio_id, Libreria_nit, Usuario_cedula) values(?,?,?,?,?,?);");
    }


    public boolean setUsuario(Usuario usuario) {
        String[] datos = {usuario.getCedula(),usuario.getContrasena(),
        usuario.getNombre(),"0"};
        return sen.insertar(datos, "insert into Usuario(cedula, contrasena,"
                + " nombre, administrador) values(?,?,?,?);");
    }

     public boolean setLibro(Libro libro) {
        String[] datos = {libro.getIsbn(),libro.getNombre(),libro.getEditorial(),
        libro.getAutor(),libro.getPrecio(),libro.getAno_publicacion(),
        libro.getEstado(), libro.getExistencia()};
        return sen.insertar(datos, "insert into Libro(isbn, nombre, editorial, "
                + "autor, precio, ano_publicacion, estado, existencia) "
                + "values(?,?,?,?,?,?,?,?);");
    }
    
    //Update
    public boolean updateLibro(Libro libro){
        String sql="update Libro set nombre='"+libro.getNombre()+"', "
                + "editorial='"+libro.getEditorial()+"', "
                + "autor='"+libro.getAutor()+"', "
                + "precio='"+libro.getPrecio()+"', "
                + "ano_publicacion='"+libro.getAno_publicacion()+"', "
                + "estado='"+libro.getEstado()+"', "
                + "existencia='"+libro.getExistencia()+
                "' where isbn='"+libro.getIsbn()+"';";
        return sen.update(sql);
    }
}
