<%@page import="Controladores.EspecialidadesControlador" %>
<%@page import="Modelos.Especialidades" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import="java.sql.ResultSet" %>
<%
    int id_especialidad = Integer.parseInt(request.getParameter("id_especialidad"));
    String nombre_especialidad = request.getParameter("nombre_especialidad");
    
    String tipo="error";
    String mensaje="Datos no modificados";
    
    Especialidades especialidad = new Especialidades();
    especialidad.setId_especialidad(id_especialidad);
    especialidad.setNombre_especialidad(nombre_especialidad);
    
    if(EspecialidadesControlador.modificar(especialidad)){
        tipo = "succes";
        mensaje = "Datos Modificados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>