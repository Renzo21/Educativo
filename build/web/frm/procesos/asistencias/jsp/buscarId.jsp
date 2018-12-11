<%@page import="Modelos.DetallesAsistencias"%>
<%@page import="Modelos.DetallesConvocatoria"%>
<%@page import="Modelos.Convocatorias"%>
<%@page import="Modelos.Asistencias"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.DetallesAsistenciasControlador"%>
<%@page import="Controladores.AsistenciasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_asistencia = Integer.parseInt(request.getParameter("id_asistencia"));
    String sfecha_asistencia = request.getParameter("fecha_asistencia");
    java.sql.Date fecha_asistencia = Utiles.stringToSqlDate(sfecha_asistencia);

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Asistencias asistencia = AsistenciasControlador.buscarId(id_asistencia);

    if (asistencia != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        asistencia = new Asistencias();
        asistencia.setId_asistencia(0);
        asistencia.setFecha_asistencia(fecha_asistencia);
        
        Convocatorias convocatoria = new Convocatorias();
        asistencia.setConvocatoria(convocatoria);

        DetallesConvocatoria detalleconvocatoria = new DetallesConvocatoria();
        asistencia.setDetalleconvocatria(detalleconvocatoria);
    }

    
    

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_asistencia", String.valueOf(asistencia.getId_asistencia()));
    obj.put("id_asignatura", String.valueOf(asistencia.getDetalleconvocatoria().getAsignatura().getId_asignatura()));
    obj.put("id_convocatoria", String.valueOf(asistencia.getConvocatoria().getId_convocatoria()));
    obj.put("id_detalleconvocatoria", String.valueOf(asistencia.getDetalleconvocatoria().getId_detalleconvocatoria()));
    obj.put("nombre_asignatura", asistencia.getDetalleconvocatoria().getAsignatura().getNombre_asignatura());
    obj.put("nombre_profesor", asistencia.getDetalleconvocatoria().getProfesor().getNombre_profesor());
    obj.put("apellido_profesor", asistencia.getDetalleconvocatoria().getProfesor().getApellido_profesor());
    obj.put("nombre_grado", asistencia.getConvocatoria().getGrado().getNombre_grado());
    obj.put("nombre_seccion", asistencia.getConvocatoria().getSeccion().getNombre_seccion());
    obj.put("nombre_turno", asistencia.getConvocatoria().getTurno().getNombre_turno());
    obj.put("fecha_asistencia", String.valueOf(asistencia.getFecha_asistencia()));

    out.print(obj);
    out.flush();
%>