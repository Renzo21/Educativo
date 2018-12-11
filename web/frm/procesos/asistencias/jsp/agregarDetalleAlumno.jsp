<%@page import="Modelos.Inscripciones"%>
<%@page import="Modelos.Asistencias"%>
<%@page import="Modelos.DetallesAsistencias"%>
<%@page import="Controladores.DetallesAsistenciasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    //int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    int id_detalleasistencias = Integer.parseInt(request.getParameter("id_detalleasistencias"));
    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
    int id_asistencia = Integer.parseInt(request.getParameter("id_asistencia")); 
    String estado_asistencia = request.getParameter("estado_asistencia");

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    DetallesAsistencias detalleasistencia = new DetallesAsistencias();
    detalleasistencia.setId_detalleasistencias(id_detalleasistencias);
    detalleasistencia.setEstado_asistencia(estado_asistencia);
    
    Asistencias asistencia = new Asistencias();
    asistencia.setId_asistencia(id_asistencia);
    detalleasistencia.setAsistencia(asistencia);
    
    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    detalleasistencia.setInscripcion(inscripcion);
      
    if (DetallesAsistenciasControlador.agregar(detalleasistencia)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>