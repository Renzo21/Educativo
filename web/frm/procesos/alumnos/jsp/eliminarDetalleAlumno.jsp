<%@page import="Controladores.DetallesAlumnosControlador"%>
<%@page import="Modelos.DetallesAlumnos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallealumnos = Integer.parseInt(request.getParameter("id_detallealumnos"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesAlumnos detallealumno = new DetallesAlumnos();
    detallealumno.setId_detallealumnos(id_detallealumnos);

    if (DetallesAlumnosControlador.eliminar(detallealumno)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>