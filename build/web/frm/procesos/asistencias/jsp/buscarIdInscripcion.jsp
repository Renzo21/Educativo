<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Inscripciones"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.InscripcionesControlador"%>
<%

    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    InscripcionesControlador.buscarId(inscripcion);

    if (inscripcion.getId_inscripcion() != 0) {

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
    obj.put("id_inscripcion", inscripcion.getId_inscripcion());
    obj.put("id_alumno", inscripcion.getAlumno().getId_alumno());
    obj.put("nombre_alumno", inscripcion.getAlumno().getNombre_alumno());
    obj.put("apellido_alumno", inscripcion.getAlumno().getApellido_alumno());
    obj.put("ci_alumno", inscripcion.getAlumno().getCi_alumno());
    obj.put("id_convocatoria", inscripcion.getConvocatoria().getId_convocatoria());
    obj.put("nombre_sede", inscripcion.getConvocatoria().getSede().getNombre_sede());
    obj.put("nombre_grado", inscripcion.getConvocatoria().getGrado().getNombre_grado());
    obj.put("nombre_especialidad", inscripcion.getConvocatoria().getEspecialidad().getNombre_especialidad());
    obj.put("nombre_seccion", inscripcion.getConvocatoria().getSeccion().getNombre_seccion());
    obj.put("nombre_turno", inscripcion.getConvocatoria().getTurno().getNombre_turno());
    obj.put("fecha_inscripcion", inscripcion.getFecha_inscripcion());
    obj.put("estado_inscripcion", inscripcion.getEstado_inscripcion());
    out.print(obj);
    out.flush();
%>