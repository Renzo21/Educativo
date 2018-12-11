<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Unidades"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.UnidadesControlador"%>
<%

    int id_unidad = Integer.parseInt(request.getParameter("id_unidad"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Unidades unidad = new Unidades();
    unidad.setId_unidad(id_unidad);
    UnidadesControlador.buscarId(unidad);

    if (unidad.getId_unidad() != 0) {

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
    obj.put("id_unidad", unidad.getId_unidad());
    obj.put("id_asignatura", unidad.getAsignatura().getId_asignatura());
    obj.put("nombre_asignatura", unidad.getAsignatura().getNombre_asignatura());
    obj.put("descripcion_unidad", unidad.getDescripcion_unidad());
    out.print(obj);
    out.flush();
%>