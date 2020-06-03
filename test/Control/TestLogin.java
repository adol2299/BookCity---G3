/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Entidad.Usuario;
import logic.LoginController;

import javafx.scene.control.TextField;


/**
 *
 * @author Celia
 */
public class TestLogin {
    
    public static LoginController ValidarLogin = new LoginController();
    
    private String DATOS_INCORRECTOS = "Datos incorrectos";
    private String USUARIO_AUTORIZADO = "Bienvenido";
    
    public TestLogin() {
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
    public void testDatos() {
        Usuario u = new Usuario("1234567852","AndresOrtega","12345asm");
        assertEquals(ValidarLogin.login(u), DATOS_INCORRECTOS);
    }
    
    @Test
    public void testTodoCorrecto() {
        Usuario u = new Usuario("1234567852","AndresOrtega","12345as");
        assertEquals(ValidarLogin.login(u), USUARIO_AUTORIZADO);
    }
    
}
