/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Grados;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class GradosControlador {

    public static boolean agregar(Grados grado) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into grados(nombre_grado)"
                    + "values('" + grado.getNombre_grado() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Grados buscarId(Grados grado) {
        if (Conexion.conectar()) {
            String sql = "select * from grados where id_grado='" + grado.getId_grado() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    grado.setId_grado(rs.getInt("id_grado"));
                    grado.setNombre_grado(rs.getString("nombre_grado"));
                } else {
                    grado.setId_grado(0);
                    grado.setNombre_grado("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return grado;
    }

    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from grados where upper (nombre_grado) like '%"+
                nombre.toUpperCase()+"%'"+" order by id_grado offset " + offset +
                "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->"+sql);
                try(PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()){
                        tabla += "<tr>"+"<td>"+rs.getString("id_grado")+"</td>"+
                        "<td>" + rs.getString("nombre_grado")+"</td>"+"</tr>";
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
    
    public static boolean modificar(Grados grado){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "update grados set nombre_grado='" + grado.getNombre_grado()+
                "'"+"where id_grado=" + grado.getId_grado();
            try{
               Conexion.getSt().executeUpdate(sql);
               valor =  true;
            }catch(SQLException ex){
                System.out.println("Error " + ex );
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Grados grado){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "delete from grados where id_grado="+grado.getId_grado();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            }catch(SQLException ex){
                System.err.println("Error "+ ex);
            }
        }
        return valor;
    }
    
    public static Grados buscarNombreRepetidoGrado(Grados grado) {
        if (Conexion.conectar()) {
            String sql = "select * from grados where nombre_grado='" + grado.getNombre_grado() + "'";
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    grado.setNombre_grado("");
                } else {
                    grado.setNombre_grado(grado.getNombre_grado());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return grado;
    }
}