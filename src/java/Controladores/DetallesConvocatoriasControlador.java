/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Asignaturas;
import Modelos.Aulas;
import Modelos.Convocatorias;
import Modelos.DetallesConvocatoria;
import Modelos.Frecuencias;
import Modelos.Horarios;
import Modelos.Profesores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Luisao
 */
public class DetallesConvocatoriasControlador {
    public static DetallesConvocatoria buscarId(int id) {
        DetallesConvocatoria detalleconvocatoria = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalle_convocatorias dp, convocatorias c, profesores p,"
                        + "asignaturas a, horarios h, frecuencias f, aulas au"
                        + " where dp.id_convocatoria=c.id_convocatoria and dp.id_profesor=p.id_profesor and "
                        + "dp.id_asignatura=a.id_asignatura and dp.id_horario=h.id_horario and "
                        + "dp.id_frecuencia=f.id_frecuencia and  dp.id_aula=au.id_aula and "
                        + "dp.id_detalleconvocatoria=?";
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalleconvocatoria = new DetallesConvocatoria();
                        detalleconvocatoria.setId_detalleconvocatoria(rs.getInt("id_detalleconvocatoria"));
                        
                        Convocatorias convocatoria = new Convocatorias();
                        convocatoria.setId_convocatoria(rs.getInt("id_convocatoria"));
                        detalleconvocatoria.setConvocatoria(convocatoria);
                        
                        Profesores profesor = new Profesores();
                        profesor.setId_profesor(rs.getInt("id_profesor"));
                        profesor.setNombre_profesor(rs.getString("nombre_profesor"));
                        profesor.setApellido_profesor(rs.getString("apellido_profesor"));
                        detalleconvocatoria.setProfesor(profesor);
                        
                        Asignaturas asignatura = new Asignaturas();
                        asignatura.setId_asignatura(rs.getInt("id_asignatura"));
                        asignatura.setNombre_asignatura(rs.getString("nombre_asignatura"));
                        detalleconvocatoria.setAsignatura(asignatura);
                        
                        Horarios horario = new Horarios();
                        horario.setId_horario(rs.getInt("id_horario"));
                        horario.setHora_inicio(rs.getString("hora_inicio"));
                        horario.setHora_fin(rs.getString("hora_fin"));
                        detalleconvocatoria.setHorario(horario);
                        
                        Frecuencias frecuencia = new Frecuencias();
                        frecuencia.setId_frecuencia(rs.getInt("id_frecuencia"));
                        frecuencia.setNombre_frecuencia(rs.getString("nombre_frecuencia"));
                        detalleconvocatoria.setFrecuencia(frecuencia);
                        
                        Aulas aula = new Aulas();
                        aula.setId_aula(rs.getInt("id_aula"));
                        aula.setNombre_aula(rs.getString("nombre_aula"));
                        detalleconvocatoria.setAula(aula);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalleconvocatoria;
    }
    
