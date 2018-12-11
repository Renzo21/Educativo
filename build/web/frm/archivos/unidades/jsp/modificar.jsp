<%@page import="Modelos.Asignaturas"%>
<%@page import="Controladores.UnidadesControlador" %>
<%@page import="Modelos.Unidades" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import="java.sql.ResultSet" %>
<%
    int id_unidad = Integer.parseInt(request.getParameter("id_unidad"));
    int id_asignatura = Integer.parseInt(request.getParameter("id_asignatura"));
    String descripcion_unidad = request.getParameter("descripcion_unidad");
    
    String tipo="error";
    String mensaje="Datos no modificados";
    
    Asignaturas asignatura = new Asignaturas();
    asignatura.setId_asignatura(id_asignatura);
    
    Unidades unidad = new Unidades();
    unidad.setId_unidad(id_unidad);
    unidad.setDescripcion_unidad(descripcion_unidad);
    unidad.setAsignatura(asignatura);
    
    if(UnidadesControlador.modificar(unidad)){
        tipo = "succes";
        mensaje = "Datos Modificados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>