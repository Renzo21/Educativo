/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import Modelos.Alumnos;
import Modelos.Convocatorias;
import Modelos.DetallesSanciones;
import Modelos.Grados;
import Modelos.Inscripciones;
import Modelos.Sanciones;
import Modelos.Secciones;
import Modelos.Turnos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Luisao
 */
public class DetallesSancionesControlador {

    public static DetallesSanciones buscarId(int id) {
        DetallesSanciones detallesancion = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalle_sanciones ds, sanciones sa,"
                        + "inscripciones i, alumnos a, convocatorias c, grados g, secciones se,"
                        + " turnos t where ds.id_sancion=sa.id_sancion and "
                        + "ds.id_inscripcion=i.id_inscripcion and i.id_alumno=a.id_alumno and "
                        + "i.id_convocatoria=c.id_convocatoria and c.id_grado=g.id_grado and "
                        + "c.id_seccion=se.id_seccion and c.id_turno=t.id_turno and "
                        + "ds.id_detallesancion=?";
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detallesancion = new DetallesSanciones();
                        detallesancion.setId_detallesancion(rs.getInt("id_detallesancion"));
                        detallesancion.setFecha_sancion(rs.getDate("fecha_sancion"));
                        
                        Turnos turno = new Turnos();
                        turno.setId_turno(rs.getInt("id_turno"));
                        turno.setNombre_turno(rs.getString("nombre_turno"));
                        
                        Grados grado = new Grados();
                        grado.setId_grado(rs.getInt("id_grado"));
                        grado.setNombre_grado(rs.getString("nombre_grado"));
                        
                        Secciones seccion = new Secciones();
                        seccion.setId_seccion(rs.getInt("id_seccion"));
                        seccion.setNombre_seccion(rs.getString("nombre_seccion"));
                        
                        Convocatorias convocatoria = new Convocatorias();
                        convocatoria.setGrado(grado);
                        convocatoria.setSeccion(seccion);
                        convocatoria.setTurno(turno);
                        
                        Alumnos alumno = new Alumnos();
                        alumno.setId_alumno(rs.getInt("id_alumno"));
                        alumno.setNombre_alumno(rs.getString("nombre_alumno"));
                        alumno.setApellido_alumno(rs.getString("apellido_alumno"));
                        
                        Inscripciones inscripcion = new Inscripciones();
                        inscripcion.setId_inscripcion(rs.getInt("id_inscripcion"));
                        inscripcion.setAlumno(alumno);
                        inscripcion.setConvocatoria(convocatoria);
                        detallesancion.setInscripcion(inscripcion);
                        
                        Sanciones sancion = new Sanciones();
                        sancion.setId_sancion(rs.getInt("id_sancion"));
                        detallesancion.setSancion(sancion);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallesancion;
    }

    public static String buscarIdSancion(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalle_sanciones ds, sanciones sa, "
                        + "inscripciones i, alumnos a, convocatorias c, grados g, secciones se,"
                        + "turnos t where ds.id_sancion=sa.id_sancion and "
                        + "ds.id_inscripcion=i.id_inscripcion and i.id_alumno=a.id_alumno and "
                        + "i.id_convocatoria=c.id_convocatoria and c.id_grado=g.id_grado and "
                        + "c.id_seccion=se.id_seccion and c.id_turno=t.id_turno and "
                        + "sa.id_sancion=" + id + " "
                        + "order by ds.id_detallesancion";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    //DecimalFormat df = new DecimalFormat("#,###.00");
                    String tabla = "";
                    //BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        //BigDecimal cantidad = rs.getBigDecimal("cantidad_articulopedido");
                        //total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallesancion") + "</td>"
                                + "<td>" + rs.getString("nombre_alumno") + "</td>"
                                + "<td>" + rs.getString("apellido_alumno") + "</td>"
                                + "<td>" + rs.getString("nombre_grado") + "</td>"
                                + "<td>" + rs.getString("nombre_seccion") + "</td>"
                                + "<td>" + rs.getString("nombre_turno") + "</td>"
                                + "<td>" + rs.getString("fecha_sancion") + "</td>"
                                //+ "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallesancion") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }// else {
                        //tabla += "<tr><td colspan=3>TOTAL</td><td class='centrado'>" + df.format(total) + "</td></tr>";
                    //}
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

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalle_sanciones ds, sanciones sa,"
                        + "inscripciones i, alumnos a, convocatorias c, grados g, secciones se,"
                        + "turnos t where ds.id_sancion=sa.id_sancion and "
                        + "ds.id_inscripcion=i.id_inscripcion and i.id_alumno=a.id_alumno and "
                        + "i.id_convocatoria=c.id_convocatoria and c.id_grado=g.id_grado and "
                        + "c.id_seccion=se.id_seccion and c.id_turno=t.id_turno and "
                        + "upper(a.nombre_alumno) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by ds.id_detallesancion "
                        + "offset " + offset + " limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallesancion") + "</td>"
                                + "<td>" + rs.getString("id_sancion") + "</td>"
                                + "<td>" + rs.getString("id_inscripcion") + "</td>"
                                + "<td>" + rs.getString("nombre_alumno") + "</td>"
                                + "<td>" + rs.getString("apellido_alumno") + "</td>"
                                + "<td>" + rs.getString("nombre_grado") + "</td>"
                                + "<td>" + rs.getString("nombre_seccion") + "</td>"
                                + "<td>" + rs.getString("nombre_turno") + "</td>"
                                + "<td>" + rs.getString("fecha_sancion") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
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

    public static boolean agregar(DetallesSanciones detallesancion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detalle_sanciones "
                    + "(id_sancion,id_inscripcion,fecha_sancion) "
                    + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallesancion.getSancion().getId_sancion());
                ps.setInt(2, detallesancion.getInscripcion().getId_inscripcion());
                ps.setDate(3, detallesancion.getFecha_sancion());
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

    public static boolean modificar(DetallesSanciones detallesancion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detalle_sanciones set "
                    + "id_sancion=?,"
                    + " id_inscripcion=?,"
                    + " fecha_sancion=? "
                    + "where id_detallesancion=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallesancion.getSancion().getId_sancion());
                ps.setInt(2, detallesancion.getInscripcion().getId_inscripcion());
                ps.setDate(3, detallesancion.getFecha_sancion());
                ps.setInt(4, detallesancion.getId_detallesancion());
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

    public static boolean eliminar(DetallesSanciones detallesancion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detalle_sanciones where id_detallesancion=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallesancion.getId_detallesancion());
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

    public static boolean eliminarSancionesInscripto(Sanciones sancion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detalle_sanciones where id_sancion=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, sancion.getId_sancion());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> " + sancion.getId_sancion());
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
