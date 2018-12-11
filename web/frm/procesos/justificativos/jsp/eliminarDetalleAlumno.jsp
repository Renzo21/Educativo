<%@page import="Modelos.DetallesAsistencias"%>
<%@page import="Controladores.DetallesAsistenciasControlador"%>
<%@page import="Modelos.DetallesAlumnos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleasistencias = Integer.parseInt(request.getParameter("id_detalleasistencias"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesAsistencias detalleasistencia = new DetallesAsistencias();
    detalleasistencia.setId_detalleasistencias(id_detalleasistencias);

    if (DetallesAsistenciasControlador.eliminar(detalleasistencia)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>