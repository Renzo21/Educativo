<%@page import="Modelos.Calificaciones"%>
<%@page import="Modelos.DetallesAsistencias"%>
<%@page import="Modelos.DetallesConvocatoria"%>
<%@page import="Modelos.Convocatorias"%>
<%@page import="Modelos.Asistencias"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.DetallesCalificacionesControlador"%>
<%@page import="Controladores.CalificacionesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_calificaciones = Integer.parseInt(request.getParameter("id_calificaciones"));
    String sfecha_calificacion = request.getParameter("fecha_calificacion");
    java.sql.Date fecha_calificacion = Utiles.stringToSqlDate(sfecha_calificacion);

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Calificaciones calificacion = CalificacionesControlador.buscarId(id_calificaciones);

    if (calificacion != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        calificacion = new Calificaciones();
        calificacion.setId_calificaciones(0);
        calificacion.setFecha_calificacion(fecha_calificacion);

        DetallesConvocatoria detalleconvocatoria = new DetallesConvocatoria();
        calificacion.setDetalleconvocatoria(detalleconvocatoria);
    }

    
    

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_calificaciones", String.valueOf(calificacion.getId_calificaciones()));
    obj.put("id_asignatura", String.valueOf(calificacion.getDetalleconvocatoria().getAsignatura().getId_asignatura()));
    obj.put("id_convocatoria", String.valueOf(calificacion.getDetalleconvocatoria().getConvocatoria().getId_convocatoria()));
    obj.put("id_detalleconvocatoria", String.valueOf(calificacion.getDetalleconvocatoria().getId_detalleconvocatoria()));
    obj.put("nombre_asignatura", calificacion.getDetalleconvocatoria().getAsignatura().getNombre_asignatura());
    obj.put("id_etapa", String.valueOf(calificacion.getEtapa().getId_etapa()));
    obj.put("nombre_etapa", calificacion.getEtapa().getNombre_etapa());
    obj.put("nombre_grado", calificacion.getDetalleconvocatoria().getConvocatoria().getGrado().getNombre_grado());
    obj.put("nombre_seccion", calificacion.getDetalleconvocatoria().getConvocatoria().getSeccion().getNombre_seccion());
    obj.put("nombre_turno", calificacion.getDetalleconvocatoria().getConvocatoria().getTurno().getNombre_turno());
    obj.put("fecha_calificacion", String.valueOf(calificacion.getFecha_calificacion()));

    out.print(obj);
    out.flush();
%>