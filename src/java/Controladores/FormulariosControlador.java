/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Formularios;
import Modelos.Menus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class FormulariosControlador {

    public static Formularios buscarId(int id) {
        Formularios formularios = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from formularios f "+
                             "left join menus m on f.id_menu=m.id_menu "+
                             "where id_formulario=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        formularios = new Formularios();
                        formularios.setId_formulario(rs.getInt("id_formulario"));
                        formularios.setNombre_formulario(rs.getString("nombre_formulario"));
                        formularios.setCodigo_formulario(rs.getString("codigo_formulario"));
                        Menus menu = new Menus();
                        menu.setId_menu(rs.getInt("id_menu"));
                        menu.setNombre_menu(rs.getString("nombre_menu"));
                        menu.setCodigo_menu(rs.getString("codigo_menu"));
                        formularios.setMenu(menu);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return formularios;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset=(pagina-1)*Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from formularios f "+
                        "left join menus m on f.id_menu=m.id_menu "+
                        "where upper(nombre_formulario) like '%" + 
                        nombre.toUpperCase() + 
                        "%' "+
                        "order by id_formulario "+
                        "offset "+ offset + " limit "+ Utiles.REGISTRO_PAGINA;
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_formulario") + "</td>"
                               + "<td>" + rs.getString("nombre_formulario") + "</td>"
                               + "<td><input type='text' value='" + rs.getString("codigo_formulario") + "' size=100 readonly='readonly'></td>"
                               + "<td>" + rs.getString("id_menu") + "</td>"
                               + "<td>" + rs.getString("nombre_menu") + "</td>"
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

    public static boolean agregar(Formularios formulario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into formularios  "
                    + "(nombre_formulario,codigo_formulario,id_menu) "
                    + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setString(1, formulario.getNombre_formulario());
                ps.setString(2, formulario.getCodigo_formulario());
                ps.setInt(3, formulario.getMenu().getId_menu());
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

    public static boolean modificar(Formularios formulario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update formularios set nombre_formulario=? ,"
                    + "codigo_formulario=? ,"
                    + "id_menu=? "
                    + "where id_formulario=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setString(1, formulario.getNombre_formulario());
                ps.setString(2, formulario.getCodigo_formulario());
                ps.setInt(3, formulario.getMenu().getId_menu());
                ps.setInt(4, formulario.getId_formulario());
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

    public static boolean eliminar(Formularios formulario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from formularios where id_formulario=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, formulario.getId_formulario());
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
    
    public static Formularios buscarNombreRepetidoFormulario(Formularios formulario) {
        if (Conexion.conectar()) {
            String sql = "select * from formularios where nombre_formulario='"
                    + formulario.getNombre_formulario() + "'";
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    formulario.setNombre_formulario("");
                } else {
                    formulario.setNombre_formulario(formulario.getNombre_formulario());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return formulario;
    }
}
