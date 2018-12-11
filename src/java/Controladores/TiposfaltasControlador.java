/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Tiposfaltas;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class TiposfaltasControlador {

    public static boolean agregar(Tiposfaltas tipofalta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into tipos_faltas(descripcion_tipofalta)"
                    + "values('" + tipofalta.getDescripcion_tipofalta() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Tiposfaltas buscarId(Tiposfaltas tipofalta) {
        if (Conexion.conectar()) {
            String sql = "select * from tipos_faltas where id_tipofalta='" + tipofalta.getId_tipofalta() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    tipofalta.setId_tipofalta(rs.getInt("id_tipofalta"));
                    tipofalta.setDescripcion_tipofalta(rs.getString("descripcion_tipofalta"));
                } else {
                    tipofalta.setId_tipofalta(0);
                    tipofalta.setDescripcion_tipofalta("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return tipofalta;
    }

    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from tipos_faltas where upper (descripcion_tipofalta) like '%"+
                nombre.toUpperCase()+"%'"+" order by id_tipofalta offset " + offset +
                "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->"+sql);
                try(PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()){
                        tabla += "<tr>"+"<td>"+rs.getString("id_tipofalta")+"</td>"+
                        "<td>" + rs.getString("descripcion_tipofalta")+"</td>"+"</tr>";
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
    
    public static boolean modificar(Tiposfaltas tipofalta){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "update tipos_faltas set descripcion_tipofalta='" + tipofalta.getDescripcion_tipofalta()+
                "'"+"where id_tipofalta=" + tipofalta.getId_tipofalta();
            try{
               Conexion.getSt().executeUpdate(sql);
               valor =  true;
            }catch(SQLException ex){
                System.out.println("Error " + ex );
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Tiposfaltas tipofalta){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "delete from tipos_faltas where id_tipofalta="+tipofalta.getId_tipofalta();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            }catch(SQLException ex){
                System.err.println("Error "+ ex);
            }
        }
        return valor;
    }
}