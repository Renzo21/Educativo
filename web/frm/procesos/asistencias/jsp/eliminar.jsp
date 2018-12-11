<%@page import="Modelos.Asistencias"%>
<%@page import="Controladores.AsistenciasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_asistencia = Integer.parseInt(request.getParameter("id_asistencia"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Asistencias asistencia = new Asistencias();
    asistencia.setId_asistencia(id_asistencia);

    if (AsistenciasControlador.eliminar(asistencia)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>