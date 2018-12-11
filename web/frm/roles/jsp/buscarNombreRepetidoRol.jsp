<%@page import="Controladores.RolesControlador"%>
<%@page import="Modelos.Roles"%>
<%@page import="Controladores.MenusControlador"%>
<%@page import="Modelos.Menus"%>
<%@page import="Controladores.FormulariosControlador"%>
<%@page import="Modelos.Formularios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Asignaturas"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.AsignaturasControlador"%>
<%

    String nombre_rol = request.getParameter("nombre_rol");

    String tipo = "error";
 
    Roles rol = new Roles();
    rol.setNombre_rol(nombre_rol);
    RolesControlador.buscarNombreRepetidoRol(rol);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_rol", rol.getNombre_rol());
    System.out.println(rol.getNombre_rol());
    out.print(obj);
    out.flush();
%>