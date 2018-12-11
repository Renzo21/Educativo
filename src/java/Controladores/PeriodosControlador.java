/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Periodos;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class PeriodosControlador {

    public static boolean agregar(Periodos periodo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into periodos(nombre_periodo)"
                    + "values('" + periodo.getNombre_periodo() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Periodos buscarId(Periodos periodo) {
        if (Conexion.conectar()) {
            String sql = "select * from periodos where id_periodo='" + periodo.getId_periodo() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    periodo.setId_periodo(rs.getInt("id_periodo"));
                    periodo.setNombre_periodo(rs.getString("nombre_periodo"));
                } else {
                    periodo.setId_periodo(0);
                    periodo.setNombre_periodo("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return periodo;
    }

    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from periodos where upper (nombre_periodo) like '%"+
                nombre.toUpperCase()+"%'"+" order by id_periodo offset " + offset +
                "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->"+sql);
                try(PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()){
                        tabla += "<tr>"+"<td>"+rs.getString("id_periodo")+"</td>"+
                        "<td>" + rs.getString("nombre_periodo")+"</td>"+"</tr>";
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
    
    public static boolean modificar(Periodos periodo){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "update periodos set nombre_periodo='" + periodo.getNombre_periodo()+
                "'"+"where id_periodo=" + periodo.getId_periodo();
            try{
               Conexion.getSt().executeUpdate(sql);
               valor =  true;
            }catch(SQLException ex){
                System.out.println("Error " + ex );
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Periodos periodo){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "delete from periodos where id_periodo="+periodo.getId_periodo();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            }catch(SQLException ex){
                System.err.println("Error "+ ex);
            }
        }
        return valor;
    }
    
    public static Periodos buscarNombreRepetidoPeriodo(Periodos periodo) {
        if (Conexion.conectar()) {
            String sql = "select * from periodos where nombre_periodo='" + periodo.getNombre_periodo() + "'";
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    periodo.setNombre_periodo("");
                } else {
                    periodo.setNombre_periodo(periodo.getNombre_periodo());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return periodo;
    }
}