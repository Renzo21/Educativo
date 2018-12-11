<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Horarios"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.HorariosControlador"%>
<%

    int id_horario = Integer.parseInt(request.getParameter("id_horario"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Horarios horario = new Horarios();
    horario.setId_horario(id_horario);
    HorariosControlador.buscarId(horario);

    if (horario.getId_horario() != 0) {

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
    obj.put("id_horario", horario.getId_horario());
    obj.put("hora_inicio", horario.getHora_inicio());
    obj.put("hora_fin", horario.getHora_fin());
    out.print(obj);
    out.flush();
%>