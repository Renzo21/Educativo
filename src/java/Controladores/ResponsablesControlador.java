/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Ciudades;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Responsables;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class ResponsablesControlador {

    public static boolean agregar(Responsables responsable) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into responsables(nombre_responsable,apellido_responsable"
                    + ",ci_responsable,direccion_reponsable,telefono_responsable,id_ciudad)"
                    + "values('" + responsable.getNombre_responsable() + "',"
                    + "'" + responsable.getApellido_responsable() + "',"
                    + "'" + responsable.getCi_responsable() + "',"
                    + "'" + responsable.getDireccion_responsable() + "',"
                    + "'" + responsable.getTelefono_responsable() + "',"
                    + "'" + responsable.getCiudad().getId_ciudad() + "')";
            System.out.println(sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Responsables buscarId(Responsables responsable) {
        if (Conexion.conectar()) {
            String sql = "select * from responsables r, ciudades c where r.id_ciudad=c.id_ciudad and "
                    + "id_responsable='" + responsable.getId_responsable() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                    responsable.setId_responsable(rs.getInt("id_responsable"));
                    responsable.setNombre_responsable(rs.getString("nombre_responsable"));
                    responsable.setApellido_responsable(rs.getString("apellido_responsable"));
                    responsable.setCi_responsable(rs.getString("ci_responsable"));
                    responsable.setDireccion_responsable(rs.getString("direccion_reponsable"));
                    responsable.setTelefono_responsable(rs.getString("telefono_responsable"));
                    responsable.setCiudad(ciudad);
                } else {
                    responsable.setId_responsable(0);
                    responsable.setNombre_responsable("");
                    responsable.setApellido_responsable("");
                    responsable.setCi_responsable("");
                    responsable.setDireccion_responsable("");
                    responsable.setTelefono_responsable("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return responsable;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from responsables where upper (ci_responsable) like '%"
                        + nombre.toUpperCase() + "%'" + " order by id_responsable offset " + offset
                        + "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_responsable") + "</td>"
                                + "<td>" + rs.getString("nombre_responsable") + "</td>"
                                + "<td>" + rs.getString("apellido_responsable") + "</td>"
                                + "<td>" + rs.getString("ci_responsable") + "</td>"
                                + "<td>" + rs.getString("direccion_reponsable") + "</td>"
                                + "<td>" + rs.getString("telefono_responsable") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan = 2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error" + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error" + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(Responsables responsable) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update responsables set nombre_responsable='" + responsable.getNombre_responsable() + "'"
                    + ", apellido_responsable='" + responsable.getApellido_responsable() + "'"
                    + ", ci_responsable='" + responsable.getCi_responsable() + "'"
                    + ", direccion_reponsable='" + responsable.getDireccion_responsable() + "'"
                    + ", telefono_responsable='" + responsable.getTelefono_responsable() + "'"
                    + ", id_ciudad=" + responsable.getCiudad().getId_ciudad()
                    + " where id_responsable=" + responsable.getId_responsable();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Responsables responsable) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from responsables where id_responsable=" + responsable.getId_responsable();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error " + ex);
            }
        }
        return valor;
    }
    
    public static Responsables buscarCedula(Responsables responsable) {
        if (Conexion.conectar()) {
            String sql = "select * from responsables where ci_responsable='" + responsable.getCi_responsable() + "'";
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    responsable.setCi_responsable("");
                } else {
                    responsable.setCi_responsable(responsable.getCi_responsable());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return responsable;
    }
    
}
