
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.ConvocatoriasControlador"%>
<%@page import="Modelos.Convocatorias"%>
<%@page import="Modelos.Responsables"%>
<%@page import="Modelos.Alumnos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.InscripcionesControlador"%>
<%@page import="Modelos.Inscripciones"%>
<%
    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
    int id_alumno = Integer.parseInt(request.getParameter("id_alumno"));
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
   String sfecha_inscripcion = request.getParameter("fecha_inscripcion");
    java.sql.Date fecha_inscripcion = Utiles.stringToSqlDate(sfecha_inscripcion);
    String estado_inscripcion = request.getParameter("estado_inscripcion");
    int cupo_convocatoria = 1;

    String tipo = "error";
    String mensaje = "Datos no agregados";

    Alumnos alumno = new Alumnos();
    alumno.setId_alumno(id_alumno);

    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);
    convocatoria.setCupo_convocatoria(cupo_convocatoria);

    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    inscripcion.setFecha_inscripcion(fecha_inscripcion);
    inscripcion.setEstado_inscripcion(estado_inscripcion);
    inscripcion.setAlumno(alumno);

    inscripcion.setConvocatoria(convocatoria);
    if (InscripcionesControlador.agregar(inscripcion)) {
        tipo = "success";
        mensaje = "Datos agregados";
        ConvocatoriasControlador.descontarcupo(convocatoria);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("cupo_convocatoria", inscripcion.getConvocatoria().getCupo_convocatoria());
    out.print(obj);
    out.flush();
%>