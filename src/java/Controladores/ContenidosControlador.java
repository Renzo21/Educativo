/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Asignaturas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Contenidos;
import Modelos.Unidades;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class ContenidosControlador {

    public static boolean agregar(Contenidos contenido) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into contenidos(id_unidad,descripcion_contenido)"
                    + "values('" + contenido.getUnidad().getId_unidad() + "'," 
                    + "'" + contenido.getDescripcion_contenido() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Contenidos buscarId(Contenidos contenido) {
        if (Conexion.conectar()) {
            String sql = "select * from contenidos co, unidades u, asignaturas a "
                    + "where co.id_unidad=u.id_unidad and u.id_asignatura=a.id_asignatura and "
                    + "id_contenido='" + contenido.getId_contenido() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    
                    Asignaturas asignatura = new Asignaturas();
                    asignatura.setNombre_asignatura(rs.getString("nombre_asignatura"));
                    
                    Unidades unidad = new Unidades();
                    unidad.setId_unidad(rs.getInt("id_unidad"));
                    unidad.setDescripcion_unidad(rs.getString("descripcion_unidad"));
                    unidad.setAsignatura(asignatura);
                    contenido.setId_contenido(rs.getInt("id_contenido"));
                    contenido.setDescripcion_contenido(rs.getString("descripcion_contenido"));
                    contenido.setUnidad(unidad);
                } else {
                    contenido.setId_contenido(0);
                    contenido.setDescripcion_contenido("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return contenido;
    }

    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from contenidos co, unidades u, asignaturas a "
                        + "where co.id_unidad=u.id_unidad and u.id_asignatura=a.id_asignatura and "
                        + "upper (nombre_asignatura) like '%"+
                nombre.toUpperCase()+"%'"+" order by id_contenido offset " + offset +
                "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->"+sql);
                try(PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()){
                        tabla += "<tr>"
                                +"<td>"+rs.getString("id_contenido")+"</td>"+
                                "<td>" + rs.getString("nombre_asignatura")+"</td>"+
                                "<td>" + rs.getString("descripcion_unidad")+"</td>"+
                                "<td>" + rs.getString("descripcion_contenido")+"</td>"+
                                "</tr>";
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
    
    public static boolean modificar(Contenidos contenido){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "update contenidos set id_unidad='" + contenido.getUnidad().getId_unidad()+ "'"
                    + ", descripcion_contenido='" + contenido.getDescripcion_contenido() + "'"
                    +"where id_contenido=" + contenido.getId_contenido();
            try{
               Conexion.getSt().executeUpdate(sql);
               valor =  true;
            }catch(SQLException ex){
                System.out.println("Error " + ex );
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Contenidos contenido){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "delete from contenidos where id_contenido="+contenido.getId_contenido();
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