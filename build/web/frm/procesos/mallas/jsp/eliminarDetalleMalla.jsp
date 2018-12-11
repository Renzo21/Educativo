<%@page import="Modelos.DetallesMallas"%>
<%@page import="Modelos.DetallesSanciones"%>
<%@page import="Controladores.DetallesMallasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallemalla = Integer.parseInt(request.getParameter("id_detallemalla"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesMallas detallemalla = new DetallesMallas();
    detallemalla.setId_detallemalla(id_detallemalla);

    if (DetallesMallasControlador.eliminar(detallemalla)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>