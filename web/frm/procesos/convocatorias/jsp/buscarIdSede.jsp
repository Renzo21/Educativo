<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Sedes"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.SedesControlador"%>
<%

    int id_sede = Integer.parseInt(request.getParameter("id_sede"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Sedes sede = new Sedes();
    sede.setId_sede(id_sede);
    SedesControlador.buscarId(sede);

    if (sede.getId_sede() != 0) {

        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_sede", sede.getId_sede());
    obj.put("nombre_sede", sede.getNombre_sede());
    out.print(obj);
    out.flush();
%>