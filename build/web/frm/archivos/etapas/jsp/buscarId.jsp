<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Etapas"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.EtapasControlador"%>
<%

    int id_etapa = Integer.parseInt(request.getParameter("id_etapa"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Etapas etapa = new Etapas();
    etapa.setId_etapa(id_etapa);
    EtapasControlador.buscarId(etapa);

    if (etapa.getId_etapa() != 0) {

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
    obj.put("id_etapa", etapa.getId_etapa());
    obj.put("nombre_etapa", etapa.getNombre_etapa());
    out.print(obj);
    out.flush();
%>