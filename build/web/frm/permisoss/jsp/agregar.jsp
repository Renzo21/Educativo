
<%@page import="Controladores.PermisossControlador"%>
<%@page import="Modelos.Permisoss"%>
<%@page import="Modelos.Formularios"%>
<%@page import="Modelos.Roles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_permisoss = Integer.parseInt(request.getParameter("id_permisoss"));
    int id_rol = Integer.parseInt(request.getParameter("id_rol"));
    int id_formulario = Integer.parseInt(request.getParameter("id_formulario"));

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Roles rol = new Roles();
    rol.setId_rol(id_rol);
    
    Formularios formulario = new Formularios();
    formulario.setId_formulario(id_formulario);
   
    
    Permisoss permisoss = new Permisoss();
    permisoss.setId_permisoss(id_permisoss);
    permisoss.setRol(rol);
    permisoss.setFormulario(formulario);
    
    if (PermisossControlador.agregar(permisoss)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>