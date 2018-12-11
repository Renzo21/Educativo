<%@page import="Modelos.DetallesJustificativos"%>
<%@page import="Controladores.DetallesJustificativosControlador"%>
<%@page import="Modelos.DetallesAlumnos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallejustificativos = Integer.parseInt(request.getParameter("id_detallejustificativos"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesJustificativos detallejustificativo = new DetallesJustificativos();
    detallejustificativo.setId_detallejustificativos(id_detallejustificativos);

    if (DetallesJustificativosControlador.eliminar(detallejustificativo)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>