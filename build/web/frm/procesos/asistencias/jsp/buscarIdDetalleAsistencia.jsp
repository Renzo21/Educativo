<%@page import="Modelos.Convocatorias"%>
<%@page import="Modelos.Alumnos"%>
<%@page import="Modelos.Inscripciones"%>
<%@page import="Modelos.Asistencias"%>
<%@page import="Modelos.DetallesAsistencias"%>
<%@page import="Controladores.DetallesAsistenciasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    int id_asistencia = Integer.parseInt(request.getParameter("id_asistencia"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
     DetallesAsistencias detalleasistencia = new DetallesAsistencias();
    
    String contenido_detalle = DetallesAsistenciasControlador.buscarId(id_convocatoria,id_asistencia);
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
        Inscripciones inscripcion = new Inscripciones();
        inscripcion.setAlumno(alumno);
    }
    
   // String contenido_detalle = DetallesAsistenciasControlador.buscarId(id_convocatoria);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("estado_asistencia", detalleasistencia.getEstado_asistencia());
    obj.put("contenido_detalle", contenido_detalle);
    
    System.out.println(contenido_detalle);
    
    out.print(obj);
    out.flush();
%>