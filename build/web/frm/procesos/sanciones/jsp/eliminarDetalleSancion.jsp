<%@page import="Modelos.DetallesSanciones"%>
<%@page import="Controladores.DetallesSancionesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallesancion = Integer.parseInt(request.getParameter("id_detallesancion"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesSanciones detallesancion = new DetallesSanciones();
    detallesancion.setId_detallesancion(id_detallesancion);

    if (DetallesSancionesControlador.eliminar(detallesancion)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>