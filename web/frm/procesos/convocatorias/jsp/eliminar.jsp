<%@page import="Controladores.ConvocatoriasControlador" %>
<%@page import="Modelos.Convocatorias" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);
    
    if(ConvocatoriasControlador.eliminar(convocatoria)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>