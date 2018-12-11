<%@page import="Modelos.Convocatorias"%>
<%@page import="Modelos.Alumnos"%>
<%@page import="Modelos.Justificativos"%>
<%@page import="Modelos.Inscripciones"%>
<%@page import="Modelos.DetallesJustificativos"%>
<%@page import="Controladores.DetallesJustificativosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallejustificativos = Integer.parseInt(request.getParameter("id_detallejustificativos"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesJustificativos detallejustificativo = DetallesJustificativosControlador.buscarId(id_detallejustificativos);
    if (detallejustificativo != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallejustificativo = new DetallesJustificativos();
        detallejustificativo.setId_detallejustificativos(0);
        
        Justificativos justificativo  = new Justificativos();
        detallejustificativo.setJustificativo(justificativo);
        
        Alumnos alumno = new Alumnos();
        Convocatorias convocatoria = new Convocatorias();
        
        Inscripciones inscripcion = new Inscripciones();
        inscripcion.setAlumno(alumno);
        inscripcion.setConvocatoria(convocatoria);
        detallejustificativo.setInscripcion(inscripcion);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_justificativo", String.valueOf(detallejustificativo.getJustificativo().getId_justificativo()));
    obj.put("id_inscripcion", String.valueOf(detallejustificativo.getInscripcion().getId_inscripcion()));
    obj.put("fecha_justificativo", String.valueOf(detallejustificativo.getFecha_justificativo()));
    obj.put("nombre_alumno", detallejustificativo.getInscripcion().getAlumno().getNombre_alumno());
    obj.put("apellido_alumno", detallejustificativo.getInscripcion().getAlumno().getApellido_alumno());
    obj.put("nombre_grado", detallejustificativo.getInscripcion().getConvocatoria().getGrado().getNombre_grado());
    obj.put("nombre_seccion", detallejustificativo.getInscripcion().getConvocatoria().getSeccion().getNombre_seccion());
    obj.put("nombre_turno", detallejustificativo.getInscripcion().getConvocatoria().getTurno().getNombre_turno());
    
    out.print(obj);
    out.flush();
%>