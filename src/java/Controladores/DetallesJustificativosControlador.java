/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import Modelos.Alumnos;
import Modelos.Convocatorias;
import Modelos.DetallesJustificativos;
import Modelos.Grados;
import Modelos.Inscripciones;
import Modelos.Justificativos;
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
public class DetallesJustificativosControlador {

    public static DetallesJustificativos buscarId(int id) {
        DetallesJustificativos detallejustificativo = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalle_justificativos dt, justificativos j,"
                        + "inscripciones i, alumnos a, convocatorias c, grados g, secciones se,"
                        + " turnos t where dt.id_justificativo=j.id_justificativo and "
                        + "dt.id_inscripcion=i.id_inscripcion and i.id_alumno=a.id_alumno and "
                        + "i.id_convocatoria=c.id_convocatoria and c.id_grado=g.id_grado and "
                        + "c.id_seccion=se.id_seccion and c.id_turno=t.id_turno and "
                        + "dt.id_detallejustificativos=?";
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detallejustificativo = new DetallesJustificativos();
                        detallejustificativo.setFecha_justificativo(rs.getDate("fecha_justificativo"));
                        
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
                        detallejustificativo.setInscripcion(inscripcion);
                        
                        Justificativos justificativo = new Justificativos();
                        justificativo.setId_justificativo(rs.getInt("id_justificativo"));
                        detallejustificativo.setJustificativo(justificativo);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallejustificativo;
    }

    public static String buscarIdJustificativo(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalle_justificativos dt, justificativos j,"
                        + "inscripciones i, alumnos a, convocatorias c, grados g, secciones se,"
                        + "turnos t where dt.id_justificativo=j.id_justificativo and "
                        + "dt.id_inscripcion=i.id_inscripcion and i.id_alumno=a.id_alumno and "
                        + "i.id_convocatoria=c.id_convocatoria and c.id_grado=g.id_grado and "
                        + "c.id_seccion=se.id_seccion and c.id_turno=t.id_turno and "
                        + "j.id_justificativo=" + id + " "
                        + "order by dt.id_detallejustificativos";
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
                                + "<td>" + rs.getString("id_detallejustificativos") + "</td>"
                                + "<td>" + rs.getString("nombre_alumno") + "</td>"
                                + "<td>" + rs.getString("apellido_alumno") + "</td>"
                                + "<td>" + rs.getString("nombre_grado") + "</td>"
                                + "<td>" + rs.getString("nombre_seccion") + "</td>"
                                + "<td>" + rs.getString("nombre_turno") + "</td>"
                                + "<td>" + rs.getString("fecha_justificativo") + "</td>"
                                //+ "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallejustificativos") + ")'"
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
                String sql = "select * from detalle_justificativos dt, justificativos j,"
                        + "inscripciones i, alumnos a, convocatorias c, grados g, secciones se,"
                        + "turnos t where dt.id_justificativo=j.id_justificativo and "
                        + "dt.id_inscripcion=i.id_inscripcion and i.id_alumno=a.id_alumno and "
                        + "i.id_convocatoria=c.id_convocatoria and c.id_grado=g.id_grado and "
                        + "c.id_seccion=se.id_seccion and c.id_turno=t.id_turno and "
                        + "upper(a.nombre_alumno) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by dt.id_detallejustificativos "
                        + "offset " + offset + " limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallejustificativos") + "</td>"
                                + "<td>" + rs.getString("id_justificativo") + "</td>"
                                + "<td>" + rs.getString("id_inscripcion") + "</td>"
                                + "<td>" + rs.getString("nombre_alumno") + "</td>"
                                + "<td>" + rs.getString("apellido_alumno") + "</td>"
                                + "<td>" + rs.getString("nombre_grado") + "</td>"
                                + "<td>" + rs.getString("nombre_seccion") + "</td>"
                                + "<td>" + rs.getString("nombre_turno") + "</td>"
                                + "<td>" + rs.getString("fecha_justificativo") + "</td>"
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

    public static boolean agregar(DetallesJustificativos detallejustificativo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detalle_justificativos "
                    + "(id_justificativo,id_inscripcion,fecha_justificativo) "
                    + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallejustificativo.getJustificativo().getId_justificativo());
                ps.setInt(2, detallejustificativo.getInscripcion().getId_inscripcion());
                ps.setDate(3, detallejustificativo.getFecha_justificativo());
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

    public static boolean modificar(DetallesJustificativos detallejustificativo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detalle_justificativos set "
                    + "id_justificativo=?,"
                    + " id_inscripcion=?,"
                    + " fecha_justificativo=? "
                    + "where id_detallejustificativos=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallejustificativo.getJustificativo().getId_justificativo());
                ps.setInt(2, detallejustificativo.getInscripcion().getId_inscripcion());
                ps.setDate(3, detallejustificativo.getFecha_justificativo());
                ps.setInt(4, detallejustificativo.getId_detallejustificativos());
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

    public static boolean eliminar(DetallesJustificativos detallejustificativo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detalle_justificativos where id_detallejustificativos=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallejustificativo.getId_detallejustificativos());
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

    public static boolean eliminarJustificativoInscripto(Justificativos justificativo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detalle_justificativos where id_justificativo=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, justificativo.getId_justificativo());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> " + justificativo.getId_justificativo());
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
