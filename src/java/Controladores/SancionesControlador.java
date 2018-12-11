/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Sanciones;
import Modelos.Tiposfaltas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class SancionesControlador {

     public static Sanciones buscarId(int id) {
        Sanciones sancion = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from sanciones sa, tipos_faltas tf"
                        + " where sa.id_tipofalta=tf.id_tipofalta and "
                        + "id_sancion=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        sancion = new Sanciones();
                        sancion.setId_sancion(rs.getInt("id_sancion"));
                        sancion.setObs_sancion(rs.getString("obs_sancion"));
                        
                        Tiposfaltas tipofalta = new Tiposfaltas();
                        tipofalta.setId_tipofalta(rs.getInt("id_tipofalta"));
                        tipofalta.setDescripcion_tipofalta(rs.getString("descripcion_tipofalta"));
                        sancion.setTipofalta(tipofalta);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return sancion;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from sanciones sa, tipos_faltas tf "
                        + "where sa.id_tipofalta=tf.id_tipofalta and "
                        + " upper(obs_sancion) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_sancion "
                        + "offset " + offset + " limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_sancion") + "</td>"
                                + "<td>" + rs.getString("descripcion_tipofalta") + "</td>"
                                + "<td>" + rs.getString("obs_sancion") + "</td>"
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

    public static boolean agregar(Sanciones sancion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = sancion.getTipofalta().getId_tipofalta();
            String v2 = sancion.getObs_sancion();

            String sql = "insert into sanciones(id_tipofalta, obs_sancion) "
                    + "values('" + v1 + "','" + v2 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_sancion = keyset.getInt(1);
                    sancion.setId_sancion(id_sancion);
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

    public static boolean modificar(Sanciones sancion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update sanciones set id_tipofalta=?, obs_sancion=? " 
                    + "where id_sancion=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, sancion.getTipofalta().getId_tipofalta());
                ps.setString(2, sancion.getObs_sancion());
                ps.setInt(3, sancion.getId_sancion());
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

    public static boolean eliminar(Sanciones sancion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from sanciones where id_sancion=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, sancion.getId_sancion());
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