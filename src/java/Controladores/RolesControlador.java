/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Roles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class RolesControlador {

    public static Roles buscarId(int id) {
        Roles roles = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from roles where id_rol=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        roles = new Roles();
                        roles.setId_rol(rs.getInt("id_rol"));
                        roles.setNombre_rol(rs.getString("nombre_rol"));
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return roles;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset=(pagina-1)*Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from roles where upper(nombre_rol) like '%" + 
                        nombre.toUpperCase() + 
                        "%' "+
                        "order by id_rol "+
                        "offset "+ offset + " limit "+ Utiles.REGISTRO_PAGINA;
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_rol") + "</td>"
                               + "<td>" + rs.getString("nombre_rol") + "</td>"
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=2>No existen registros ...</td></tr>";
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

    public static boolean agregar(Roles rol) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into roles "
                    + "(nombre_rol) "
                    + "values (?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setString(1, rol.getNombre_rol());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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

    public static boolean modificar(Roles rol) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update roles set nombre_rol=? "
                    + "where id_rol=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setString(1, rol.getNombre_rol());
                ps.setInt(2, rol.getId_rol());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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

    public static boolean eliminar(Roles rol) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from roles where id_rol=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, rol.getId_rol());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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
    
    public static Roles buscarNombreRepetidoRol(Roles rol) {
        if (Conexion.conectar()) {
            String sql = "select * from roles where nombre_rol='"
                    + rol.getNombre_rol() + "'";
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    rol.setNombre_rol(sql);
                } else {
                    rol.setNombre_rol(rol.getNombre_rol());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return rol;
    }
}
