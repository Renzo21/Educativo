<%@page import="Modelos.Convocatorias"%>
<%@page import="Modelos.Alumnos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Inscripciones"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.InscripcionesControlador"%>
<%

    int id_alumno = Integer.parseInt(request.getParameter("id_alumno"));
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    
    Alumnos alumno = new Alumnos();
    alumno.setId_alumno(id_alumno);
    
    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);
    
    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setAlumno(alumno);
    inscripcion.setConvocatoria(convocatoria);
    InscripcionesControlador.doble(inscripcion);

    //se realiza una codicion para enviarla al archivo js dependiendo
    //del resultado de busqueda se enviaran un mensaje especifico
    //y los datos nesecitados
    if (inscripcion.getId_inscripcion() != 0) {

        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        tipo = "success";
        mensaje = "El alumno ya esta inscripto";
        nuevo = "true";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_inscripcion", String.valueOf(inscripcion.getId_inscripcion()));
    obj.put("id_alumno", inscripcion.getAlumno().getId_alumno());
    obj.put("id_convocatoria", inscripcion.getConvocatoria().getId_convocatoria());
    out.flush();
%>