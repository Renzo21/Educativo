/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Ciudades;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class CiudadesControlador {

    public static boolean agregar(Ciudades ciudad) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into ciudades(nombre_ciudad)"
                    + "values('" + ciudad.getNombre_ciudad() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Ciudades buscarId(Ciudades ciudad) {
        if (Conexion.conectar()) {
            String sql = "select * from ciudades where id_ciudad='" + ciudad.getId_ciudad() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                } else {
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return ciudad;
    }

    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from ciudades where upper (nombre_ciudad) like '%"+
                nombre.toUpperCase()+"%'"+" order by id_ciudad offset " + offset +
                "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->"+sql);
                try(PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()){
                        tabla += "<tr>"+"<td>"+rs.getString("id_ciudad")+"</td>"+
                        "<td>" + rs.getString("nombre_ciudad")+"</td>"+"</tr>";
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
    
    public static boolean modificar(Ciudades ciudad){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "update ciudades set nombre_ciudad='" + ciudad.getNombre_ciudad()+
                "'"+"where id_ciudad=" + ciudad.getId_ciudad();
            try{
               Conexion.getSt().executeUpdate(sql);
               valor =  true;
            }catch(SQLException ex){
                System.out.println("Error " + ex );
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Ciudades ciudad){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "delete from ciudades where id_ciudad="+ciudad.getId_ciudad();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            }catch(SQLException ex){
                System.err.println("Error "+ ex);
            }
        }
        return valor;
    }
    
    public static Ciudades buscarNombreRepetidoCiudad(Ciudades ciudad) {
        if (Conexion.conectar()) {
            String sql = "select * from ciudades where nombre_ciudad='" + ciudad.getNombre_ciudad() + "'";
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    ciudad.setNombre_ciudad("");
                } else {
                    ciudad.setNombre_ciudad(ciudad.getNombre_ciudad());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return ciudad;
    }
}