<%@page import="Controladores.PermisossControlador"%>
<%@page import="Modelos.Formularios"%>
<%@page import="Modelos.Roles"%>
<%@page import="Modelos.Permisoss"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_permisoss = Integer.parseInt(request.getParameter("id_permisoss"));
    
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    Permisoss permisoss = PermisossControlador.buscarId(id_permisoss);
    if(permisoss!=null){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else{
        Roles rol = new Roles();
        Formularios formulario = new Formularios();
        permisoss = new Permisoss();
        permisoss.setRol(rol);
        permisoss.setFormulario(formulario);
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_rol", permisoss.getRol().getId_rol());
    obj.put("nombre_rol", permisoss.getRol().getNombre_rol());
    obj.put("id_formulario", permisoss.getFormulario().getId_formulario());
    obj.put("nombre_formulario", permisoss.getFormulario().getNombre_formulario());
    
    out.print(obj);
    out.flush();
%>