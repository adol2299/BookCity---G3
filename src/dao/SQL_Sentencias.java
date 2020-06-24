/**
 *
 * @author david
 */
package dao;

import Entidad.Libreria;
import Entidad.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SQL_Sentencias {
    //En esta clase estarán las sentencias de SQL que se utilizarán para controlar la BD
    //
    private final SQL_Conexion con = new SQL_Conexion("root","");
    PreparedStatement ps;
    ResultSet res;
    private String user="";
    private String pass="";
    public SQL_Sentencias(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public SQL_Sentencias() {
    }

    public boolean insertar(String datos[], String insert) {
        boolean estado = false;
        try {
            ps = con.conectado().prepareStatement(insert);
            for (int i = 0; i <= datos.length - 1; i++) {
                ps.setString(i + 1, datos[i]);
            }
            ps.execute();
            ps.close();
            estado = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return estado;
    }
    
    public int insertarFactura(String datos[], String insert) {
        int lastInt = -1;
        try {
            ps = con.conectado().prepareStatement(insert,PreparedStatement.RETURN_GENERATED_KEYS);
            for (int i = 0; i <= datos.length - 1; i++) {
                if(i!=2){
                    ps.setString(i + 1, datos[i]);
                }else{
                    ps.setInt(i+1, Integer.parseInt(datos[i]));
                }
            }
            ps.execute();
            res=ps.getGeneratedKeys();
            while(res.next()){
                lastInt=res.getInt(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lastInt;
    }

    public boolean insertarFactura_has_libro(String datos[], String insert) {
        try {
            boolean estado = false;
            ps = con.conectado().prepareStatement(insert);
            ps.setInt(1, Integer.parseInt(datos[0]));
            ps.setString(2, datos[1]);
            ps.setInt(3, Integer.parseInt(datos[2]));
            ps.execute();
            ps.close();
            estado = true;
            return estado;
        } catch (SQLException ex) {
            Logger.getLogger(SQL_Sentencias.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
  
    public int insertarDomicilio(String datos[], String insert) {
        int lastId = -1;

        try {
            ps = con.conectado().prepareStatement(insert,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, datos[0]);
            ps.setString(2, datos[1]);
            ps.setFloat(3, Float.parseFloat(datos[2]));
            ps.setString(4, datos[3]);
            ps.setString(5, datos[4]);
            ps.execute();
            res = ps.getGeneratedKeys(); // Get ResultSet containing new primary key.
            if (res.next()) {
                lastId = res.getInt(1); // Get primary key of record created from ResultSet and set field in Address object.
            }
            ps.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SQL_Sentencias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastId;
    }
    
    //Método para registrar un nuevo usuario en la BD a partir de un objeto de la clase Usuario
    public boolean insertarUsuario(Usuario usu) {
        boolean estado = false;
        try {
            ps = con.conectado().prepareStatement("insert into Usuario values("+usu.getCedula()+",'"
            +usu.getNombre()+"','"+usu.getContrasena()+"',"+usu.isAdministrador()+")");
            ps.execute();
            ps.close();
            estado = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return estado;
    }
    
    //Método para Login a partir de un objeto de la clase Usuario
    public Usuario Login(Usuario usu) {
        Usuario a = new Usuario("","","");
        try {
            ps = con.conectado().prepareStatement("select cedula, nombre, contrasena, administrador from Usuario "
                    + "where cedula = "+usu.getCedula()+" and contrasena = '"+usu.getContrasena()+"'");
            res = ps.executeQuery();
            res.next();
            a.setCedula(res.getString("cedula"));
            a.setNombre(res.getString("nombre"));
            a.setContrasena(res.getString("contrasena"));
            a.setAdministrador(res.getBoolean("administrador"));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return a;
    }
    
     public Usuario getUsuario(String cedula) {
        Usuario a = new Usuario("","","");
        try {
            ps = con.conectado().prepareStatement("select nombre, contrasena from Usuario "
                    + "where cedula = '"+cedula+"';");
            res = ps.executeQuery();
            res.next();
            a.setCedula(cedula);
            a.setNombre(res.getString("nombre"));
            a.setContrasena(res.getString("contrasena"));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return a;
    }
    
    public Libreria getLibreria(String NIT)
    {   Libreria lib = new Libreria(NIT,"","","","");
        try {
            ps = con.conectado().prepareStatement("select nombre, direccion, telefono, representante_legal from libreria "
                    + "where nit = '"+NIT+"';");
            res = ps.executeQuery();
            res.next();
            lib.setNombre(res.getString("nombre"));
            lib.setDireccion(res.getString("direccion"));
            lib.setTelefono(res.getString("telefono"));
            lib.setRepresentante_legal("representante_legal");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lib;
    }
    
    //Método para consultar si un ISBN ya se encuentra registrado
    public int UniqueISBN(String ISBN) {
        int a = 0;
        try {
            ps = con.conectado().prepareStatement("select count(ISBN) as conteo from Libro "
                    + "where ISBN = '"+ISBN+"'");
            res = ps.executeQuery();
            res.next();
            a = res.getInt("conteo");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return a;
    }
    

    public int lastID(String tabla) {
        int id = -1;
        try {
            ps = con.conectado().prepareStatement(
                    "select * from "+tabla+" where id=(SELECT LAST_INSERT_ID());"
            );
            res = ps.executeQuery();
            res.next();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
               id=rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }

    
    public boolean update(String sql) {
        boolean estado = false;
        try {
            ps = con.conectado().prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            estado = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return estado;
    }

    public Object[][] GetTabla(String colName[], String tabla, String sql) {
        int registros = 0;
        try {
            ps = con.conectado().prepareStatement("select count(*) as total from " + tabla);
            res = ps.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        Object[][] data = new String[registros][colName.length];
        String col[] = new String[colName.length];

        try {
            ps = con.conectado().prepareStatement(sql);
            res = ps.executeQuery();
            int i = 0;
            while (res.next()) {
                for (int j = 0; j <= colName.length - 1; j++) {
                    col[j] = res.getString(colName[j]);
                    data[i][j] = col[j];
                }
                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }


    public boolean existencias(String campo, String from_where) {
        int registros = 0;
        try {
            ps = con.conectado().prepareStatement("SELECT count(" + campo + ") as total  " + from_where);
            res = ps.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (registros > 0) {
            return true;
        } else {
            return false;
        }
    }


    public Object[] poblar_combox(String tabla, String nombrecol, String sql) {
        int registros = 0;
        try {
            ps = con.conectado().prepareStatement("SELECT count(*) as total FROM " + tabla);
            res = ps.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        Object[] datos = new Object[registros];
        try {
            ps = con.conectado().prepareStatement(sql);
            res = ps.executeQuery();
            int i = 0;
            while (res.next()) {
                datos[i] = res.getObject(nombrecol);
                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }

    public Double datos_totalfactura(String campo, String sql) {
        double data = 0;
        try {
            ps = con.conectado().prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()) {
                data = res.getDouble(campo);
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

    


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
