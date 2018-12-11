<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Tiposfaltas"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.TiposfaltasControlador"%>
<%

    int id_tipofalta = Integer.parseInt(request.getParameter("id_tipofalta"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Tiposfaltas tipofalta = new Tiposfaltas();
    tipofalta.setId_tipofalta(id_tipofalta);
    TiposfaltasControlador.buscarId(tipofalta);

    if (tipofalta.getId_tipofalta() != 0) {

        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        tipo = "success";
        mensaje = "Datos no encontrados";
        nuevo = "true";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_tipofalta", tipofalta.getId_tipofalta());
    obj.put("descripcion_tipofalta", tipofalta.getDescripcion_tipofalta());
    out.print(obj);
    out.flush();
%>