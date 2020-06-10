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
import Entidad.Libro;
import logic.Validation;

/**
 *
 * @author Celia
 */
public class TestGestionInventario {
    
    // controller restricciones
    public static Validation validar = new Validation();
    
    private String ISBN_UNICO = "El ISBN ya se encuentra registrado";
    private String LONG_ISBN = "El ISBN debe tener 10 caracteres";
    private String LONG_TITULO = "El Nombre debe tener entre 2 y 40 caracteres";
    private String LONG_EDITORIAL = "La Editorial debe tener entre 5 y 19 caracteres";
    private String LONG_AUTOR = "El Autor debe tener entre 5 y 19 caracteres";
    private String FORMATO_PRECIO_INCORRECTO = "El Precio tiene un formato incorrecto";
    private String PRECIO_LIBRO_INCORRECTO = "El Precio registrado debe ser mayor a $0.00";
    private String FOMRATO_ANO_PUBLICACION_INCORRECTO = "El Año de publicación tiene un formato incorrecto";
    private String ANO_PUBLICACION_INVALIDO = "El Año de publicación no puede ser mayor al año actual";
    private String TODO_CORRECTO = "correcto";
            
    public TestGestionInventario() {
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
    public void TestISBNUnico() {
        Libro libro = new Libro("9789587048","Un mundo feliz","Planeta","Aldous Huxley","20000","1932","nuevo","1");
        assertEquals(validar.validarISBN(libro.getIsbn()),ISBN_UNICO);
    }
   
    @Test
    public void TestLongitudISBN() {
        Libro libro = new Libro("1234567","Un mundo feliz","Planeta","Aldous Huxley","20000","1932","nuevo","1");
        assertEquals(validar.validarLibro(libro),LONG_ISBN);
    }
    
    @Test
    public void TestLongitudTitulo() {
        Libro libro = new Libro("1234567890","U","Planeta","Aldous Huxley","20000","1932","nuevo","1");
        assertEquals(validar.validarLibro(libro),LONG_TITULO);
        
        libro = new Libro("1234567890","Un mundo feliz Un mundo feliz Un mundo feliz","Planeta","Aldous Huxley","20000","1932","nuevo","1");     
        assertEquals(validar.validarLibro(libro),LONG_TITULO);
    }
    
    @Test
    public void TestLongitudEditorial() {
        Libro libro = new Libro("1234567890","Un mundo feliz","Plan","Aldous Huxley","20000","1932","nuevo","1");
        assertEquals(validar.validarLibro(libro),LONG_EDITORIAL);
                
        libro = new Libro("1234567890","Un mundo feliz","Planeta Planeta Planeta","Aldous Huxley","20000","1932","nuevo","1");
        assertEquals(validar.validarLibro(libro),LONG_EDITORIAL);
    }
    
    @Test
    public void TestLongAutor() {
        Libro libro = new Libro("1234567890","Un mundo feliz","Planeta","Ald","20000","1932","nuevo","1");
        assertEquals(validar.validarLibro(libro),LONG_AUTOR);
        
        libro = new Libro("1234567890","Un mundo feliz","Planeta","Aldous Huxley Aldous Huxley","20000","1932","nuevo","1");
        assertEquals(validar.validarLibro(libro),LONG_AUTOR);
    }
    
    @Test
    public void TestFormatoPrecio() {
        Libro libro = new Libro("1234567890","Un mundo feliz","Planeta","Aldous Huxley","fg","1932","nuevo","1");
        assertEquals(validar.validarLibro(libro),FORMATO_PRECIO_INCORRECTO);
    }
    
    @Test
    public void TestPrecioLibro() {
        Libro libro = new Libro("1234567890","Un mundo feliz","Planeta","Aldous Huxley","0","1932","nuevo","1");
        assertEquals(validar.validarLibro(libro),PRECIO_LIBRO_INCORRECTO);
    }
    
    @Test
    public void TestFormatoAnoPublicacion() {
        Libro libro = new Libro("1234567890","Un mundo feliz","Planeta","Aldous Huxley","20000","abcd","nuevo","1");
        assertEquals(validar.validarLibro(libro),FOMRATO_ANO_PUBLICACION_INCORRECTO);
    }
    
    @Test
    public void TestAnoPublicacion() {
        Libro libro = new Libro("1234567890","Un mundo feliz","Planeta","Aldous Huxley","20000","2021","nuevo","1");
        assertEquals(validar.validarLibro(libro),ANO_PUBLICACION_INVALIDO);
    }
    
    @Test
    public void TestTodoCorrecto() {
        Libro libro = new Libro("1234567890","Un mundo feliz","Planeta","Aldous Huxley","20000","1932","nuevo","1");
        assertEquals(validar.validarLibro(libro),TODO_CORRECTO);
    }
}
