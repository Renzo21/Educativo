/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Frecuencias;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class FrecuenciasControlador {

    public static boolean agregar(Frecuencias frecuencia) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into frecuencias(nombre_frecuencia)"
                    + "values('" + frecuencia.getNombre_frecuencia() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Frecuencias buscarId(Frecuencias frecuencia) {
        if (Conexion.conectar()) {
            String sql = "select * from frecuencias where id_frecuencia='" + frecuencia.getId_frecuencia() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    frecuencia.setId_frecuencia(rs.getInt("id_frecuencia"));
                    frecuencia.setNombre_frecuencia(rs.getString("nombre_frecuencia"));
                } else {
                    frecuencia.setId_frecuencia(0);
                    frecuencia.setNombre_frecuencia("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return frecuencia;
    }

    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from frecuencias where upper (nombre_frecuencia) like '%"+
                nombre.toUpperCase()+"%'"+" order by id_frecuencia offset " + offset +
                "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->"+sql);
                try(PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()){
                        tabla += "<tr>"+"<td>"+rs.getString("id_frecuencia")+"</td>"+
                        "<td>" + rs.getString("nombre_frecuencia")+"</td>"+"</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td colspan = 2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }catch (SQLException ex){
                    System.err.println("Error" + ex);
                }
                Conexion.cerrar();
            }catch(Exception ex){
                System.err.println("Error" + ex);
            }
        }
        Conexion.cerrar();
            return valor;
    }
    
    public static boolean modificar(Frecuencias frecuencia){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "update frecuencias set nombre_frecuencia='" + frecuencia.getNombre_frecuencia()+
                "'"+"where id_frecuencia=" + frecuencia.getId_frecuencia();
            try{
               Conexion.getSt().executeUpdate(sql);
               valor =  true;
            }catch(SQLException ex){
                System.out.println("Error " + ex );
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Frecuencias frecuencia){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "delete from frecuencias where id_frecuencia="+frecuencia.getId_frecuencia();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            }catch(SQLException ex){
                System.err.println("Error "+ ex);
            }
        }
        return valor;
    }
    
    public static Frecuencias buscarNombreRepetidoFrecuencia(Frecuencias frecuencia) {
        if (Conexion.conectar()) {
            String sql = "select * from frecuencias where nombre_frecuencia='"
                    + frecuencia.getNombre_frecuencia() + "'";
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    frecuencia.setNombre_frecuencia("");
                } else {
                    frecuencia.setNombre_frecuencia(frecuencia.getNombre_frecuencia());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return frecuencia;
    }
}