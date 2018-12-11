/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Justificativos;
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
public class JustificativosControlador {

     public static Justificativos buscarId(int id) {
        Justificativos justificativo = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from justificativos where id_justificativo=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        justificativo = new Justificativos();
                        justificativo.setId_justificativo(rs.getInt("id_justificativo"));
                        justificativo.setMotivo_justificativo(rs.getString("motivo_justificativo"));
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return justificativo;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from justificativos where"
                        + " upper(motivo_justificativo) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_justificativo "
                        + "offset " + offset + " limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_justificativo") + "</td>"
                                + "<td>" + rs.getString("motivo_justificativo") + "</td>"
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

    public static boolean agregar(Justificativos justificativo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String v1 = justificativo.getMotivo_justificativo();

            String sql = "insert into justificativos(motivo_justificativo) "
                    + "values('" + v1 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_justificativo = keyset.getInt(1);
                    justificativo.setId_justificativo(id_justificativo);
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

    public static boolean modificar(Justificativos justificativo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update justificativos set motivo_justificativo=? " 
                    + "where id_justificativo=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setString(1, justificativo.getMotivo_justificativo());
                ps.setInt(2, justificativo.getId_justificativo());
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

    public static boolean eliminar(Justificativos justificativo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from justificativos where id_justificativo=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, justificativo.getId_justificativo());
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