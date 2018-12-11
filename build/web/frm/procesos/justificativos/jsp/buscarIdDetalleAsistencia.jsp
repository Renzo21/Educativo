<%@page import="Modelos.Convocatorias"%>
<%@page import="Modelos.Alumnos"%>
<%@page import="Modelos.Inscripciones"%>
<%@page import="Modelos.Asistencias"%>
<%@page import="Modelos.DetallesAsistencias"%>
<%@page import="Controladores.DetallesAsistenciasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleasistencias = Integer.parseInt(request.getParameter("id_detalleasistencias"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesAsistencias detalleasistencia = DetallesAsistenciasControlador.buscarId(id_detalleasistencias);
    if (detalleasistencia != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detalleasistencia = new DetallesAsistencias();
        detalleasistencia.setId_detalleasistencias(0);
        detalleasistencia.setEstado_asistencia("");
        
        Asistencias asistencia = new Asistencias();
        asistencia.setId_asistencia(0);
        detalleasistencia.setAsistencia(asistencia);
        
        Alumnos alumno = new Alumnos();
        Convocatorias convocatoria = new Convocatorias();
        
        Inscripciones inscripcion = new Inscripciones();
        inscripcion.setAlumno(alumno);
        inscripcion.setConvocatoria(convocatoria);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detalleasistencias", String.valueOf(detalleasistencia.getId_detalleasistencias()));
    obj.put("id_asistencia", String.valueOf(detalleasistencia.getAsistencia().getId_asistencia()));
    obj.put("id_inscripcion", String.valueOf(detalleasistencia.getInscripcion().getId_inscripcion()));
    obj.put("nombre_alumno", detalleasistencia.getInscripcion().getAlumno().getNombre_alumno());
    obj.put("apellido_alumno", detalleasistencia.getInscripcion().getAlumno().getApellido_alumno());
    obj.put("nombre_grado", detalleasistencia.getInscripcion().getConvocatoria().getGrado().getNombre_grado());
    obj.put("nombre_especialidad", detalleasistencia.getInscripcion().getConvocatoria().getEspecialidad().getNombre_especialidad());
    obj.put("nombre_seccion", detalleasistencia.getInscripcion().getConvocatoria().getSeccion().getNombre_seccion());
    obj.put("nombre_turno", detalleasistencia.getInscripcion().getConvocatoria().getTurno().getNombre_turno());
    obj.put("estado_asistencia", detalleasistencia.getEstado_asistencia());
    
    out.print(obj);
    out.flush();
%>