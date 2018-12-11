<%@page import="Modelos.Calificaciones"%>
<%@page import="Modelos.Convocatorias"%>
<%@page import="Modelos.Alumnos"%>
<%@page import="Modelos.Inscripciones"%>
<%@page import="Modelos.Asistencias"%>
<%@page import="Modelos.DetallesCalificaciones"%>
<%@page import="Controladores.DetallesCalificacionesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    int id_calificaciones = Integer.parseInt(request.getParameter("id_calificaciones"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
     DetallesCalificaciones detallecalificacion = new DetallesCalificaciones();
    
    String contenido_detalle = DetallesCalificacionesControlador.buscarId(id_convocatoria,id_calificaciones);
    if (detallecalificacion != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallecalificacion = new DetallesCalificaciones();
        detallecalificacion.setId_detallecalificacion(0);
        detallecalificacion.setNota_final(0);
        
        Calificaciones calificacion = new Calificaciones();
        calificacion.setId_calificaciones(0);
        detallecalificacion.setCalificacion(calificacion);
        
        Alumnos alumno = new Alumnos();
        Inscripciones inscripcion = new Inscripciones();
        inscripcion.setAlumno(alumno);
        detallecalificacion.setInscripcion(inscripcion);
    }
    
   // String contenido_detalle = DetallesAsistenciasControlador.buscarId(id_convocatoria);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("nota_final", detallecalificacion.getNota_final());
    obj.put("contenido_detalle", contenido_detalle);
    
    System.out.println(contenido_detalle);
    
    out.print(obj);
    out.flush();
%>