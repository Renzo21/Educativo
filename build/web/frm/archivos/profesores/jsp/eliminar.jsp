<%@page import="Controladores.ProfesoresControlador" %>
<%@page import="Modelos.Profesores" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_profesor = Integer.parseInt(request.getParameter("id_profesor"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Profesores profesor = new Profesores();
    profesor.setId_profesor(id_profesor);
    
    if(ProfesoresControlador.eliminar(profesor)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>