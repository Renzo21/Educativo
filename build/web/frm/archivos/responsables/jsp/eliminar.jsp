<%@page import="Controladores.ResponsablesControlador" %>
<%@page import="Modelos.Responsables" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_responsable = Integer.parseInt(request.getParameter("id_responsable"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Responsables responsable = new Responsables();
    responsable.setId_responsable(id_responsable);
    
    if(ResponsablesControlador.eliminar(responsable)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>