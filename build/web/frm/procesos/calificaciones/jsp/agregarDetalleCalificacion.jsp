<%@page import="Modelos.DetallesConvocatoria"%>
<%@page import="Modelos.Calificaciones"%>
<%@page import="Modelos.DetallesCalificaciones"%>
<%@page import="Modelos.Convocatorias"%>
<%@page import="Modelos.Inscripciones"%>
<%@page import="Modelos.Asistencias"%>
<%@page import="Modelos.DetallesAsistencias"%>
<%@page import="Controladores.DetallesCalificacionesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    //int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    //int id_detalleasistencias = Integer.parseInt(request.getParameter("id_detalleasistencias"));
    //int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
    int id_calificaciones = Integer.parseInt(request.getParameter("id_calificaciones")); 
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria")); 
    int nota_final = 0;

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    DetallesCalificaciones detallecalificacion = new DetallesCalificaciones();
    detallecalificacion.setNota_final(nota_final);
    
    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);
    
    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setConvocatoria(convocatoria);
    
    Calificaciones calificacion = new Calificaciones();
    calificacion.setId_calificaciones(id_calificaciones);
    detallecalificacion.setCalificacion(calificacion);
    detallecalificacion.setInscripcion(inscripcion);
      
    if (DetallesCalificacionesControlador.agregar(detallecalificacion)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>