<%@page import="Controladores.EtapasControlador" %>
<%@page import="Modelos.Etapas" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_etapa = Integer.parseInt(request.getParameter("id_etapa"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Etapas etapa = new Etapas();
    etapa.setId_etapa(id_etapa);
    
    if(EtapasControlador.eliminar(etapa)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>