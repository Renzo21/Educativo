/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Permisos;
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
public class PermisosControlador {

     public static Permisos buscarId(int id) {
        Permisos permiso = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from permisos where id_permiso=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        permiso = new Permisos();
                        permiso.setId_permiso(rs.getInt("id_permiso"));
                        permiso.setMotivo_permiso(rs.getString("motivo_permiso"));
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return permiso;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from permisos where"
                        + " upper(motivo_permiso) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_permiso "
                        + "offset " + offset + " limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_permiso") + "</td>"
                                + "<td>" + rs.getString("motivo_permiso") + "</td>"
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

    public static boolean agregar(Permisos permiso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String v1 = permiso.getMotivo_permiso();

            String sql = "insert into permisos(motivo_permiso) "
                    + "values('" + v1 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_permiso = keyset.getInt(1);
                    permiso.setId_permiso(id_permiso);
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

    public static boolean modificar(Permisos permiso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update permisos set motivo_permiso=? " 
                    + "where id_permiso=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setString(1, permiso.getMotivo_permiso());
                ps.setInt(2, permiso.getId_permiso());
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

    public static boolean eliminar(Permisos permiso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from permisos where id_permiso=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, permiso.getId_permiso());
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