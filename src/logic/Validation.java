/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import Entidad.Libro;
import dao.SQL_Sentencias;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author juan8
 */
public class Validation {

    public boolean longNombre(String usuario) {
        return usuario.length() > 1 && usuario.length() <= 16;
    }

    public boolean longCedula(String cedula) {
        return cedula.length() > 4 && cedula.length() <= 13;
    }

    public boolean testRegExPass(String password) {
        // expresión regular que revisa si tiene alguno de los siguientes caracteres
        String REG_EXP = "\\_+|\\\u00bf+|\\?+|\\\u00b0+|\\\u00ac+|\\|+|\\!+|\\#+|\\$+|" + "\\%+|\\&+|\\+|\\=+|\\\u2019+|\\\u00a1+|\\++|\\*+|\\~+|\\[+|\\]" + "+|\\{+|\\}+|\\^+|\\<+|\\>+|\\\"+ ";
        Pattern pattern = Pattern.compile(REG_EXP);
        Matcher matcher = pattern.matcher(password);
        //retorna true si tiene alguno de los caracteres anteriores o false si no tiene ninguno
        return matcher.find();
    }

    public boolean longPassw(String password) {
        return password.length() > 6 && password.length() <= 13;
    }

    public boolean numericOnly(String cedula) {
        double n = 0;
        try {
            n = Double.parseDouble(cedula);
        } catch (Exception ex) {
            return true;
        }
        return false;
    }

    public boolean textOnly(String nombre) {
        String n;
        String[] array = nombre.split("");
        for (String tab : array) {
            if (tab.matches("\\d")) {
                return true;
            }
        }
        return false;
    }
    public String validarISBN(String ISBN)
    {   SQL_Sentencias sql = new SQL_Sentencias();
        if(sql.UniqueISBN(ISBN)>0)
            return "El ISBN ya se encuentra registrado"; 
        else
            return "";
    }
    
    public String validarLibro(Libro libro)
    {   if(libro.getIsbn().length()!=10)
            return "El ISBN debe tener 10 caracteres";
        if(libro.getNombre().length()<=1 || libro.getNombre().length()>40)
            return "El Nombre debe tener entre 2 y 40 caracteres";
        if(libro.getEditorial().length()<=4 || libro.getEditorial().length()>=20)
            return "La Editorial debe tener entre 5 y 19 caracteres";
        if(libro.getAutor().length()<=4 || libro.getAutor().length()>=20)
            return "El Autor debe tener entre 5 y 19 caracteres";
        if(numericOnly(libro.getPrecio()))
            return "El Precio tiene un formato incorrecto";
        else
        {   int p = Integer.parseInt(libro.getPrecio());
            if(p<=0)
                return "El Precio registrado debe ser mayor a $0.00";
        }
        if(numericOnly(libro.getAno_publicacion()))
            return "El Año de publicación tiene un formato incorrecto";
        else
        {   int p = Integer.parseInt(libro.getAno_publicacion());
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            if(p>year)
                return "El Año de publicación no puede ser mayor al año actual";
        }
        return "correcto";
        
    }
    
}
