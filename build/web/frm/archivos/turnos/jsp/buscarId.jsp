<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Turnos"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.TurnosControlador"%>
<%

    int id_turno = Integer.parseInt(request.getParameter("id_turno"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Turnos turno = new Turnos();
    turno.setId_turno(id_turno);
    TurnosControlador.buscarId(turno);

    if (turno.getId_turno() != 0) {

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
    obj.put("id_turno", turno.getId_turno());
    obj.put("nombre_turno", turno.getNombre_turno());
    out.print(obj);
    out.flush();
%>