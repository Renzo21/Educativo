/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Aulas;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class AulasControlador {

    public static boolean agregar(Aulas aula) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into aulas(nombre_aula)"
                    + "values('" + aula.getNombre_aula() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Aulas buscarId(Aulas aula) {
        if (Conexion.conectar()) {
            String sql = "select * from aulas where id_aula='" + aula.getId_aula() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    aula.setId_aula(rs.getInt("id_aula"));
                    aula.setNombre_aula(rs.getString("nombre_aula"));
                } else {
                    aula.setId_aula(0);
                    aula.setNombre_aula("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return aula;
    }

    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from aulas where upper (nombre_aula) like '%"+
                nombre.toUpperCase()+"%'"+" order by id_aula offset " + offset +
                "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->"+sql);
                try(PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()){
                        tabla += "<tr>"+"<td>"+rs.getString("id_aula")+"</td>"+
                        "<td>" + rs.getString("nombre_aula")+"</td>"+"</tr>";
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
    
    public static boolean modificar(Aulas aula){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "update aulas set nombre_aula='" + aula.getNombre_aula()+
                "'"+"where id_aula=" + aula.getId_aula();
            try{
               Conexion.getSt().executeUpdate(sql);
               valor =  true;
            }catch(SQLException ex){
                System.out.println("Error " + ex );
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Aulas aula){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "delete from aulas where id_aula="+aula.getId_aula();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            }catch(SQLException ex){
                System.err.println("Error "+ ex);
            }
        }
        return valor;
    }
    
    public static Aulas buscarNombreRepetidoAula(Aulas aula) {
        if (Conexion.conectar()) {
            String sql = "select * from aulas where nombre_aula='"
                    + aula.getNombre_aula() + "'";
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    aula.setNombre_aula("");
                } else {
                    aula.setNombre_aula(aula.getNombre_aula());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return aula;
    }
}