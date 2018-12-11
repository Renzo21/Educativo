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
import Modelos.Asistencias;
import Modelos.Convocatorias;
import Modelos.DetallesConvocatoria;
import Modelos.Grados;
import Modelos.Profesores;
import Modelos.Secciones;
import Modelos.Turnos;
import java.sql.Date;
import java.sql.Statement;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class AsistenciasControlador {

   public static Asistencias buscarId(int id) {
        Asistencias asistencia = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from asistencias asi, convocatorias c, detalle_convocatorias dc, profesores p, "
                        + "asignaturas a, grados g, secciones se, turnos t "
                        + "where asi.id_convocatoria=c.id_convocatoria and asi.id_detalleconvocatoria=dc.id_detalleconvocatoria and "
                        + "c.id_grado=g.id_grado and c.id_seccion=se.id_seccion and c.id_turno=t.id_turno and "
                        + "dc.id_profesor=p.id_profesor and dc.id_asignatura=a.id_asignatura and"
                        + " id_asistencia=?";
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        asistencia = new Asistencias();
                        asistencia.setId_asistencia(rs.getInt("id_asistencia"));
                        asistencia.setFecha_asistencia(rs.getDate("fecha_asistencia"));
                        
                        Grados grado = new Grados();
                        grado.setId_grado(rs.getInt("id_grado"));
                        grado.setNombre_grado(rs.getString("nombre_grado"));
                        
                        Secciones seccion = new Secciones();
                        seccion.setId_seccion(rs.getInt("id_seccion"));
                        seccion.setNombre_seccion(rs.getString("nombre_seccion"));
                        
                        Turnos turno = new Turnos();
                        turno.setId_turno(rs.getInt("id_turno"));
                        turno.setNombre_turno(rs.getString("nombre_turno"));
                        
                        Convocatorias convocatoria = new Convocatorias();
                        convocatoria.setGrado(grado);
                        convocatoria.setSeccion(seccion);
                        convocatoria.setTurno(turno);
                        convocatoria.setId_convocatoria(rs.getInt("id_convocatoria"));
                        asistencia.setConvocatoria(convocatoria);
                        
                        Asignaturas asignatura = new Asignaturas();
                        asignatura.setId_asignatura(rs.getInt("id_asignatura"));
                        asignatura.setNombre_asignatura(rs.getString("nombre_asignatura"));
                        
                        Profesores profesor = new Profesores();
                        profesor.setId_profesor(rs.getInt("id_profesor"));
                        profesor.setNombre_profesor(rs.getString("nombre_profesor"));
                        profesor.setApellido_profesor(rs.getString("apellido_profesor"));
                        
                        DetallesConvocatoria detalleconvocatoria = new DetallesConvocatoria();
                        detalleconvocatoria.setId_detalleconvocatoria(rs.getInt("id_detalleconvocatoria"));
                        detalleconvocatoria.setAsignatura(asignatura);
                        detalleconvocatoria.setProfesor(profesor);
                        asistencia.setDetalleconvocatria(detalleconvocatoria);
                        
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return asistencia;
    }

    public static String buscarNombre(Date nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from asistencias asi, convocatorias c, detalle_convocatorias dc, profesores p, "
                        + "asignaturas a, grados g, secciones se, turnos t "
                        + "where asi.id_convocatoria=c.id_convocatoria and asi.id_detalleconvocatoria=dc.id_detalleconvocatoria and "
                        + "c.id_grado=g.id_grado and c.id_seccion=se.id_seccion and c.id_turno=t.id_turno and "
                        + "dc.id_profesor=p.id_profesor and dc.id_asignatura=a.id_asignatura and "
                        + "asi.fecha_asistencia = '%"
                        + nombre
                        + "%' "
                        + "order by id_asistencia "
                        + "offset " + offset + " limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_asistencia") + "</td>"
                                + "<td>" + rs.getString("nombre_grado") + "</td>"
                                + "<td>" + rs.getString("nombre_seccion") + "</td>"
                                + "<td>" + rs.getString("nombre_turno") + "</td>"
                                + "<td>" + rs.getString("nombre_asignatura") + "</td>"
                                + "<td>" + rs.getString("nombre_profesor") + "</td>"
                                + "<td>" + rs.getString("apellido_profesor") + "</td>"
                                + "<td>" + rs.getString("fecha_asistencia") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(Asistencias asistencia) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = asistencia.getConvocatoria().getId_convocatoria();
            int v2 = asistencia.getDetalleconvocatoria().getId_detalleconvocatoria();
            Date v3 = asistencia.getFecha_asistencia();

            String sql = "insert into asistencias(id_convocatoria,id_detalleconvocatoria,fecha_asistencia) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_asistencia = keyset.getInt(1);
                    asistencia.setId_asistencia(id_asistencia);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(Asistencias asistencia) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update asistencias set  id_convocatoria=? "
                    + ", id_detalleconvocatoria=?, fecha_asistencia=? "
                    + "where id_asistencia=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, asistencia.getConvocatoria().getId_convocatoria());
                ps.setInt(2, asistencia.getDetalleconvocatoria().getId_detalleconvocatoria());
                ps.setDate(3, asistencia.getFecha_asistencia());
                ps.setInt(4, asistencia.getId_asistencia());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(Asistencias asistencia) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from asistencias where id_asistencia=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, asistencia.getId_asistencia());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
}