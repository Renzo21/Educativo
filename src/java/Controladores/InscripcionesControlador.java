/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Alumnos;
import Modelos.Convocatorias;
import Modelos.Especialidades;
import Modelos.Grados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Inscripciones;
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
public class InscripcionesControlador {

    public static boolean agregar(Inscripciones inscripcion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "select * from convocatorias where id_convocatoria='"
                    + inscripcion.getConvocatoria().getId_convocatoria() + "'"
                    + " and cupo_convocatoria>0";
            System.out.println(sql);
            try {

                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    String sql1 = "insert into inscripciones(id_alumno,id_convocatoria,fecha_inscripcion"
                            + ",estado_inscripcion)"
                            + "values('" + inscripcion.getAlumno().getId_alumno() + "',"
                            + "'" + inscripcion.getConvocatoria().getId_convocatoria() + "',"
                            + "'" + inscripcion.getFecha_inscripcion() + "',"
                            + "'" + inscripcion.getEstado_inscripcion() + "')";
                    try {
                        Conexion.getSt().executeUpdate(sql1, Statement.RETURN_GENERATED_KEYS);
                        ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                        if (keyset.next()) {
                            int id_inscripcion = keyset.getInt(1);
                            inscripcion.setId_inscripcion(id_inscripcion);
                            Conexion.getConn().setAutoCommit(false);
                        }
                        Conexion.getSt().executeUpdate(sql1);
                        valor = true;
                    } catch (SQLException ex) {
                        System.err.println("Error:" + ex);
                    }
                }

            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }

