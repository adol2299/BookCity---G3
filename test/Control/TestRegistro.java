package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Entidad.Usuario;
import logic.RegistroController;
import javafx.scene.control.TextField;



/**
 *
 * @author Celia
 */
public class TestRegistro {
    
    public static RegistroController RegistroController = new RegistroController();
    
    private String FALTA_NOMBRE = "Debe llenar el campo de Nombre";
    private String FALTA_CEDULA = "Debe llenar el campo de Cédula";
    private String FALTA_CONTRASENIA = "Debe llenar el campo de Contraseña";
    private String LONG_NOMBRE_INCORRECTA = "Longitud de nombre incorrecta";
    private String LONG_CEDULA_INCORRECTA = "Longitud de cédula incorrecta";
    private String LONG_PASSWORD_INCORRECTA = "Longitud de contraseña incorrecta";
    private String FORM_ID_INCORRECTA = "Solo se admiten números en la cédula";
    private String FORM_NOMBRE_INCORRECTA = "No se admiten números en el Nombre";
    private String FORM_PASSWORD_INCORRECTA = "No se aceptan caracteres especiales en la contraseña";
    /*private String NOMBRE_USUARIO_INDISPO = "Nombre de usuario no disponible";*/
    private String USUARIO_AUTORIZADO = "Usuario registrado exitosamente";
    private String REGISTRO_FRACASADO = "Error al registrar usuario, por favor revise sus datos";

    
    public TestRegistro() {
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
    public void faltaCampoNombre() {
        Usuario u = new Usuario("1234567852","","12345as");
        TextField usuario = new TextField(u.getNombre()); 
        RegistroController.TextUsuario = usuario;
        TextField contrasena = new TextField(u.getContrasena());
        RegistroController.TextPassw = contrasena;
        TextField cedula = new TextField(u.getCedula());
        RegistroController.TextCedula = cedula;
        assertEquals(RegistroController.Registrar(), FALTA_NOMBRE);
    }
    
    @Test
    public void faltaCampoCedula() {
        Usuario u = new Usuario("","AndresOrtega","12345as");
        TextField usuario = new TextField(u.getNombre()); 
        RegistroController.TextUsuario = usuario;
        TextField contrasena = new TextField(u.getContrasena());
        RegistroController.TextPassw = contrasena;
        TextField cedula = new TextField(u.getCedula());
        RegistroController.TextCedula = cedula;
        assertEquals(RegistroController.Registrar(), FALTA_CEDULA);
    }
    
    @Test
    public void faltaCampoContrasenia() {
        Usuario u = new Usuario("1234567852","AndresOrtega","");
        TextField usuario = new TextField(u.getNombre()); 
        RegistroController.TextUsuario = usuario;
        TextField contrasena = new TextField(u.getContrasena());
        RegistroController.TextPassw = contrasena;
        TextField cedula = new TextField(u.getCedula());
        RegistroController.TextCedula = cedula;
        assertEquals(RegistroController.Registrar(), FALTA_CONTRASENIA);
    }
    
    @Test
    public void testLongitudNombre() {
        Usuario u = new Usuario("1234567852","A","12345as");
        TextField usuario = new TextField(u.getNombre()); 
        RegistroController.TextUsuario = usuario;
        TextField contrasena = new TextField(u.getContrasena());
        RegistroController.TextPassw = contrasena;
        TextField cedula = new TextField(u.getCedula());
        RegistroController.TextCedula = cedula;
        assertEquals(RegistroController.Registrar(), LONG_NOMBRE_INCORRECTA);
        
        u.setNombre("AndresOrtegaLobos");
        TextField usuario = new TextField(u.getNombre()); 
        RegistroController.TextUsuario = usuario;
        assertEquals(RegistroController.Registrar(), LONG_NOMBRE_INCORRECTA);
                }
    
    @Test
    public void testLongitudCedula() {
        Usuario u = new Usuario("1234","AndresOrtega","12345as");
        TextField usuario = new TextField(u.getNombre()); 
        RegistroController.TextUsuario = usuario;
        TextField contrasena = new TextField(u.getContrasena());
        RegistroController.TextPassw = contrasena;
        TextField cedula = new TextField(u.getCedula());
        RegistroController.TextCedula = cedula;
        assertEquals(RegistroController.Registrar(), LONG_CEDULA_INCORRECTA);
        
        u.setCedula("12345678912345");
        TextField cedula = new TextField(u.getCedula());
        RegistroController.TextCedula = cedula;
        assertEquals(RegistroController.Registrar(), LONG_CEDULA_INCORRECTA);

    }
    
    /*@Test
    public void testUsuarioUnico() {
        Usuario u = new Usuario("111111","maria","#Contrasenia4");
        //u.setNombre("maria");
        //u.setContrasena("#Contrasenia4");
        assertEquals(RegistroController.Registrar(), NOMBRE_USUARIO_INDISPO);
        
    }*/
    
    @Test
    public void testLongitudContrasenia() {
        Usuario u = new Usuario("1234567852","AndresOrtega","contra");
        TextField usuario = new TextField(u.getNombre()); 
        RegistroController.TextUsuario = usuario;
        TextField contrasena = new TextField(u.getContrasena());
        RegistroController.TextPassw = contrasena;
        TextField cedula = new TextField(u.getCedula());
        RegistroController.TextCedula = cedula;
        assertEquals(RegistroController.Registrar(), LONG_PASSWORD_INCORRECTA);
        
        u.setContrasena("contrasenia123");
        TextField contrasena = new TextField(u.getContrasena());
        RegistroController.TextPassw = contrasena;
        assertEquals(RegistroController.Registrar(), LONG_PASSWORD_INCORRECTA);
    }
    
    @Test
    public void testFormatoCedula() {
        Usuario u = new Usuario("12345678m52","AndresOrtega","12345as");
        TextField usuario = new TextField(u.getNombre()); 
        RegistroController.TextUsuario = usuario;
        TextField contrasena = new TextField(u.getContrasena());
        RegistroController.TextPassw = contrasena;
        TextField cedula = new TextField(u.getCedula());
        RegistroController.TextCedula = cedula;
        assertEquals(RegistroController.Registrar(), FORM_ID_INCORRECTA);
    }
    
    @Test
    public void testFormatoNombre() {
        Usuario u = new Usuario("1234567852","AndresOrtega4","12345as");
        TextField usuario = new TextField(u.getNombre()); 
        RegistroController.TextUsuario = usuario;
        TextField contrasena = new TextField(u.getContrasena());
        RegistroController.TextPassw = contrasena;
        TextField cedula = new TextField(u.getCedula());
        RegistroController.TextCedula = cedula;
        assertEquals(RegistroController.Registrar(), FORM_NOMBRE_INCORRECTA);
    }
    
    @Test
    public void testFormatoContrasenia() {
        Usuario u = new Usuario("1234567852","AndresOrtega","12345as%");
        TextField usuario = new TextField(u.getNombre()); 
        RegistroController.TextUsuario = usuario;
        TextField contrasena = new TextField(u.getContrasena());
        RegistroController.TextPassw = contrasena;
        TextField cedula = new TextField(u.getCedula());
        RegistroController.TextCedula = cedula;
        assertEquals(RegistroController.Registrar(), FORM_PASSWORD_INCORRECTA);
    }
    
    @Test
    public void testTodoCorrecto() {
        Usuario u = new Usuario("1234567852","AndresOrtega","12345as");
        TextField usuario = new TextField(u.getNombre()); 
        RegistroController.TextUsuario = usuario;
        TextField contrasena = new TextField(u.getContrasena());
        RegistroController.TextPassw = contrasena;
        TextField cedula = new TextField(u.getCedula());
        RegistroController.TextCedula = cedula;
        assertEquals(RegistroController.Registrar(), USUARIO_AUTORIZADO);
    }
}
