<%@page import="Modelos.Usuarios"%>
<%@page import="Controladores.UsuariosControlador"%>
<%@page import="Controladores.RolesControlador"%>
<%@page import="Modelos.Roles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
    
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    Usuarios usuario = UsuariosControlador.buscarId(id_usuario);
    if(usuario!=null){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else{
        usuario = new Usuarios();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_usuario", usuario.getId_usuario());
    obj.put("id_rol", usuario.getRol().getId_rol());
    obj.put("nombre_rol", usuario.getRol().getNombre_rol());
    obj.put("nombre_usuario", usuario.getNombre_usuario());
    obj.put("login_usuario", usuario.getLogin_usuario());
    obj.put("password_usuario", usuario.getPassword_usuario());
    
    out.print(obj);
    out.flush();
%>