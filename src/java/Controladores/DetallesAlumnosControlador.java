/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Alumnos;
import Modelos.DetallesAlumnos;
import Modelos.Responsables;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Luisao
 */
public class DetallesAlumnosControlador {

    public static DetallesAlumnos buscarId(int id) {
        DetallesAlumnos detallealumno = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallealumnos da, responsables r, alumnos a "
                        + "where da.id_alumno=a.id_alumno and da.id_responsable=r.id_responsable and "
                        + "da.id_detallealumnos=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detallealumno = new DetallesAlumnos();
                        detallealumno.setId_detallealumnos(rs.getInt("id_detallealumnos"));
                        detallealumno.setCategoria_responsable(rs.getString("categoria_responsable"));

                        Responsables responsable = new Responsables();
                        responsable.setId_responsable(rs.getInt("id_responsable"));
                        responsable.setNombre_responsable(rs.getString("nombre_responsable"));
                        responsable.setApellido_responsable(rs.getString("apellido_responsable"));
                        responsable.setCi_responsable(rs.getString("ci_responsable"));
                        detallealumno.setResponsable(responsable);

                        Alumnos alumno = new Alumnos();
                        alumno.setId_alumno(rs.getInt("id_alumno"));
                        detallealumno.setAlumno(alumno);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallealumno;
    }

    public static String buscarIdAlumno(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallealumnos da, responsables r, alumnos a "
                        + "where da.id_alumno=a.id_alumno and da.id_responsable=r.id_responsable and "
                        + "da.id_alumno=" + id + " "
                        + "order by id_detallealumnos";
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
                                + "<td>" + rs.getString("id_detallealumnos") + "</td>"
                                + "<td>" + rs.getString("nombre_responsable") + "</td>"
                                + "<td>" + rs.getString("apellido_responsable") + "</td>"
                                + "<td>" + rs.getString("ci_responsable") + "</td>"
                                + "<td>" + rs.getString("categoria_responsable") + "</td>"
                                //+ "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallealumnos") + ")'"
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
                String sql = "select * from detallealumnos da, alumnos a, responsables r "
                        + "where da.id_alumno=a.id_alumno and da.id_responsable=r.id_responsable and "
                        + "upper(r.ci_responsable) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detallealumnos "
                        + "offset " + offset + " limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallealumnos") + "</td>"
                                + "<td>" + rs.getString("id_alumno") + "</td>"
                                + "<td>" + rs.getString("id_responsable") + "</td>"
                                + "<td>" + rs.getString("nombre_responsable") + "</td>"
                                + "<td>" + rs.getString("apellido_responsable") + "</td>"
                                + "<td>" + rs.getString("ci_responsable") + "</td>"
                                + "<td>" + rs.getString("categoria_responsable") + "</td>"
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

    public static boolean agregar(DetallesAlumnos detallealumno) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallealumnos "
                    + "(id_alumno,id_responsable,categoria_responsable) "
                    + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallealumno.getAlumno().getId_alumno());
                ps.setInt(2, detallealumno.getResponsable().getId_responsable());
                ps.setString(3, detallealumno.getCategoria_responsable());
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

    public static boolean modificar(DetallesAlumnos detallealumno) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallealumnos set "
                    + "id_alumno=?,"
                    + " id_responsable=?,"
                    + " categoria_responsable=? "
                    + "where id_detallealumnos=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallealumno.getAlumno().getId_alumno());
                ps.setInt(2, detallealumno.getResponsable().getId_responsable());
                ps.setString(3, detallealumno.getCategoria_responsable());
                ps.setInt(4, detallealumno.getId_detallealumnos());
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

    public static boolean eliminar(DetallesAlumnos detallealumno) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallealumnos where id_detallealumnos=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallealumno.getId_detallealumnos());
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

    public static boolean eliminarResponsableAlumno(Alumnos alumno) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallealumnos where id_alumno=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, alumno.getId_alumno());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> " + alumno.getId_alumno());
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
