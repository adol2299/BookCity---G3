/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import dao.SQL_Sentencias;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Entidad.Usuario;
import Entidad.Libro;
import dao.ControlBd;



/**
 *
 * @author Celia
 */
public class Inicializacion {
    
    public Inicializacion() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void initData() {
                        
        // usuarios        
        //Usuario a = new Usuario("111111","maria","Contrasenia4");
        //Usuario b = new Usuario("1234567852","AndresOrtega","12345as");
        
        //SQL_Sentencias sql = new SQL_Sentencias();
        //sql.insertarUsuario(a);
        //sql.insertarUsuario(b);
        
        ControlBd control = new ControlBd("root", "");
        Libro libro = new Libro("9789587048","Demasiados héroes","Alfaguara","Laura Restrepo","20000","2009","nuevo","1");
        control.setLibro(libro);
       
    }
}
