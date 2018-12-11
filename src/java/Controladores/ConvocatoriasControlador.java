/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Convocatorias;
import Modelos.Especialidades;
import Modelos.Grados;
import Modelos.Periodos;
import Modelos.Secciones;
import Modelos.Sedes;
import Modelos.Turnos;
import java.sql.Statement;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class ConvocatoriasControlador {

    public static Convocatorias buscarId(int id) {
        Convocatorias convocatorias = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias c, sedes s, grados g, especialidades e"
                        + ", secciones se, turnos t, periodos p "
                        + "where c.id_sede=s.id_sede and c.id_grado=g.id_grado and "
                        + "c.id_especialidad=e.id_especialidad and c.id_seccion=se.id_seccion and "
                        + "c.id_turno=t.id_turno and c.id_periodo=p.id_periodo and "
                        + "id_convocatoria=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        convocatorias = new Convocatorias();
                        convocatorias.setId_convocatoria(rs.getInt("id_convocatoria"));
                        Sedes sede = new Sedes();
                        sede.setId_sede(rs.getInt("id_sede"));
                        sede.setNombre_sede(rs.getString("nombre_sede"));
                        Grados grado = new Grados();
                        grado.setId_grado(rs.getInt("id_grado"));
                        grado.setNombre_grado(rs.getString("nombre_grado"));
                        Especialidades especialidad = new Especialidades();
                        especialidad.setId_especialidad(rs.getInt("id_especialidad"));
                        especialidad.setNombre_especialidad(rs.getString("nombre_especialidad"));
                        Secciones seccion = new Secciones();
                        seccion.setId_seccion(rs.getInt("id_seccion"));
                        seccion.setNombre_seccion(rs.getString("nombre_seccion"));
                        Turnos turno = new Turnos();
                        turno.setId_turno(rs.getInt("id_turno"));
                        turno.setNombre_turno(rs.getString("nombre_turno"));
                        Periodos periodo = new Periodos();
                        periodo.setId_periodo(rs.getInt("id_periodo"));
                        periodo.setNombre_periodo(rs.getString("nombre_periodo"));
                        convocatorias.setSede(sede);
                        convocatorias.setGrado(grado);
                        convocatorias.setEspecialidad(especialidad);
                        convocatorias.setSeccion(seccion);
                        convocatorias.setTurno(turno);
                        convocatorias.setPeriodo(periodo);
                        convocatorias.setCupo_convocatoria(rs.getInt("cupo_convocatoria"));
                        convocatorias.setEstado_convocatoria(rs.getString("estado_convocatoria"));
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return convocatorias;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias c, sedes s, grados g, especialidades e, secciones se,"
                        + "turnos t, periodos p "
                        + "where c.id_sede=s.id_sede and c.id_grado=g.id_grado and "
                        + "c.id_especialidad=e.id_especialidad and c.id_seccion=se.id_seccion and "
                        + "c.id_turno=t.id_turno and c.id_periodo=p.id_periodo and "
                        + "upper(nombre_sede) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_convocatoria "
                        + "offset " + offset + " limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_convocatoria") + "</td>"
                                + "<td>" + rs.getString("nombre_sede") + "</td>"
                                + "<td>" + rs.getString("nombre_grado") + "</td>"
                                + "<td>" + rs.getString("nombre_especialidad") + "</td>"
                                + "<td>" + rs.getString("nombre_seccion") + "</td>"
                                + "<td>" + rs.getString("nombre_turno") + "</td>"
                                + "<td>" + rs.getString("nombre_periodo") + "</td>"
                                + "<td>" + rs.getString("cupo_convocatoria") + "</td>"
                                + "<td>" + rs.getString("estado_convocatoria") + "</td>"
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

    public static boolean agregar(Convocatorias convocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = convocatoria.getSede().getId_sede();
            int v2 = convocatoria.getGrado().getId_grado();
            int v3 = convocatoria.getEspecialidad().getId_especialidad();
            int v4 = convocatoria.getSeccion().getId_seccion();
            int v5 = convocatoria.getTurno().getId_turno();
            int v6 = convocatoria.getPeriodo().getId_periodo();
            int v7 = convocatoria.getCupo_convocatoria();
            String v8 = convocatoria.getEstado_convocatoria();

            String sql = "insert into convocatorias(id_sede,id_grado,id_especialidad,id_seccion,id_turno,id_periodo,"
                    + "cupo_convocatoria, estado_convocatoria) "
                    + "values('" + v1 + "','"
                    + v2 + "','"
                    + v3 + "','"
                    + v4 + "','"
                    + v5 + "','"
                    + v6 + "','"
                    + v7 + "','"
                    + v8 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_convocatoria = keyset.getInt(1);
                    convocatoria.setId_convocatoria(id_convocatoria);
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

    public static boolean modificar(Convocatorias convocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update convocatorias set id_sede=?"
                    + ", id_grado=?, id_especialidad=?, id_seccion=?, id_turno=?"
                    + ", id_periodo=?, cupo_convocatoria=?, estado_convocatoria=? "
                    + "where id_convocatoria=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, convocatoria.getSede().getId_sede());
                ps.setInt(2, convocatoria.getGrado().getId_grado());
                ps.setInt(3, convocatoria.getEspecialidad().getId_especialidad());
                ps.setInt(4, convocatoria.getSeccion().getId_seccion());
                ps.setInt(5, convocatoria.getTurno().getId_turno());
                ps.setInt(6, convocatoria.getPeriodo().getId_periodo());
                ps.setInt(7, convocatoria.getCupo_convocatoria());
                ps.setString(8, convocatoria.getEstado_convocatoria());
                ps.setInt(9, convocatoria.getId_convocatoria());
                //ps.setInt(2, pedido.getId_pedido());
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

    public static boolean eliminar(Convocatorias convocatoria  ) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from convocatorias where id_convocatoria=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, convocatoria.getId_convocatoria());
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
    
    public static boolean descontarcupo(Convocatorias convocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update convocatorias set cupo_convocatoria= cupo_convocatoria -? "
                    + "where id_convocatoria=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                
                ps.setInt(1, convocatoria.getCupo_convocatoria());
                ps.setInt(2, convocatoria.getId_convocatoria());
                //ps.setInt(2, pedido.getId_pedido());
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
}
