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
import Modelos.Unidades;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class UnidadesControlador {

    public static boolean agregar(Unidades unidad) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into unidades(id_asignatura,descripcion_unidad)"
                    + "values('" + unidad.getAsignatura().getId_asignatura() + "',"
                    + "'" + unidad.getDescripcion_unidad() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Unidades buscarId(Unidades unidad) {
        if (Conexion.conectar()) {
            String sql = "select * from unidades u, asignaturas a "
                    + "where u.id_asignatura=a.id_asignatura and "
                    + "id_unidad='" + unidad.getId_unidad() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    Asignaturas asignatura = new Asignaturas();
                    asignatura.setId_asignatura(rs.getInt("id_asignatura"));
                    asignatura.setNombre_asignatura(rs.getString("nombre_asignatura"));
                    unidad.setId_unidad(rs.getInt("id_unidad"));
                    unidad.setDescripcion_unidad(rs.getString("descripcion_unidad"));
                    unidad.setAsignatura(asignatura);
                } else {
                    unidad.setId_unidad(0);
                    unidad.setDescripcion_unidad("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return unidad;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from unidades u, asignaturas a "
                        + "where u.id_asignatura=a.id_asignatura and "
                        + "upper (nombre_asignatura) like '%"
                        + nombre.toUpperCase() + "%'" + " order by id_unidad offset " + offset
                        + "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>" + 
                                "<td>" + rs.getString("id_unidad") + "</td>"
                                + "<td>" + rs.getString("nombre_asignatura") + "</td>" 
                                + "<td>" + rs.getString("descripcion_unidad") + "</td>" 
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan = 2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error" + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error" + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(Unidades unidad) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update unidades set id_asignatura='" + unidad.getAsignatura().getId_asignatura() + "'" 
                    + ", descripcion_unidad='" + unidad.getDescripcion_unidad() + "'"
                    + "where id_unidad=" + unidad.getId_unidad();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Unidades unidad) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from unidades where id_unidad=" + unidad.getId_unidad();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error " + ex);
            }
        }
        return valor;
    }
}
