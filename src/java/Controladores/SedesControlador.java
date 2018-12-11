/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Sedes;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class SedesControlador {

    public static boolean agregar(Sedes sede) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into sedes(nombre_sede,direccion_sede,telefono_sede,email_sede,estado_sede)"
                    + "values('" + sede.getNombre_sede() + "',"
                    + "'" + sede.getDireccion_sede() + "',"
                    + "'" + sede.getTelefono_sede() + "',"
                    + "'" + sede.getEmail_sede() + "',"
                    + "'" + sede.getEstado_sede() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Sedes buscarId(Sedes sede) {
        if (Conexion.conectar()) {
            String sql = "select * from sedes where id_sede='" + sede.getId_sede() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    sede.setId_sede(rs.getInt("id_sede"));
                    sede.setNombre_sede(rs.getString("nombre_sede"));
                    sede.setDireccion_sede(rs.getString("direccion_sede"));
                    sede.setTelefono_sede(rs.getString("telefono_sede"));
                    sede.setEmail_sede(rs.getString("email_sede"));
                    sede.setEstado_sede(rs.getString("estado_sede"));
                } else {
                    sede.setId_sede(0);
                    sede.setNombre_sede("");
                    sede.setDireccion_sede("");
                    sede.setTelefono_sede("");
                    sede.setEmail_sede("");
                    sede.setEstado_sede("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return sede;
    }

    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from sedes where upper (nombre_sede) like '%"+
                nombre.toUpperCase()+"%'"+" order by id_sede offset " + offset +
                "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->"+sql);
                try(PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()){
                        tabla += "<tr>"+
                                 "<td>"+rs.getString("id_sede")+"</td>"+
                                 "<td>" + rs.getString("nombre_sede")+"</td>"+
                                 "<td>" + rs.getString("direccion_sede")+"</td>"+
                                 "<td>" + rs.getString("telefono_sede")+"</td>"+
                                 "<td>" + rs.getString("email_sede")+"</td>"+
                                 "<td>" + rs.getString("estado_sede")+"</td>"
                                 +"</tr>";
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
    
    public static boolean modificar(Sedes sede){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "update sedes set nombre_sede='" + sede.getNombre_sede()+ "'"
                    + ", direccion_sede='" + sede.getDireccion_sede() + "'"
                    + ", telefono_sede='" + sede.getTelefono_sede() + "'"
                    + ", email_sede='" + sede.getEmail_sede() + "'"
                    + ", estado_sede='" + sede.getEstado_sede() + "'"
                    +"where id_sede=" + sede.getId_sede();
            try{
               Conexion.getSt().executeUpdate(sql);
               valor =  true;
            }catch(SQLException ex){
                System.out.println("Error " + ex );
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Sedes sede){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "delete from sedes where id_sede="+sede.getId_sede();
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