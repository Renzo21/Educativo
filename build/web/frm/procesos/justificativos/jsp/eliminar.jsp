<%@page import="Modelos.Justificativos"%>
<%@page import="Controladores.JustificativosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_justificativo = Integer.parseInt(request.getParameter("id_justificativo"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Justificativos justificativo = new Justificativos();
    justificativo.setId_justificativo(id_justificativo);

    if (JustificativosControlador.eliminar(justificativo)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>