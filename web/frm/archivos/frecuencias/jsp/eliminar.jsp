<%@page import="Controladores.FrecuenciasControlador" %>
<%@page import="Modelos.Frecuencias" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_frecuencia = Integer.parseInt(request.getParameter("id_frecuencia"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Frecuencias frecuencia = new Frecuencias();
    frecuencia.setId_frecuencia(id_frecuencia);
    
    if(FrecuenciasControlador.eliminar(frecuencia)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>