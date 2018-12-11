<%@page import="Controladores.SedesControlador" %>
<%@page import="Modelos.Sedes" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_sede = Integer.parseInt(request.getParameter("id_sede"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Sedes sede = new Sedes();
    sede.setId_sede(id_sede);
    
    if(SedesControlador.eliminar(sede)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>