    public static String buscarIdConvocatoria(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalle_convocatorias dp, convocatorias c, profesores p,"
                        + "asignaturas a, horarios h, frecuencias f, aulas au "
                        +"where dp.id_convocatoria=c.id_convocatoria and dp.id_profesor=p.id_profesor and "
                        + "dp.id_asignatura=a.id_asignatura and dp.id_horario=h.id_horario and "
                        + "dp.id_frecuencia=f.id_frecuencia and dp.id_aula=au.id_aula and "
                        + "dp.id_convocatoria="+id+" "+
                        "order by id_detalleconvocatoria";
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    //DecimalFormat df = new DecimalFormat( "#,###.00" );
                    String tabla = "";
                    //BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        //BigDecimal cantidad = rs.getBigDecimal("cantidad_articulopedido");
                        //total = total.add(cantidad);
                       // System.out.println("total"+total);
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_detalleconvocatoria") + "</td>"
                               + "<td>" + rs.getString("nombre_profesor") + "</td>" 
                               + "<td>" + rs.getString("apellido_profesor") + "</td>" 
                               + "<td>" + rs.getString("nombre_asignatura") + "</td>"
                               + "<td>" + rs.getString("nombre_aula") + "</td>"
                               + "<td class='centrado'>"
                                + "<button onclick='editarLinea("+rs.getString("id_detalleconvocatoria")+")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
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
    
    public static String buscarNombre(String nombre, int pagina ) {
        int offset=(pagina-1)*Utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalle_convocatorias dp, convocatorias c, profesores p,"
                        + "asignaturas a, horarios h, frecuencia f, aulas au "
                        +"where dp.id_convocatoria=c.id_convocatoria and dp.id_profesor=p.id_profesor and "
                        + "dp.id_asignatura=a.id_asignatura and dp.id_horario=h.id_horario and "
                        + "dp.id_frecuencia=f.id_frecuencia and dp.id_aula=au.id_aula and "
                        + "upper(a.nombre_asigntura) like '%" + 
                        nombre.toUpperCase() + 
                        "%' "+
                        "order by id_detalleconvocatoria "+
                        "offset "+ offset + " limit "+ Utiles.REGISTRO_PAGINA;
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_detalleconvocatoria") + "</td>"
                               + "<td>" + rs.getString("id_convocatoria") + "</td>"
                               + "<td>" + rs.getString("id_profesor") + "</td>"
                               + "<td>" + rs.getString("nombre_profesor") + "</td>"
                               + "<td>" + rs.getString("apellido_profesor") + "</td>"
                               + "<td>" + rs.getString("id_asignatura") + "</td>"
                               + "<td>" + rs.getString("nombre_asigntura") + "</td>"
                               + "<td>" + rs.getString("id_horario") + "</td>"
                               + "<td>" + rs.getString("hora_inicio") + "</td>"
                               + "<td>" + rs.getString("hora_fin") + "</td>"
                               + "<td>" + rs.getString("id_frecuencia") + "</td>"
                               + "<td>" + rs.getString("nombre_frecuencia") + "</td>"
                               + "<td>" + rs.getString("id_aula") + "</td>"
                               + "<td>" + rs.getString("nombre_aula") + "</td>"
                               //+ "<td>" + rs.getInt("cantidad_articulopedido") + "</td>" 
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
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

    public static boolean agregar(DetallesConvocatoria detalleconvocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detalle_convocatorias "
                    + "(id_convocatoria,id_profesor,id_asignatura,id_horario,id_frecuencia,id_aula) "
                    + "values (?,?,?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleconvocatoria.getConvocatoria().getId_convocatoria());
                ps.setInt(2, detalleconvocatoria.getProfesor().getId_profesor());
                ps.setInt(3, detalleconvocatoria.getAsignatura().getId_asignatura());
                ps.setInt(4, detalleconvocatoria.getHorario().getId_horario());
                ps.setInt(5, detalleconvocatoria.getFrecuencia().getId_frecuencia());
                ps.setInt(6, detalleconvocatoria.getAula().getId_aula());
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

    public static boolean modificar(DetallesConvocatoria detalleconvocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detalle_convocatorias set "
                    + "id_convocatoria=?,"
                    + "id_profesor=?,"
                    + "id_asignatura=?,"
                    + "id_horario=?,"
                    + "id_frecuencia=?,"
                    + "id_aula=? "
                    + "where id_detalleconvocatoria=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleconvocatoria.getConvocatoria().getId_convocatoria());
                ps.setInt(2, detalleconvocatoria.getProfesor().getId_profesor());
                ps.setInt(3, detalleconvocatoria.getAsignatura().getId_asignatura());
                ps.setInt(4, detalleconvocatoria.getHorario().getId_horario());
                ps.setInt(5, detalleconvocatoria.getFrecuencia().getId_frecuencia());
                ps.setInt(6, detalleconvocatoria.getAula().getId_aula());
                ps.setInt(7, detalleconvocatoria.getId_detalleconvocatoria());
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

    public static boolean eliminar(DetallesConvocatoria detalleconvocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detalle_convocatorias where id_detalleconvocatoria=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleconvocatoria.getId_detalleconvocatoria());
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
    
    public static boolean eliminarConvocatoriaDetalle(Convocatorias convocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detalle_convocatorias where id_convocatoria=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, convocatoria.getId_convocatoria());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + convocatoria.getId_convocatoria());
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
    
    public static DetallesConvocatoria buscarProfesor(int id) {
        DetallesConvocatoria detalleconvocatoria = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detalle_convocatorias dp, asignaturas a "
                        + "where dp.id_asignatura=a.id_asignatura and "
                        + "dp.id_asignatura=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalleconvocatoria = new DetallesConvocatoria();
                        detalleconvocatoria.setId_detalleconvocatoria(rs.getInt("id_detalleconvocatoria"));
                        
                        Asignaturas asignatura = new Asignaturas();
                        asignatura.setId_asignatura(rs.getInt("id_asignatura"));
                        detalleconvocatoria.setAsignatura(asignatura);
                        
                        Profesores profesor = new Profesores();
                        profesor.setNombre_profesor(rs.getString("nombre_profesor"));
                        profesor.setApellido_profesor(rs.getString("apellido_profesor"));
                        detalleconvocatoria.setProfesor(profesor);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalleconvocatoria;
    }
}
