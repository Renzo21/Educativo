/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import Modelos.Calificaciones;
import Modelos.DetallesCalificaciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class DetallesCalificacionesControlador {

    public static String buscarId(int id, int id1)  {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalle_calificaciones dc, inscripciones i, convocatorias c, alumnos a, "
                        + "calificaciones ca "
                        + "where dc.id_inscripcion=i.id_inscripcion and "
                        + "i.id_alumno=a.id_alumno and dc.id_calificacion=ca.id_calificaciones and "
                        + "ca.id_calificaciones ="
                        + id1 + " and "
                        + "i.id_convocatoria=c.id_convocatoria and  c.id_convocatoria=" + 
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
                        String id_det = rs.getString("id_detallecalificacion");
                        String nota = rs.getString("nota_final");
                        //BigDecimal cantidad = rs.getBigDecimal("cantidad_articulopedido");
                        //total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                               
                                + "<td>" + rs.getString("id_detallecalificacion") + "</td>"
                                + "<td>" + rs.getString("id_inscripcion") + "</td>"
                                + "<td>" + rs.getString("nombre_alumno") + "</td>"
                                + "<td>" + rs.getString("apellido_alumno") + "</td>"
//                                + "<script>"
//                                + "var Linea={"
//                                + "	estado:'" + estado + "',"
//                                + "	id_det:'" + id_det + "',"
//                                + "};"
//                                + "</script>"
                                
                                + "<td class='centrado'> <input id='nota_final' name='nota_final'"
                                + " type='text' class='form-control "
                                + "input-sm' value='" + rs.getString("nota_final") + "'  > </td>"
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

    public static String buscarIdCalificacion(DetallesCalificaciones detallecalificacion) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from inscripciones i, convocatorias c, alumnos a "
                        + "where i.id_alumno=a.id_alumno and "
                        + "i.id_convocatoria=c.id_convocatoria and  c.id_convocatoria=" + 
                        detallecalificacion.getInscripcion().getConvocatoria().getId_convocatoria()
                        + " and i.estado_inscripcion!='ANULADO'"
                        + "order by id_detallecalificacion";
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
                                + "<td>" + rs.getString("nota_final") + "</td>"
                                
                                //+ "<td class='centrado'>" + df.format(cantidad) + "</td>"
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
                String sql = "select * from detalle_calificaciones dc, inscripciones i, alumnos a, "
                        + "convocatorias c , grados g, secciones se, especialidades e,"
                        + " turnos t, calificaciones ca "
                        + "where dc.id_inscripciones=i.id_inscripcion and "
                        + "dc.id_calificacion=ca.id_calificaciones and "
                        + "i.id_alumno=a.id_alumno and i.id_convocatoria = c.id_convocatoria and "
                        + "c.id_grado=g.id_grado and c.id_especialidad=e.id_especialidad and "
                        + "c.id_seccion=se.id_seccion and c.id_turno=t.id_turno and "
                        + "upper(a.nombre_alumno) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detallecalificacion "
                        + "offset " + offset + " limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallecalificacion") + "</td>"
                                + "<td>" + rs.getString("nombre_alumno") + "</td>"
                                + "<td>" + rs.getString("apellido_alumno") + "</td>"
                                + "<td>" + rs.getString("nombre_grado") + "</td>"
                                + "<td>" + rs.getString("nombre_especialidad") + "</td>"
                                + "<td>" + rs.getString("nombre_seccion") + "</td>"
                                + "<td>" + rs.getString("nombre_turno") + "</td>"
                                + "<td>" + rs.getString("nota_final") + "</td>"
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

    public static boolean agregar(DetallesCalificaciones detallecalificacion) {
        boolean valor = false;
        if (Conexion.conectar()) {

            String sql = "select * from inscripciones i, convocatorias c where "
                    + "i.id_convocatoria=c.id_convocatoria and  c.id_convocatoria=" + 
                    detallecalificacion.getInscripcion().getConvocatoria().getId_convocatoria()
                    + " and i.estado_inscripcion!='ANULADO'";

            try {
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        String sql1 = "insert into detalle_calificaciones(id_calificacion,id_inscripcion,nota_final)"
                                + "values('" + detallecalificacion.getCalificacion().getId_calificaciones() + "',"
                                + "'" + rs.getString("id_inscripcion") + "',"
                                + "'" + detallecalificacion.getNota_final() + "')";

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

            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }

        return valor;
    }

    public static boolean modificar(DetallesCalificaciones detallecalificacion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detalle_calificaciones set"
                    + " nota_final=? "
                    + "where id_detallecalificacion=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, detallecalificacion.getNota_final());
                ps.setInt(2, detallecalificacion.getId_detallecalificacion());
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

    public static boolean eliminar(DetallesCalificaciones detallecalificacion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detalle_calificaciones where id_detallecalificacion=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecalificacion.getId_detallecalificacion());
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

    public static boolean eliminarInscripcionAsistencia(Calificaciones calificacion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detalle_calificaciones where id_calificacion=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, calificacion.getId_calificaciones());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> " + calificacion.getId_calificaciones());
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
