
<%@page import="Modelos.Usuarios"%>
<%@page import="Controladores.UsuariosControlador"%>
<%@page import="Modelos.Roles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   
    String usuario_usuario = request.getParameter("login_usuario");
    String clave_usuario = request.getParameter("password_usuario");
    
    String acceso = "false";
   
    
    Usuarios usuario = new Usuarios(0, "", usuario_usuario, clave_usuario, new Roles());
    if(UsuariosControlador.validarAcceso(usuario, request)!=null){
        acceso = "true";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("acceso", acceso);
    out.print(obj);
    out.flush();
%>