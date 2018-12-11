/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Secciones;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class SeccionesControlador {

    public static boolean agregar(Secciones seccion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into secciones(nombre_seccion)"
                    + "values('" + seccion.getNombre_seccion() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Secciones buscarId(Secciones seccion) {
        if (Conexion.conectar()) {
            String sql = "select * from secciones where id_seccion='" + seccion.getId_seccion() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    seccion.setId_seccion(rs.getInt("id_seccion"));
                    seccion.setNombre_seccion(rs.getString("nombre_seccion"));
                } else {
                    seccion.setId_seccion(0);
                    seccion.setNombre_seccion("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return seccion;
    }

    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from secciones where upper (nombre_seccion) like '%"+
                nombre.toUpperCase()+"%'"+" order by id_seccion offset " + offset +
                "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->"+sql);
                try(PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()){
                        tabla += "<tr>"+"<td>"+rs.getString("id_seccion")+"</td>"+
                        "<td>" + rs.getString("nombre_seccion")+"</td>"+"</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td colspan = 2> No existen registros...</tr></td>";
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
    
    public static boolean modificar(Secciones seccion){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "update secciones set nombre_seccion='" + seccion.getNombre_seccion()+
                "'"+"where id_seccion=" + seccion.getId_seccion();
            try{
               Conexion.getSt().executeUpdate(sql);
               valor =  true;
            }catch(SQLException ex){
                System.out.println("Error " + ex );
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Secciones seccion){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "delete from secciones where id_seccion="+seccion.getId_seccion();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            }catch(SQLException ex){
                System.err.println("Error "+ ex);
            }
        }
        return valor;
    }
    
    public static Secciones buscarNombreRepetidoSeccion(Secciones seccion) {
        if (Conexion.conectar()) {
            String sql = "select * from secciones where nombre_seccion='" + seccion.getNombre_seccion() + "'";
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    seccion.setNombre_seccion("");
                } else {
                    seccion.setNombre_seccion(seccion.getNombre_seccion());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return seccion;
    }
}