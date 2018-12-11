/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Ciudades;
import Modelos.Generos;
import Modelos.Nacionalidades;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Profesores;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class ProfesoresControlador {

    public static boolean agregar(Profesores profesor) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into profesores(nombre_profesor, apellido_profesor, ci_profesor,"
                    + "direccion_profesor, telefono_profesor, id_nacionalidad, id_ciudad, id_genero)"
                    + "values('" + profesor.getNombre_profesor() + "',"
                    + "'" + profesor.getApellido_profesor() + "',"
                    + "'" + profesor.getCi_profesor() + "',"
                    + "'" + profesor.getDireccion_profesor() + "',"
                    + "'" + profesor.getTelefono_profesor() + "',"
                    + "'" + profesor.getCiudad().getId_ciudad() + "',"
                    + "'" + profesor.getNacionalidad().getId_nacionalidad() + "',"
                    + "'" + profesor.getGenero().getId_genero() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Profesores buscarId(Profesores profesor) {
        if (Conexion.conectar()) {
            String sql = "select * from profesores p, nacionalidades n , ciudades c, generos g"
                    + " where p.id_nacionalidad=n.id_nacionalidad and p.id_ciudad=c.id_ciudad "
                    + "and p.id_genero=g.id_genero and "
                    + "id_profesor='" + profesor.getId_profesor() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                    Nacionalidades nacionalidad = new Nacionalidades();
                    nacionalidad.setId_nacionalidad(rs.getInt("id_nacionalidad"));
                    nacionalidad.setNombre_nacionalidad(rs.getString("nombre_nacionalidad"));
                    Generos genero = new Generos();
                    genero.setId_genero(rs.getInt("id_genero"));
                    genero.setNombre_genero(rs.getString("nombre_genero"));
                    profesor.setId_profesor(rs.getInt("id_profesor"));
                    profesor.setNombre_profesor(rs.getString("nombre_profesor"));
                    profesor.setApellido_profesor(rs.getString("apellido_profesor"));
                    profesor.setCi_profesor(rs.getString("ci_profesor"));
                    profesor.setDireccion_profesor(rs.getString("direccion_profesor"));
                    profesor.setTelefono_profesor(rs.getString("telefono_profesor"));
                    profesor.setCiudad(ciudad);
                    profesor.setNacionalidad(nacionalidad);
                    profesor.setGenero(genero);
                } else {
                    profesor.setId_profesor(0);
                    profesor.setNombre_profesor("");
                    profesor.setApellido_profesor("");
                    profesor.setCi_profesor("");
                    profesor.setDireccion_profesor("");
                    profesor.setTelefono_profesor("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return profesor;
    }

    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from profesores where upper (ci_profesor) like '%"+
                nombre.toUpperCase()+"%'"+" order by id_profesor offset " + offset +
                "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->"+sql);
                try(PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()){
                        tabla += "<tr>"+"<td>"+rs.getString("id_profesor")+"</td>"+
                        "<td>" + rs.getString("nombre_profesor")+"</td>"+
                        "<td>" + rs.getString("apellido_profesor")+"</td>"+
                        "<td>" + rs.getString("ci_profesor")+"</td>"+
                        "<td>" + rs.getString("direccion_profesor")+"</td>"+
                        "<td>" + rs.getString("telefono_profesor")+"</td>"
                                +"</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td colspan = 2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }catch (SQLException ex){
                    System.err.println("Error" + ex);
                }
                Conexion.cerrar();
            }catch(Exception ex){
                System.err.println("Error" + ex);
            }
        }
        Conexion.cerrar();
            return valor;
    }
    
    public static boolean modificar(Profesores profesor){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "update profesores set nombre_profesor='" + profesor.getNombre_profesor() + "'"
                    + ", apellido_profesor='" + profesor.getApellido_profesor() + "'"
                    + ", ci_profesor='" + profesor.getCi_profesor() + "'"
                    + ", direccion_profesor='" + profesor.getDireccion_profesor() + "'"
                    + ", telefono_profesor='" + profesor.getTelefono_profesor() + "'"
                    + ", id_ciudad='" + profesor.getCiudad().getId_ciudad() + "'"
                    + ", id_nacionalidad='" + profesor.getNacionalidad().getId_nacionalidad() + "'"
                    + ", id_genero='" + profesor.getGenero().getId_genero() + "'"
                    +" where id_profesor=" + profesor.getId_profesor();
            try{
               Conexion.getSt().executeUpdate(sql);
               valor =  true;
            }catch(SQLException ex){
                System.out.println("Error " + ex );
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Profesores profesor){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "delete from profesores where id_profesor="+profesor.getId_profesor();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            }catch(SQLException ex){
                System.err.println("Error "+ ex);
            }
        }
        return valor;
    }
    
    public static Profesores buscarCedula(Profesores profesor) {
        if (Conexion.conectar()) {
            String sql = "select * from profesores where ci_profesor='" + profesor.getCi_profesor() + "'";
            System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    profesor.setCi_profesor("");
                } else {
                    profesor.setCi_profesor(profesor.getCi_profesor());
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return profesor;
    }
}