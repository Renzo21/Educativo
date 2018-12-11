<%@page import="Controladores.EtapasControlador" %>
<%@page import="Modelos.Etapas" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import="java.sql.ResultSet" %>
<%
    int id_etapa = Integer.parseInt(request.getParameter("id_etapa"));
    String nombre_etapa = request.getParameter("nombre_etapa");
    
    String tipo="error";
    String mensaje="Datos no modificados";
    
    Etapas etapa = new Etapas();
    etapa.setId_etapa(id_etapa);
    etapa.setNombre_etapa(nombre_etapa);
    
    if(EtapasControlador.modificar(etapa)){
        tipo = "succes";
        mensaje = "Datos Modificados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>