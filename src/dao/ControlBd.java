/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entidad.Domicilio;
import Entidad.Factura;
import Entidad.Factura_has_libro;
import Entidad.Libreria;
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


    public ControlBd(String user, String pass) {
        this.user = user;
        this.pass = pass;
        con = new SQL_Conexion(user, pass);
        sen = new SQL_Sentencias(user, pass);

    }
    public static void main(String[] args) {
        ControlBd bd= new ControlBd("root", "");
        Libro libro=new Libro();
        libro.setIsbn("8.4975944E9");
        bd.getExistenciaLibro(libro);
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
    
    public Object[][] getLibreria(String libreriaNit){
        String[] columnas={"nit","nombre","direccion","telefono","representante_legal"};
        Object[][] resultado = sen.GetTabla(columnas, "libreria", 
                "select * FROM libreria Where nit = "+libreriaNit+";");
        return resultado;
    }
    
    public int getExistenciaLibro(Libro libro){
        String[] columnas={"existencia"};
        Object[][] resultado = sen.GetTabla(columnas, "libro", 
                "select * FROM libro Where isbn ='"+libro.getIsbn() +"';");
        return  Integer.parseInt((String) resultado[0][0]);
    }
    
  
    public boolean isContrasena(Usuario usuario) {
        String[] columnas = {"contrasena"};
        Object[][] contrasena = sen.GetTabla(columnas, "Usuario", "select contrasena FROM usuario Where cedula='" + usuario.getCedula() + "';");
        boolean resultado=false;
        if(contrasena[0][0].toString().equals(usuario.getContrasena())) resultado=true;
        return resultado;
    }
    //Metodo para insertar nueva Factura
    public int setFactura(Factura factura) {
        String[] datos = {
        factura.getFecha(),factura.getValor(), String.valueOf(factura.getDomicilio_id()),
        factura.getLibreria_nit(),factura.getUsuario_cedula()};
        return sen.insertarFactura(datos, "insert into Factura(fecha,"
                + "valor, Domicilio_id, Libreria_nit, Usuario_cedula) values(?,?,?,?,?);");
    }
    //Metodo para insertar nueva Factura_has_libro
    public boolean setFactura_has_Libro(Factura_has_libro factura) {
        String[] datos = {String.valueOf(factura.getFactura_id()),
            factura.getLibro_isbn(),String.valueOf(factura.getCantidad())};
        return sen.insertarFactura_has_libro(datos, "insert into Factura_has_libro("
                + "Factura_id, Libro_isbn, Cantidad) values (?,?,?);");
    }
    
    //Metodo para insertar domicilios
    public int setDomicilio(Domicilio domicilio) {
        String[] datos = {domicilio.getDireccion(),
            domicilio.getFecha(),String.valueOf(domicilio.getValor()),
            domicilio.getDatos_adicionales(),domicilio.getTipo_entrega()};
        return sen.insertarDomicilio(datos, "insert into Domicilio("
                + "direccion, fecha, valor, datos_adicionales, tipo_entrega) "
                + "values (?,?,?,?,?);");
    }

    public boolean setUsuario(Usuario usuario) {
        String[] datos = {usuario.getCedula(),usuario.getNombre(),
        usuario.getCedula(),"0"};
        return sen.insertar(datos, "insert into Usuario(cedula, nombre,"
                + " contrasena, administrador) values(?,?,?,?);");
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
                + "existencia='" + libro.getExistencia()
                + "' where isbn='" + libro.getIsbn() + "';";
        return sen.update(sql);
    }

    public boolean testConexion() {
        if (con.conectado() != null) {
            return true;
        }else{
            return false;
        }
    }
}
