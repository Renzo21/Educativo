<%@page import="Modelos.DetallesCalificaciones"%>
<%@page import="Modelos.DetallesAsistencias"%>
<%@page import="Controladores.DetallesCalificacionesControlador"%>
<%@page import="Modelos.DetallesAlumnos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecalificacion = Integer.parseInt(request.getParameter("id_detallecalificacion"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesCalificaciones detallecalificacion = new DetallesCalificaciones();
    detallecalificacion.setId_detallecalificacion(id_detallecalificacion);

    if (DetallesCalificacionesControlador.eliminar(detallecalificacion)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>