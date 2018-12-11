/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Especialidades;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class EspecialidadesControlador {

    public static boolean agregar(Especialidades especialidad) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into especialidades(nombre_especialidad)"
                    + "values('" + especialidad.getNombre_especialidad() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Especialidades buscarId(Especialidades especialidad) {
        if (Conexion.conectar()) {
            String sql = "select * from especialidades where id_especialidad='" + especialidad.getId_especialidad() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    especialidad.setId_especialidad(rs.getInt("id_especialidad"));
                    especialidad.setNombre_especialidad(rs.getString("nombre_especialidad"));
                } else {
                    especialidad.setId_especialidad(0);
                    especialidad.setNombre_especialidad("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return especialidad;
    }

    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from especialidades where upper (nombre_especialidad) like '%"+
                nombre.toUpperCase()+"%'"+" order by id_especialidad offset " + offset +
                "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->"+sql);
                try(PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()){
                        tabla += "<tr>"+"<td>"+rs.getString("id_especialidad")+"</td>"+
                        "<td>" + rs.getString("nombre_especialidad")+"</td>"+"</tr>";
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
    
    public static boolean modificar(Especialidades especialidad){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "update especialidades set nombre_especialidad='" + especialidad.getNombre_especialidad()+
                "'"+"where id_especialidad=" + especialidad.getId_especialidad();
            try{
               Conexion.getSt().executeUpdate(sql);
               valor =  true;
            }catch(SQLException ex){
                System.out.println("Error " + ex );
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Especialidades especialidad){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "delete from especialidades where id_especialidad="+especialidad.getId_especialidad();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            }catch(SQLException ex){
                System.err.println("Error "+ ex);
            }
        }
        return valor;
    }
    
    public static Especialidades buscarNombreRepetidoEspecialidad(Especialidades especialidad) {
        if (Conexion.conectar()) {
            String sql = "select * from especialidades where nombre_especialidad='" + especialidad.getNombre_especialidad() + "'";
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    especialidad.setNombre_especialidad("");
                } else {
                    especialidad.setNombre_especialidad(especialidad.getNombre_especialidad());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return especialidad;
    }
}