<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Contenidos"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.ContenidosControlador"%>
<%

    int id_contenido = Integer.parseInt(request.getParameter("id_contenido"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Contenidos contenido = new Contenidos();
    contenido.setId_contenido(id_contenido);
    ContenidosControlador.buscarId(contenido);

    if (contenido.getId_contenido() != 0) {

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
    obj.put("id_contenido", contenido.getId_contenido());
    obj.put("id_unidad", contenido.getUnidad().getId_unidad());
    obj.put("nombre_asignatura", contenido.getUnidad().getAsignatura().getNombre_asignatura());
    obj.put("descripcion_unidad", contenido.getUnidad().getDescripcion_unidad());
    obj.put("descripcion_contenido", contenido.getDescripcion_contenido());
    out.print(obj);
    out.flush();
%>