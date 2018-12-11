/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Alumnos;
import Modelos.Asistencias;
import Modelos.Convocatorias;
import Modelos.DetallesAsistencias;
import Modelos.Especialidades;
import Modelos.Grados;
import Modelos.Inscripciones;
import Modelos.Secciones;
import Modelos.Turnos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class DetallesAsistenciasControlador {

    public static String buscarId(int id, int id1)  {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalle_asistencias da, inscripciones i, convocatorias c, alumnos a, "
                        + "asistencias asis "
                        + "where da.id_inscripcion=i.id_inscripcion and "
                        + "i.id_alumno=a.id_alumno and "
                        + "i.id_convocatoria=c.id_convocatoria and  da.id_asistencia=asis.id_asistencia and "
                        + "asis.id_asistencia="
                        + id1 + " and "
                        + "c.id_convocatoria=" + 
                        id
                        + " and i.estado_inscripcion!='ANULADO'"
                        + "order by c.id_convocatoria";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    //DecimalFormat df = new DecimalFormat( "#,###.00" );
                    String tabla = "";
                    
                    //BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        String id_det = rs.getString("id_detalleasistencias");
                        String estado = rs.getString("estado_asistencia");
                        //BigDecimal cantidad = rs.getBigDecimal("cantidad_articulopedido");
                        //total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                               
                                + "<td>" + rs.getString("id_detalleasistencias") + "</td>"
                                + "<td>" + rs.getString("id_inscripcion") + "</td>"
                                + "<td>" + rs.getString("nombre_alumno") + "</td>"
                                + "<td>" + rs.getString("apellido_alumno") + "</td>"
//                                + "<script>"
//                                + "var Linea={"
//                                + "	estado:'" + estado + "',"
//                                + "	id_det:'" + id_det + "',"
//                                + "};"
//                                + "</script>"
                                
                                + "<td class='centrado'> <input id='estado_asistencia' name='estado_asistencia'"
                                + " type='text' class='form-control "
                                + "input-sm' value='" + rs.getString("estado_asistencia") + "'  > </td>"
                                + "</tr>"
                                
                                ;                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    } else {
                        //tabla += "<tr><td colspan=3>TOTAL</td><td class='centrado'>"+df.format(total)+"</td></tr>";
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

    public static String buscarIdAsistencia(DetallesAsistencias detalleasistencia) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from inscripciones i, convocatorias c, alumnos a "
                        + "where i.id_alumno=a.id_alumno and "
                        + "i.id_convocatoria=c.id_convocatoria and  c.id_convocatoria=" + 
                        detalleasistencia.getInscripcion().getConvocatoria().getId_convocatoria()
                        + " and i.estado_inscripcion!='ANULADO'"
                        + "order by id_detalleasistencias";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    //DecimalFormat df = new DecimalFormat( "#,###.00" );
                    String tabla = "";
                    //BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        //BigDecimal cantidad = rs.getBigDecimal("cantidad_articulopedido");
                        //total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("nombre_alumno") + "</td>"
                                + "<td>" + rs.getString("apellido_alumno") + "</td>"
                                + "<td>" + rs.getString("estado_asistencia") + "</td>"
                                
                                //+ "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>" + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    } else {
                        //tabla += "<tr><td colspan=3>TOTAL</td><td class='centrado'>"+df.format(total)+"</td></tr>";
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

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalle_asistencias da, inscripciones i, alumnos a, "
                        + "convocatorias c , grados g, secciones se, especialidades e,"
                        + " turnos t, asistencias as "
                        + "where da.id_inscripciones=i.id_inscripcion and "
                        + "da.id_asistencia=as.id_asistencia and "
                        + "i.id_alumno=a.id_alumno and i.id_convocatoria = c.id_convocatoria and "
                        + "c.id_grado=g.id_grado and c.id_especialidad=e.id_especialidad and "
                        + "c.id_seccion=se.id_seccion and c.id_turno=t.id_turno and "
                        + "upper(p.nombre_profesor) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detalleasistencias "
                        + "offset " + offset + " limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detalle_asistencias") + "</td>"
                                + "<td>" + rs.getString("nombre_alumno") + "</td>"
                                + "<td>" + rs.getString("apellido_alumno") + "</td>"
                                + "<td>" + rs.getString("nombre_grado") + "</td>"
                                + "<td>" + rs.getString("nombre_especialidad") + "</td>"
                                + "<td>" + rs.getString("nombre_seccion") + "</td>"
                                + "<td>" + rs.getString("nombre_turno") + "</td>"
                                + "<td>" + rs.getString("estado_asistencia") + "</td>"
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

    public static boolean agregar(DetallesAsistencias detalleasistencia) {
        boolean valor = false;
        if (Conexion.conectar()) {

            String sql = "select * from inscripciones i, convocatorias c where "
                    + "i.id_convocatoria=c.id_convocatoria and  c.id_convocatoria=" + 
                    detalleasistencia.getAsistencia().getConvocatoria().getId_convocatoria()
                    + " and i.estado_inscripcion!='ANULADO'";

            try {
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        String sql1 = "insert into detalle_asistencias(id_asistencia,id_inscripcion,estado_asistencia)"
                                + "values('" + detalleasistencia.getAsistencia().getId_asistencia() + "',"
                                + "'" + rs.getString("id_inscripcion") + "',"
                                + "'" + detalleasistencia.getEstado_asistencia() + "')";

                        System.out.println(sql1);
                        try {
                            Conexion.getSt().executeUpdate(sql1);
                            valor = true;
                        } catch (SQLException ex) {
                            System.err.println("Error:" + ex);
                        }
                    }

                    ps.close();

                }

            }  catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }

        return valor;
    }

    public static boolean modificar(DetallesAsistencias detalleasistencia) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detalle_asistencias set"
                    + " estado_asistencia=? "
                    + "where id_detalleasistencias=?";
            
            System.out.println("sql modifi "+sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setString(1, detalleasistencia.getEstado_asistencia());
                ps.setInt(2, detalleasistencia.getId_detalleasistencias());
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

    public static boolean eliminar(DetallesAsistencias detalleasistencia) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detalle_asistencias where id_detalleasistencias=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleasistencia.getId_detalleasistencias());
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

    public static boolean eliminarInscripcionAsistencia(Asistencias asistencia) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detalle_asistencias where id_asistencia=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, asistencia.getId_asistencia());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> " + asistencia.getId_asistencia());
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
