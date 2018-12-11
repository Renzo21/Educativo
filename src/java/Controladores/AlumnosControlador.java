/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Alumnos;
import Modelos.Ciudades;
import Modelos.Generos;
import Modelos.Nacionalidades;
import java.sql.Statement;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class AlumnosControlador {

     public static Alumnos buscarId(int id) {
        Alumnos alumno = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from alumnos a, nacionalidades n, ciudades c, generos g "
                        + "where a.id_nacionalidad=n.id_nacionalidad and a.id_ciudad=c.id_ciudad and "
                        + "a.id_genero=g.id_genero and id_alumno=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        alumno = new Alumnos();
                        alumno.setId_alumno(rs.getInt("id_alumno"));
                        alumno.setNombre_alumno(rs.getString("nombre_alumno"));
                        alumno.setApellido_alumno(rs.getString("apellido_alumno"));
                        alumno.setCi_alumno(rs.getString("ci_alumno"));
                        alumno.setTelefono_alumno(rs.getString("telefono_alumno"));
                        alumno.setObs_alumno(rs.getString("obs_alumno"));
                        Nacionalidades nacionalidad = new Nacionalidades();
                        nacionalidad.setId_nacionalidad(rs.getInt("id_nacionalidad"));
                        nacionalidad.setNombre_nacionalidad(rs.getString("nombre_nacionalidad"));
                        alumno.setNacionalidad(nacionalidad);
                        Ciudades ciudad = new Ciudades();
                        ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                        ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                        alumno.setCiudad(ciudad);
                        Generos genero = new Generos();
                        genero.setId_genero(rs.getInt("id_genero"));
                        genero.setNombre_genero(rs.getString("nombre_genero"));
                        alumno.setGenero(genero);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return alumno;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from alumnos a, nacionalidades n, ciudades c, generos g "
                        + "where a.id_nacionalidad=n.id_nacionalidad and a.id_ciudad=c.id_ciudad and "
                        + "a.id_genero=g.id_genero and upper(ci_alumno) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_alumno "
                        + "offset " + offset + " limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_alumno") + "</td>"
                                + "<td>" + rs.getString("nombre_alumno") + "</td>"
                                + "<td>" + rs.getString("apellido_alumno") + "</td>"
                                + "<td>" + rs.getString("ci_alumno") + "</td>"
                                + "<td>" + rs.getString("nombre_genero") + "</td>"
                                + "<td>" + rs.getString("nombre_nacionalidad") + "</td>"
                                + "<td>" + rs.getString("nombre_ciudad") + "</td>"
                                + "<td>" + rs.getString("telefono_alumno") + "</td>"
                                + "<td>" + rs.getString("obs_alumno") + "</td>"
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

    public static boolean agregar(Alumnos alumno) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String v1 = alumno.getNombre_alumno();
            String v2 = alumno.getApellido_alumno();
            String v3 = alumno.getCi_alumno();
            int v4 = alumno.getGenero().getId_genero();
            String v5 = alumno.getTelefono_alumno();
            String v6 = alumno.getObs_alumno();
            int v7 = alumno.getNacionalidad().getId_nacionalidad();
            int v8 = alumno.getCiudad().getId_ciudad();

            String sql = "insert into alumnos(nombre_alumno,apellido_alumno,ci_alumno,id_genero,"
                    + "telefono_alumno,obs_alumno,id_nacionalidad,id_ciudad) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "','" + v4 + "','" + v5 + "','" + v6 +
                    "','" + v7 + "','" + v8 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_alumno = keyset.getInt(1);
                    alumno.setId_alumno(id_alumno);
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

    public static boolean modificar(Alumnos alumno) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update alumnos set nombre_alumno=?"
                    + ", apellido_alumno=?, ci_alumno=?, id_genero=?, telefono_alumno=?"
                    + ", obs_alumno=?, id_nacionalidad=?, id_ciudad=? "
                    + "where id_alumno=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setString(1, alumno.getNombre_alumno());
                ps.setString(2, alumno.getApellido_alumno());
                ps.setString(3, alumno.getCi_alumno());
                ps.setInt(4, alumno.getGenero().getId_genero());
                ps.setString(5, alumno.getTelefono_alumno());
                ps.setString(6, alumno.getObs_alumno());
                ps.setInt(7, alumno.getNacionalidad().getId_nacionalidad());
                ps.setInt(8, alumno.getCiudad().getId_ciudad());
                ps.setInt(9, alumno.getId_alumno());
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

    public static boolean eliminar(Alumnos alumno  ) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from alumnos where id_alumno=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, alumno.getId_alumno());
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
    
    public static Alumnos buscarCedula(Alumnos alumno) {
        if (Conexion.conectar()) {
            String sql = "select * from alumnos where ci_alumno='" + alumno.getCi_alumno() + "'";
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    alumno.setCi_alumno("");
                } else {
                    alumno.setCi_alumno(alumno.getCi_alumno());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return alumno;
    }
}