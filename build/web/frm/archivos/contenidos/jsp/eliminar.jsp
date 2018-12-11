<%@page import="Controladores.ContenidosControlador" %>
<%@page import="Modelos.Contenidos" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_contenido = Integer.parseInt(request.getParameter("id_contenido"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Contenidos contenido = new Contenidos();
    contenido.setId_contenido(id_contenido);
    
    if(ContenidosControlador.eliminar(contenido)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>