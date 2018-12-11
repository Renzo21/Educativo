<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Responsables"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.ResponsablesControlador"%>
<%

    int id_responsable = Integer.parseInt(request.getParameter("id_responsable"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Responsables responsable = new Responsables();
    responsable.setId_responsable(id_responsable);
    ResponsablesControlador.buscarId(responsable);

    if (responsable.getId_responsable() != 0) {

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
    obj.put("id_responsable", responsable.getId_responsable());
    obj.put("nombre_responsable", responsable.getNombre_responsable());
    obj.put("apellido_responsable", responsable.getApellido_responsable());
    obj.put("ci_responsable", responsable.getCi_responsable());
    out.print(obj);
    out.flush();
%>