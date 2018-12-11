/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Utiles {
    public static final int REGISTRO_PAGINA = 10;
    //cifrado md5
    public static String md5(String palabra) {
        String palabraMD5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(palabra.getBytes());
            int size = b.length;
            StringBuffer sb = new StringBuffer(size);
            for (int i = 0; i < size; i++) {
                int u = b[i] & 255;
                if (u < 16) {
                    sb.append("0" + Integer.toHexString(u));
                } else {
                    sb.append(Integer.toHexString(u));
                }
            }
            palabraMD5 = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
            return palabraMD5;
    }
    //quitar guiones simples
     public static String quitarGuiones(String texto) {
        return texto.replace("--", "");
    }
     //Fecha: Cambio desde fecha del string a fecha para util.
     public static java.util.Date stringToUtilDate(String fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        java.util.Date utilDate = new java.util.Date();
        try{
            utilDate = sdf.parse(fecha);
        }catch(ParseException ex){
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return utilDate;
    }
     //Fecha: Cambio desde fecha del util a fecha para sql.
     public static java.sql.Date utilToSqlDate(java.util.Date utilDate){
        return new java.sql.Date(utilDate.getTime());
    }
    //Fecha: Cambio desde fecha del sql a fecha para Util.
    public static java.util.Date sqlToUtilDate(java.sql.Date sqlDate){
        return new java.util.Date(sqlDate.getTime());
    }

    //quitar comas
    public static String quitarComas(String texto) {
        return texto.replace(",", "");
    }

    //Fecha: Cambio desde fecha del string a fecha para sql.
    public static java.sql.Date stringToSqlDate(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = null;
        try {
            utilDate = sdf.parse(fecha);
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sqlDate;
    }

    //Fecha: Cambio desde fecha del sql a string.
    public static String sqlDateToString(java.sql.Date fecha) {
        String string;
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(fecha);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        string = sdf.format(fecha);
        return string;
    }
// FECHA Y HORA

    public static String addTheT(String texto) {
        return texto.replace(" ", "T");
    }

    public static String takeTheT(String texto) {
        return texto.replace("T", " ");
    }

    //Fecha: Cambio desde fecha del sql a string.
    public static String sqlDateTimeToString(java.sql.Timestamp fecha) {
        String string;
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(fecha);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        string = sdf.format(fecha);
        return string;
    }

    //String to sql
    public static java.sql.Date stringToSqlDate2(String fecha) {
        System.out.println("recibí " + fecha);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm");
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = null;
        try {
            utilDate = sdf.parse(fecha);
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sqlDate;
    }

}
