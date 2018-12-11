<%@page import="Modelos.Calificaciones"%>
<%@page import="Modelos.Asistencias"%>
<%@page import="Controladores.CalificacionesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_calificaciones = Integer.parseInt(request.getParameter("id_calificacion"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Calificaciones calificacion = new Calificaciones();
    calificacion.setId_calificaciones(id_calificaciones);

    if (CalificacionesControlador.eliminar(calificacion)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>