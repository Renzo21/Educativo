<%@page import="Modelos.DetallesPermisos"%>
<%@page import="Controladores.DetallesPermisosControlador"%>
<%@page import="Modelos.DetallesAlumnos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallepermiso = Integer.parseInt(request.getParameter("id_detallepermiso"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesPermisos detallepermiso = new DetallesPermisos();
    detallepermiso.setId_detallepermiso(id_detallepermiso);

    if (DetallesPermisosControlador.eliminar(detallepermiso)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>