        return valor;
    }

    //aqui en la funcion buscar id del controlador de ejecuta un comando para realizr una consulta
    //en la base de datos para verificar si existe ekl id solicitado
    //en caso de que exista el controlador enviara los datos necesitados
    public static Inscripciones buscarId(Inscripciones inscripcion) {
        if (Conexion.conectar()) {
            String sql = "select * from inscripciones i, alumnos a, convocatorias c, "
                    + "sedes s, grados g, especialidades e, secciones se, turnos t "
                    + "where i.id_alumno=a.id_alumno and "
                    + "i.id_convocatoria=c.id_convocatoria and c.id_sede=s.id_sede and "
                    + "c.id_grado=g.id_grado and c.id_especialidad=e.id_especialidad and "
                    + "c.id_seccion=se.id_seccion and c.id_turno=t.id_turno and "
                    + "id_inscripcion='" + inscripcion.getId_inscripcion() + "'";
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    Alumnos alumno = new Alumnos();
                    alumno.setId_alumno(rs.getInt("id_alumno"));
                    alumno.setNombre_alumno(rs.getString("nombre_alumno"));
                    alumno.setApellido_alumno(rs.getString("apellido_alumno"));
                    alumno.setCi_alumno(rs.getString("ci_alumno"));

                    Sedes sede = new Sedes();
                    sede.setNombre_sede(rs.getString("nombre_sede"));

                    Grados grado = new Grados();
                    grado.setNombre_grado(rs.getString("nombre_grado"));

                    Especialidades especialidad = new Especialidades();
                    especialidad.setNombre_especialidad(rs.getString("nombre_especialidad"));

                    Secciones seccion = new Secciones();
                    seccion.setNombre_seccion(rs.getString("nombre_seccion"));

                    Turnos turno = new Turnos();
                    turno.setNombre_turno(rs.getString("nombre_turno"));

                    Convocatorias convocatoria = new Convocatorias();
                    convocatoria.setId_convocatoria(rs.getInt("id_convocatoria"));
                    convocatoria.setSede(sede);
                    convocatoria.setGrado(grado);
                    convocatoria.setEspecialidad(especialidad);
                    convocatoria.setSeccion(seccion);
                    convocatoria.setTurno(turno);
                    inscripcion.setId_inscripcion(rs.getInt("id_inscripcion"));
                    inscripcion.setFecha_inscripcion(rs.getDate("fecha_inscripcion"));
                    inscripcion.setEstado_inscripcion(rs.getString("estado_inscripcion"));
                    inscripcion.setAlumno(alumno);
                    inscripcion.setConvocatoria(convocatoria);
                } else {
                    inscripcion.setId_inscripcion(0);
                    java.sql.Date fecha_inscripcion = new java.sql.Date(new java.util.Date().getTime());
                    inscripcion.setFecha_inscripcion(fecha_inscripcion);
                    
                    Alumnos alumno = new Alumnos();
                    alumno.setId_alumno(0);
                    alumno.setNombre_alumno("");
                    alumno.setApellido_alumno("");
                    alumno.setCi_alumno("");

                    Sedes sede = new Sedes();
                    sede.setNombre_sede("");

                    Grados grado = new Grados();
                    grado.setNombre_grado("");

                    Especialidades especialidad = new Especialidades();
                    especialidad.setNombre_especialidad("");

                    Secciones seccion = new Secciones();
                    seccion.setNombre_seccion("");

                    Turnos turno = new Turnos();
                    turno.setNombre_turno("");

                    Convocatorias convocatoria = new Convocatorias();
                    convocatoria.setId_convocatoria(0);
                    convocatoria.setSede(sede);
                    convocatoria.setGrado(grado);
                    convocatoria.setEspecialidad(especialidad);
                    convocatoria.setSeccion(seccion);
                    convocatoria.setTurno(turno);
                    inscripcion.setEstado_inscripcion("");
                    inscripcion.setAlumno(alumno);
                    inscripcion.setConvocatoria(convocatoria);
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return inscripcion;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from inscripciones i, alumnos a, convocatorias c, "
                        + "sedes s, grados g, especialidades e, secciones se, turnos t "
                        + "where i.id_alumno=a.id_alumno and "
                        + "i.id_convocatoria=c.id_convocatoria and c.id_sede=s.id_sede and "
                        + "c.id_grado=g.id_grado and c.id_especialidad=e.id_especialidad and "
                        + "c.id_turno=t.id_turno and c.id_seccion=se.id_seccion and "
                        + "upper (ci_alumno) like '%"
                        + nombre.toUpperCase() + "%'" + " order by id_inscripcion offset " + offset
                        + "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_inscripcion") + "</td>"
                                + "<td>" + rs.getString("nombre_alumno") + "</td>"
                                + "<td>" + rs.getString("apellido_alumno") + "</td>"
                                + "<td>" + rs.getString("ci_alumno") + "</td>"
                                + "<td>" + rs.getString("nombre_sede") + "</td>"
                                + "<td>" + rs.getString("nombre_grado") + "</td>"
                                + "<td>" + rs.getString("nombre_especialidad") + "</td>"
                                + "<td>" + rs.getString("nombre_seccion") + "</td>"
                                + "<td>" + rs.getString("nombre_turno") + "</td>"
                                + "<td>" + rs.getString("fecha_inscripcion") + "</td>"
                                + "<td>" + rs.getString("estado_inscripcion") + "</td>"
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

    public static boolean modificar(Inscripciones inscripcion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update inscripciones set id_alumno='" + inscripcion.getAlumno().getId_alumno() + "'"
                    + ", id_convocatoria='" + inscripcion.getConvocatoria().getId_convocatoria() + "'"
                    + ", fecha_inscripcion='" + inscripcion.getFecha_inscripcion() + "'"
                    + ", estado_inscripcion='" + inscripcion.getEstado_inscripcion() + "'"
                    + " where id_inscripcion=" + inscripcion.getId_inscripcion();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Inscripciones inscripcion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from inscripciones where id_inscripcion=" + inscripcion.getId_inscripcion();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error " + ex);
            }
        }
        return valor;
    }
    
    public static Inscripciones doble(Inscripciones inscripcion) {
        if (Conexion.conectar()) {
            String sql = "select * from inscripciones where id_alumno=" + 
                    inscripcion.getAlumno().getId_alumno() + " and id_convocatoria=" +
                    inscripcion.getConvocatoria().getId_convocatoria();
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    Alumnos alumno = new Alumnos();
                    alumno.setId_alumno(0);

                    Convocatorias convocatoria = new Convocatorias();
                    convocatoria.setId_convocatoria(0);
                    inscripcion.setId_inscripcion(0);
                    inscripcion.setAlumno(alumno);
                    inscripcion.setConvocatoria(convocatoria);
                } else {
                    inscripcion.setId_inscripcion(0);
                    java.sql.Date fecha_inscripcion = new java.sql.Date(new java.util.Date().getTime());
                    inscripcion.setFecha_inscripcion(fecha_inscripcion);
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return inscripcion;
    }
}
