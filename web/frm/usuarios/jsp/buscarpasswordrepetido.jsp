<%@page import="Controladores.UsuariosControlador"%>
<%@page import="Modelos.Usuarios"%>
<%@page import="Controladores.MenusControlador"%>
<%@page import="Modelos.Menus"%>
<%@page import="Controladores.FormulariosControlador"%>
<%@page import="Modelos.Formularios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Asignaturas"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.AsignaturasControlador"%>
<%

    String password_usuario = request.getParameter("password_usuario");

    String tipo = "error";
 
    Usuarios usuario = new Usuarios();
    usuario.setPassword_usuario(password_usuario);
    UsuariosControlador.buscarpasswordrepetido(usuario);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("password_usuario", usuario.getPassword_usuario());
    System.out.println(usuario.getPassword_usuario());
    out.print(obj);
    out.flush();
%>