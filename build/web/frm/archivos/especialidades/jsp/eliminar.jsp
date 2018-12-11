<%@page import="Controladores.EspecialidadesControlador" %>
<%@page import="Modelos.Especialidades" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_especialidad = Integer.parseInt(request.getParameter("id_especialidad"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Especialidades especialidad = new Especialidades();
    especialidad.setId_especialidad(id_especialidad);
    
    if(EspecialidadesControlador.eliminar(especialidad)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>