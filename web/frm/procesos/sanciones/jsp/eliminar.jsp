<%@page import="Modelos.Sanciones"%>
<%@page import="Controladores.SancionesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_sancion = Integer.parseInt(request.getParameter("id_sancion"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Sanciones sancion = new Sanciones();
    sancion.setId_sancion(id_sancion);

    if (SancionesControlador.eliminar(sancion)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>