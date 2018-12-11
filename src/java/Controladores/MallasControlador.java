/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Especialidades;
import Modelos.Grados;
import Modelos.Mallas;
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
public class MallasControlador {

     public static Mallas buscarId(int id) {
        Mallas malla = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from malla_curricular m, grados g, especialidades e "
                        + " where m.id_grado=g.id_grado and m.id_especialidad=e.id_especialidad and "
                        + "id_malla=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        malla = new Mallas();
                        malla.setId_malla(rs.getInt("id_malla"));
                        malla.setModalidad_malla(rs.getString("modalidad_malla"));
                        
                        Grados grado = new Grados();
                        grado.setId_grado(rs.getInt("id_grado"));
                        grado.setNombre_grado(rs.getString("nombre_grado"));
                        malla.setGrado(grado);
                        
                        Especialidades especialidad = new Especialidades();
                        especialidad.setId_especialidad(rs.getInt("id_especialidad"));
                        especialidad.setNombre_especialidad(rs.getString("nombre_especialidad"));
                        malla.setEspecialidad(especialidad);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return malla;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from malla_curricular m, grados g, especialidades e "
                        + "where m.id_grado=g.id_grado and m.id_especialidad=e.id_especialidad and "
                        + " upper(m.modalidad_malla) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by m.id_malla "
                        + "offset " + offset + " limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_malla") + "</td>"
                                + "<td>" + rs.getString("nombre_grado") + "</td>"
                                + "<td>" + rs.getString("nombre_especialidad") + "</td>"
                                + "<td>" + rs.getString("modalidad_malla") + "</td>"
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

    public static boolean agregar(Mallas malla) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = malla.getGrado().getId_grado();
            int v2 = malla.getEspecialidad().getId_especialidad();
            String v3 = malla.getModalidad_malla();

            String sql = "insert into malla_curricular(id_grado, id_especialidad, modalidad_malla) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_malla = keyset.getInt(1);
                    malla.setId_malla(id_malla);
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

    public static boolean modificar(Mallas malla) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update malla_curricular set id_grado=?, "
                    + "id_especialidad=?, "
                    + "modalidad_malla=? " 
                    + "where id_malla=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, malla.getGrado().getId_grado());
                ps.setInt(2, malla.getEspecialidad().getId_especialidad());
                ps.setString(3, malla.getModalidad_malla());
                ps.setInt(4, malla.getId_malla());
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

    public static boolean eliminar(Mallas malla) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from malla_curricular where id_malla=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, malla.getId_malla());
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