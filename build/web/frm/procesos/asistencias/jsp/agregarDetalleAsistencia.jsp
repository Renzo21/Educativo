<%@page import="Modelos.Convocatorias"%>
<%@page import="Modelos.Inscripciones"%>
<%@page import="Modelos.Asistencias"%>
<%@page import="Modelos.DetallesAsistencias"%>
<%@page import="Controladores.DetallesAsistenciasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    //int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    //int id_detalleasistencias = Integer.parseInt(request.getParameter("id_detalleasistencias"));
    //int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
    int id_asistencia = Integer.parseInt(request.getParameter("id_asistencia")); 
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria")); 
    String estado_asistencia = "PRESENTE";

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    DetallesAsistencias detalleasistencia = new DetallesAsistencias();
    detalleasistencia.setEstado_asistencia(estado_asistencia);
    
    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);
    
    Asistencias asistencia = new Asistencias();
    asistencia.setId_asistencia(id_asistencia);
    asistencia.setConvocatoria(convocatoria);
    detalleasistencia.setAsistencia(asistencia);
      
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