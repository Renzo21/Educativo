<%@page import="Controladores.ControlclasesControlador"%>
<%@page import="Modelos.Controlclases"%>
<%@page import="Controladores.AsignaturasControlador" %>
<%@page import="Modelos.Asignaturas" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_controlclase = Integer.parseInt(request.getParameter("id_controlclase"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Controlclases controlclase = new Controlclases();
    controlclase.setId_controlclase(id_controlclase);
    
    if(ControlclasesControlador.eliminar(controlclase)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>