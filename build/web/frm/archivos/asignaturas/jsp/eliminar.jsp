<%@page import="Controladores.AsignaturasControlador" %>
<%@page import="Modelos.Asignaturas" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_asignatura = Integer.parseInt(request.getParameter("id_asignatura"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Asignaturas asignatura = new Asignaturas();
    asignatura.setId_asignatura(id_asignatura);
    
    if(AsignaturasControlador.eliminar(asignatura)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>