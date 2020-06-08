/**
 *
 * @author david
 */
package dao;

import Entidad.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
