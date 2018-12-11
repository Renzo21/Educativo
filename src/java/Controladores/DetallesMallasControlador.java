/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import Modelos.Asignaturas;
import Modelos.DetallesMallas;
import Modelos.Mallas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Luisao
 */
public class DetallesMallasControlador {

    public static DetallesMallas buscarId(int id) {
        DetallesMallas detallemalla = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalle_malla dm, asignaturas a, malla_curricular m "
                        + "where dm.id_malla=m.id_malla and dm.id_asignatura=a.id_asignatura and "
                        + "dm.id_detallemalla=?";
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detallemalla = new DetallesMallas();
                        detallemalla.setId_detallemalla(rs.getInt("id_detallemalla"));
                        detallemalla.setCargahoraria_total(rs.getString("cargahoraria_total"));
                        
                        Mallas malla = new Mallas();
                        malla.setId_malla(rs.getInt("id_malla"));
                        detallemalla.setMalla(malla);
                        
                        Asignaturas asignatura = new Asignaturas();
                        asignatura.setId_asignatura(rs.getInt("id_asignatura"));
                        asignatura.setNombre_asignatura(rs.getString("nombre_asignatura"));
                        detallemalla.setAsignatura(asignatura);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallemalla;
    }

    public static String buscarIdMalla(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalle_malla dm, asignaturas a, malla_curricular m "
                        + "where dm.id_malla=m.id_malla and dm.id_asignatura=a.id_asignatura and "
                        + "m.id_malla=" + id + " "
                        + "order by dm.id_detallemalla";
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
                                + "<td>" + rs.getString("id_detallemalla") + "</td>"
                                + "<td>" + rs.getString("nombre_asignatura") + "</td>"
                                + "<td>" + rs.getString("cargahoraria_total") + "</td>"
                                //+ "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallemalla") + ")'"
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
                String sql = "select * from detalle_malla dm, asignaturas a, malla_curricular m "
                        + "where dm.id_malla=m.id_malla and dm.id_asignatura=a.id_asignatura and "
                        + "upper(a.nombre_asignatura) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by dm.id_detallemalla "
                        + "offset " + offset + " limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallemalla") + "</td>"
                                + "<td>" + rs.getString("id_malla") + "</td>"
                                + "<td>" + rs.getString("id_asignatura") + "</td>"
                                + "<td>" + rs.getString("nombre_asignatura") + "</td>"
                                + "<td>" + rs.getString("cargahoraria_total") + "</td>"
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

    public static boolean agregar(DetallesMallas detallemalla) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detalle_malla "
                    + "(id_malla,id_asignatura,cargahoraria_total) "
                    + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallemalla.getMalla().getId_malla());
                ps.setInt(2, detallemalla.getAsignatura().getId_asignatura());
                ps.setString(3, detallemalla.getCargahoraria_total());
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

    public static boolean modificar(DetallesMallas detallemalla) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detalle_malla set "
                    + "id_malla=?,"
                    + " id_asignatura=?,"
                    + " cargahoraria_total=? "
                    + "where id_detallemalla=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallemalla.getMalla().getId_malla());
                ps.setInt(2, detallemalla.getAsignatura().getId_asignatura());
                ps.setString(3, detallemalla.getCargahoraria_total());
                ps.setInt(4, detallemalla.getId_detallemalla());
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

    public static boolean eliminar(DetallesMallas detallemalla) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detalle_malla where id_detallemalla=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallemalla.getId_detallemalla());
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

    public static boolean eliminarSancionesInscripto(Mallas malla) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detalle_malla where id_malla=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, malla.getId_malla());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> " + malla.getId_malla());
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
