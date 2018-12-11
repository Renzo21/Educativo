/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Formularios;
import Modelos.Permisoss;
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
public class PermisossControlador {
   public static Permisoss buscarId(int id) {
        Permisoss permisoss = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from permisoss p "+
                             "left join roles r on p.id_rol=r.id_rol "+
                             "left join formularios f on p.id_formulario=f.id_formulario "+
                             "where id_permisoss=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        permisoss = new Permisoss();
                        permisoss.setId_permisoss(rs.getInt("id_permisoss"));
                        Roles rol = new Roles();
                        rol.setId_rol(rs.getInt("id_rol"));
                        rol.setNombre_rol(rs.getString("nombre_rol"));
                        permisoss.setRol(rol);
                        Formularios formulario = new Formularios();
                        formulario.setId_formulario(rs.getInt("id_formulario"));
                        formulario.setNombre_formulario(rs.getString("nombre_formulario"));
                        permisoss.setFormulario(formulario);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return permisoss;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset=(pagina-1)*Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from permisoss p "+
                        "left join roles r on p.id_rol=r.id_rol "+
                        "left join formularios f on p.id_formulario=f.id_formulario "+
                        "where upper(nombre_rol) like '%" + 
                        nombre.toUpperCase() + 
                        "%' "+
                        "order by id_permisoss "+
                        "offset "+ offset + " limit "+ Utiles.REGISTRO_PAGINA;
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_permisoss") + "</td>"
                               + "<td>" + rs.getString("id_rol") + "</td>"
                               + "<td>" + rs.getString("nombre_rol") + "</td>"
                               + "<td>" + rs.getString("id_formulario") + "</td>"
                               + "<td>" + rs.getString("nombre_formulario") + "</td>"
                               + "</tr>";
                    }
                    if(tabla.equals("")){
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

    public static boolean agregar(Permisoss permisoss) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into permisoss "
                    + "(id_rol,id_formulario) "
                    + "values (?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, permisoss.getRol().getId_rol());
                ps.setInt(2, permisoss.getFormulario().getId_formulario());
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

    public static boolean modificar(Permisoss permisoss) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update permisoss set "
                    + "id_rol=?, "
                    + "id_formulario=? "
                    + "where id_permisoss=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, permisoss.getRol().getId_rol());
                ps.setInt(2, permisoss.getFormulario().getId_formulario());
                ps.setInt(3, permisoss.getId_permisoss());
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

    public static boolean eliminar(Permisoss permisoss) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from permisoss where id_permisoss=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, permisoss.getId_permisoss());
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
