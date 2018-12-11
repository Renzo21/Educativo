<%@page import="Controladores.GenerosControlador" %>
<%@page import="Modelos.Generos" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_genero = Integer.parseInt(request.getParameter("id_genero"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Generos genero = new Generos();
    genero.setId_genero(id_genero);
    
    if(GenerosControlador.eliminar(genero)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>