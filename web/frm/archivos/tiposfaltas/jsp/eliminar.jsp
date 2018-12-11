<%@page import="Controladores.TiposfaltasControlador" %>
<%@page import="Modelos.Tiposfaltas" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_tipofalta = Integer.parseInt(request.getParameter("id_tipofalta"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Tiposfaltas tipofalta = new Tiposfaltas();
    tipofalta.setId_tipofalta(id_tipofalta);
    
    if(TiposfaltasControlador.eliminar(tipofalta)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>