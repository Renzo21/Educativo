/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Asignaturas;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class AsignaturasControlador {

    public static boolean agregar(Asignaturas asignatura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into asignaturas(nombre_asignatura)"
                    + "values('" + asignatura.getNombre_asignatura() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Asignaturas buscarId(Asignaturas asignatura) {
        if (Conexion.conectar()) {
            String sql = "select * from asignaturas where id_asignatura='" + asignatura.getId_asignatura() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    asignatura.setId_asignatura(rs.getInt("id_asignatura"));
                    asignatura.setNombre_asignatura(rs.getString("nombre_asignatura"));
                } else {
                    asignatura.setId_asignatura(0);
                    asignatura.setNombre_asignatura("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return asignatura;
    }

    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from asignaturas where upper (nombre_asignatura) like '%"+
                nombre.toUpperCase()+"%'"+" order by id_asignatura offset " + offset +
                "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->"+sql);
                try(PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()){
                        tabla += "<tr>"+"<td>"+rs.getString("id_asignatura")+"</td>"+
                        "<td>" + rs.getString("nombre_asignatura")+"</td>"+"</tr>";
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
    
    public static boolean modificar(Asignaturas asignatura){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "update asignaturas set nombre_asignatura='" + asignatura.getNombre_asignatura()+
                "'"+"where id_asignatura=" + asignatura.getId_asignatura();
            try{
               Conexion.getSt().executeUpdate(sql);
               valor =  true;
            }catch(SQLException ex){
                System.out.println("Error " + ex );
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Asignaturas asignatura){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "delete from asignaturas where id_asignatura="+asignatura.getId_asignatura();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            }catch(SQLException ex){
                System.err.println("Error "+ ex);
            }
        }
        return valor;
    }
    
    public static Asignaturas buscarNombreRepetidoAsignatura(Asignaturas asignatura) {
        if (Conexion.conectar()) {
            String sql = "select * from asignaturas where nombre_asignatura='"
                    + asignatura.getNombre_asignatura() + "'";
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    asignatura.setNombre_asignatura("");
                } else {
                    asignatura.setNombre_asignatura(asignatura.getNombre_asignatura());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return asignatura;
    }
    
    public static Asignaturas buscarMateria(int id) {
        Asignaturas asignatura = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalle_convocatorias dp,profesores p, asignaturas a, convocatorias c "
                        + "where dp.id_profesor=p.id_profesor and dp.id_asignatura=a.id_asignatura and "
                        + "dp.id_convocatoria=c.id_convocatoria and "
                        + "dp.id_convocatoria=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        asignatura = new Asignaturas();
                        asignatura.setId_asignatura(rs.getInt("id_asignatura"));
                        asignatura.setNombre_asignatura(rs.getString("nombre_asignatura"));
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return asignatura;
    }
    
    public static String buscarNombreMateria(String nombre, int pagina, int id){
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from detalle_convocatorias dp,profesores p, asignaturas a, convocatorias c "
                        + "where dp.id_profesor=p.id_profesor and dp.id_asignatura=a.id_asignatura and "
                        + "dp.id_convocatoria=c.id_convocatoria and "
                        + "dp.id_convocatoria="+id+" "+ " and upper (a.nombre_asignatura) like '%"+
                nombre.toUpperCase()+"%'"+" order by a.id_asignatura offset " + offset +
                " limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->"+sql);
                try(PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                   
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()){
                        tabla += "<tr>"+"<td>"+rs.getString("id_asignatura")+"</td>"+
                        "<td>" + rs.getString("nombre_asignatura")+"</td>"+"</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td colspan = 2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    System.out.println("tabkle"+tabla);
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
}