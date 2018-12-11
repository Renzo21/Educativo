<%@page import="Modelos.Mallas"%>
<%@page import="Controladores.MallasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_malla = Integer.parseInt(request.getParameter("id_malla"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Mallas malla = new Mallas();
    malla.setId_malla(id_malla);

    if (MallasControlador.eliminar(malla)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>