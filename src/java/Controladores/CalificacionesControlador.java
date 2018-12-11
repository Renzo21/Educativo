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
import Modelos.Calificaciones;
import Modelos.Convocatorias;
import Modelos.DetallesConvocatoria;
import Modelos.Etapas;
import Modelos.Grados;
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
public class CalificacionesControlador {
    
    public static Calificaciones buscarId(int id) {
        Calificaciones calificacion = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from calificaciones ca, asignaturas a, detalle_convocatorias dc, convocatorias c,"
                        + " grados g, turnos t, secciones se, etapas et "
                        + "where ca.id_detalleconvocatoria=dc.id_detalleconvocatoria and "
                        + "dc.id_convocatoria=c.id_convocatoria and c.id_grado=g.id_grado and "
                        + "c.id_seccion=se.id_seccion and c.id_turno=t.id_turno and "
                        + "ca.id_etapa=et.id_etapa and dc.id_asignatura=a.id_asignatura and "
                        + " id_calificaciones=?";
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
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
                        convocatoria.setId_convocatoria(rs.getInt("id_convocatoria"));
                        convocatoria.setGrado(grado);
                        convocatoria.setSeccion(seccion);
                        convocatoria.setTurno(turno);
                        
                        Asignaturas asignatura = new Asignaturas();
                        asignatura.setId_asignatura(rs.getInt("id_asignatura"));
                        asignatura.setNombre_asignatura(rs.getString("nombre_asignatura"));
                        
                        DetallesConvocatoria detalleconvocatoria = new DetallesConvocatoria();                        
                        detalleconvocatoria.setId_detalleconvocatoria(rs.getInt("id_detalleconvocatoria"));
                        detalleconvocatoria.setConvocatoria(convocatoria);
                        detalleconvocatoria.setAsignatura(asignatura);
                        
                        Etapas etapa = new Etapas();
                        etapa.setId_etapa(rs.getInt("id_etapa"));
                        etapa.setNombre_etapa(rs.getString("nombre_etapa"));
                        
                        calificacion = new Calificaciones();
                        calificacion.setId_calificaciones(rs.getInt("id_calificaciones"));
                        calificacion.setFecha_calificacion(rs.getDate("fecha_calificacion"));
                        calificacion.setDetalleconvocatoria(detalleconvocatoria);
                        calificacion.setEtapa(etapa);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return calificacion;
    }
    
    public static String buscarNombre(Date nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from calificaciones ca, asignaturas a, detalle_convocatorias dc, convocatorias c,"
                        + " grados g, turnos t, secciones se, etapas et "
                        + "where ca.id_detalleconvocatoria=dc.id_detalleconvocatoria and "
                        + "dc.id_convocatoria=c.id_convocatoria and c.id_grado=g.id_grado and "
                        + "c.id_seccion=se.id_seccion and c.id_turno=t.id_turno and "
                        + "ca.id_etapa=et.id_etapa and dc.id_asignatura=a.id_asignatura and "
                        + "ca.fecha_calificacion = '%"
                        + nombre
                        + "%' "
                        + "order by id_calificaciones "
                        + "offset " + offset + " limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_calificaciones") + "</td>"
                                + "<td>" + rs.getString("nombre_grado") + "</td>"
                                + "<td>" + rs.getString("nombre_seccion") + "</td>"
                                + "<td>" + rs.getString("nombre_turno") + "</td>"
                                + "<td>" + rs.getString("nombre_asignatura") + "</td>"
                                + "<td>" + rs.getString("nombre_etapa") + "</td>"
                                + "<td>" + rs.getString("fecha_calificacion") + "</td>"
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
    
    public static boolean agregar(Calificaciones calificacion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = calificacion.getDetalleconvocatoria().getId_detalleconvocatoria();
            int v2 = calificacion.getEtapa().getId_etapa();
            Date v3 = calificacion.getFecha_calificacion();
            
            String sql = "insert into calificaciones(id_detalleconvocatoria,id_etapa,fecha_calificacion) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_calificacion = keyset.getInt(1);
                    calificacion.setId_calificaciones(id_calificacion);
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
    
    public static boolean modificar(Calificaciones calificacion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update calificaciones set  id_detalleconvocatoria=? "
                    + ", id_etapa=?, fecha_calificacion=? "
                    + "where id_calificaciones=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                
                ps.setInt(1, calificacion.getDetalleconvocatoria().getId_detalleconvocatoria());
                ps.setInt(2, calificacion.getEtapa().getId_etapa());
                ps.setDate(3, calificacion.getFecha_calificacion());
                ps.setInt(4, calificacion.getId_calificaciones());
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
    
    public static boolean eliminar(Calificaciones calificacion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from calificaciones where id_calificaciones=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, calificacion.getId_calificaciones());
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
