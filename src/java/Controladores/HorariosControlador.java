/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelos.Horarios;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class HorariosControlador {

    public static boolean agregar(Horarios horario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into horarios(hora_inicio,hora_fin)"
                    + "values('" + horario.getHora_inicio() + "',"
                    + "'" + horario.getHora_fin() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Horarios buscarId(Horarios horario) {
        if (Conexion.conectar()) {
            String sql = "select * from horarios where id_horario='" + horario.getId_horario() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    horario.setId_horario(rs.getInt("id_horario"));
                    horario.setHora_inicio(rs.getString("hora_inicio"));
                    horario.setHora_fin(rs.getString("hora_fin"));
                } else {
                    horario.setId_horario(0);
                    horario.setHora_inicio("");
                    horario.setHora_fin("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return horario;
    }

    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1) * Utiles.REGISTRO_PAGINA;
        String valor = "";
        if(Conexion.conectar()){
            try{
                String sql = "select * from horarios where upper (hora_inicio) like '%"+
                nombre.toUpperCase()+"%'"+" order by id_horario offset " + offset +
                "limit " + Utiles.REGISTRO_PAGINA;
                System.out.println("--->"+sql);
                try(PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()){
                        tabla += "<tr>"+
                                "<td>"+rs.getString("id_horario")+"</td>"+
                                "<td>" + rs.getString("hora_inicio")+"</td>"+
                                "<td>" + rs.getString("hora_fin")+"</td>"
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
    
    public static boolean modificar(Horarios horario){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "update horarios set hora_inicio='" + horario.getHora_inicio()+ "'"
                    + ", hora_fin='" + horario.getHora_fin() + "'"
                    +" where id_horario=" + horario.getId_horario();
            try{
               Conexion.getSt().executeUpdate(sql);
               valor =  true;
            }catch(SQLException ex){
                System.out.println("Error " + ex );
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Horarios horario){
        boolean valor = false;
        if(Conexion.conectar()){
            String sql = "delete from horarios where id_horario="+horario.getId_horario();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            }catch(SQLException ex){
                System.err.println("Error "+ ex);
            }
        }
        return valor;
    }
}