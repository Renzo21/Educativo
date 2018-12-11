<%@page import="Modelos.Sanciones"%>
<%@page import="Modelos.Convocatorias"%>
<%@page import="Modelos.Alumnos"%>
<%@page import="Modelos.Inscripciones"%>
<%@page import="Modelos.DetallesSanciones"%>
<%@page import="Controladores.DetallesSancionesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallesancion = Integer.parseInt(request.getParameter("id_detallesancion"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesSanciones detallesancion = DetallesSancionesControlador.buscarId(id_detallesancion);
    if (detallesancion != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallesancion = new DetallesSanciones();
        detallesancion.setId_detallesancion(0);
        
        Sanciones sancion  = new Sanciones();
        detallesancion.setSancion(sancion);
        
        Alumnos alumno = new Alumnos();
        Convocatorias convocatoria = new Convocatorias();
        
        Inscripciones inscripcion = new Inscripciones();
        inscripcion.setAlumno(alumno);
        inscripcion.setConvocatoria(convocatoria);
        detallesancion.setInscripcion(inscripcion);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_sancion", String.valueOf(detallesancion.getSancion().getId_sancion()));
    obj.put("id_inscripcion", String.valueOf(detallesancion.getInscripcion().getId_inscripcion()));
    obj.put("fecha_sancion", String.valueOf(detallesancion.getFecha_sancion()));
    obj.put("nombre_alumno", detallesancion.getInscripcion().getAlumno().getNombre_alumno());
    obj.put("apellido_alumno", detallesancion.getInscripcion().getAlumno().getApellido_alumno());
    obj.put("nombre_grado", detallesancion.getInscripcion().getConvocatoria().getGrado().getNombre_grado());
    obj.put("nombre_seccion", detallesancion.getInscripcion().getConvocatoria().getSeccion().getNombre_seccion());
    obj.put("nombre_turno", detallesancion.getInscripcion().getConvocatoria().getTurno().getNombre_turno());
    
    out.print(obj);
    out.flush();
%>