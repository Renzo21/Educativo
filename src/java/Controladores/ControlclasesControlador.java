/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Asignaturas;
import Modelos.Contenidos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Controlclases;
import Modelos.Especialidades;
import Modelos.Grados;
import Modelos.Profesores;
import Modelos.Unidades;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class ControlclasesControlador {

    public static boolean agregar(Controlclases controlclase) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into control_clases(id_grado,id_especialidad,id_contenido,"
                    + "id_profesor,fecha_clase,progreso_clase)"
                    + "values('" + controlclase.getGrado().getId_grado() + "',"
                    + "'" + controlclase.getEspecialidad().getId_especialidad() + "',"
                    + "'" + controlclase.getContenido().getId_contenido() + "',"
                    + "'" + controlclase.getProfesor().getId_profesor() + "',"
                    + "'" + controlclase.getFecha_clase() + "',"
                    + "'" + controlclase.getProgreso_clase() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Controlclases buscarId(Controlclases controlclase) {
        if (Conexion.conectar()) {
            String sql = "select * from control_clases cc,grados g, especialidades e, "
                    + "contenidos co, profesores p, unidades u, asignaturas a "
                    + "where cc.id_grado=g.id_grado and cc.id_especialidad=e.id_especialidad and "
                    + "cc.id_contenido=co.id_contenido and co.id_unidad=u.id_unidad and "
                    + "u.id_asignatura=a.id_asignatura and cc.id_profesor=p.id_profesor and "
                    + "id_controlclase='" + controlclase.getId_controlclase() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    Asignaturas asignatura = new Asignaturas();
                    asignatura.setId_asignatura(rs.getInt("id_asignatura"));
                    asignatura.setNombre_asignatura(rs.getString("nombre_asignatura"));
                    
                    Unidades unidad = new Unidades();
                    unidad.setId_unidad(rs.getInt("id_unidad"));
                    unidad.setDescripcion_unidad(rs.getString("descripcion_unidad"));
                    unidad.setAsignatura(asignatura);
                    
                    Grados grado = new Grados();
                    grado.setId_grado(rs.getInt("id_grado"));
                    grado.setNombre_grado(rs.getString("nombre_grado"));

                    Especialidades especialidad = new Especialidades();
                    especialidad.setId_especialidad(rs.getInt("id_especialidad"));
                    especialidad.setNombre_especialidad(rs.getString("nombre_especialidad"));

                    Contenidos contenido = new Contenidos();
                    contenido.setId_contenido(rs.getInt("id_contenido"));
                    contenido.setDescripcion_contenido(rs.getString("descripcion_contenido"));
                    contenido.setUnidad(unidad);

                    Profesores profesor = new Profesores();
                    profesor.setId_profesor(rs.getInt("id_profesor"));
                    profesor.setNombre_profesor(rs.getString("nombre_profesor"));
                    profesor.setApellido_profesor(rs.getString("apellido_profesor"));

                    controlclase.setId_controlclase(rs.getInt("id_controlclase"));
                    controlclase.setFecha_clase(rs.getDate("fecha_clase"));
                    controlclase.setProgreso_clase(rs.getString("progreso_clase"));
                    controlclase.setGrado(grado);
                    controlclase.setEspecialidad(especialidad);
                    controlclase.setContenido(contenido);
                    controlclase.setProfesor(profesor);
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return controlclase;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from control_clases cc, grados g, especialidades e,"
                        + " contenidos co, unidades u, asignaturas a, profesores p"
                        + " where cc.id_grado=g.id_grado and cc.id_especialidad=e.id_especialidad and "
                        + "cc.id_contenido=co.id_contenido and co.id_unidad=u.id_unidad and "
                        + "u.id_asignatura=a.id_asignatura and cc.id_profesor=p.id_profesor and "
                        + "upper (g.nombre_grado) like '%"
                        + nombre.toUpperCase() + "%'" + " order by cc.id_controlclase offset " + offset
                        + "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>" 
                                + "<td>" + rs.getString("id_controlclase") + "</td>"
                                + "<td>" + rs.getString("nombre_grado") + "</td>"
                                + "<td>" + rs.getString("nombre_especialidad")+ "</td>"
                                + "<td>" + rs.getString("nombre_asignatura") + "</td>"
                                + "<td>" + rs.getString("descripcion_unidad") + "</td>"
                                + "<td>" + rs.getString("descripcion_contenido") + "</td>"
                                + "<td>" + rs.getString("nombre_profesor") + "</td>"
                                + "<td>" + rs.getString("fecha_clase") + "</td>" 
                                + "<td>" + rs.getString("progreso_clase") + "</td>" +
                                "</tr>";
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

    public static boolean modificar(Controlclases controlclase) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update control_clases set id_grado='" + controlclase.getGrado().getId_grado() + "'" 
                    + ", id_especialidad='" + controlclase.getEspecialidad().getId_especialidad() + "'"
                    + ", id_contenido='" + controlclase.getContenido().getId_contenido() + "'"
                    + ", id_profesor='" + controlclase.getProfesor().getId_profesor() + "'"
                    + ", fecha_clase='" + controlclase.getFecha_clase() + "'"
                    + ", progreso_clase='" + controlclase.getProgreso_clase() + "'"
                    + "where id_controlclase=" + controlclase.getId_controlclase();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Controlclases controlclase) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from control_clases where id_controlclase=" + controlclase.getId_controlclase();
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
