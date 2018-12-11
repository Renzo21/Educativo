<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Asignaturas"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.AsignaturasControlador"%>
<%

    int id_asignatura = Integer.parseInt(request.getParameter("id_asignatura"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Asignaturas asignatura = new Asignaturas();
    asignatura.setId_asignatura(id_asignatura);
    AsignaturasControlador.buscarId(asignatura);

    if (asignatura.getId_asignatura() != 0) {

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
    obj.put("id_asignatura", asignatura.getId_asignatura());
    obj.put("nombre_asignatura", asignatura.getNombre_asignatura());
    out.print(obj);
    out.flush();
%>