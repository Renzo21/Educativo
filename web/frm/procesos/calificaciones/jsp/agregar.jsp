<%@page import="Modelos.Etapas"%>
<%@page import="Modelos.Calificaciones"%>
<%@page import="Modelos.DetallesConvocatoria"%>
<%@page import="Modelos.Convocatorias"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.CalificacionesControlador"%>
<%@page import="Modelos.Asistencias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_etapa = Integer.parseInt(request.getParameter("id_etapa"));
    int id_detalleconvocatoria = Integer.parseInt(request.getParameter("id_detalleconvocatoria"));
    String sfecha_calificacion = request.getParameter("fecha_calificacion");
    java.sql.Date fecha_calificacion = Utiles.stringToSqlDate(sfecha_calificacion);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Calificaciones calificacion = new Calificaciones();
    calificacion.setFecha_calificacion(fecha_calificacion);
    
    Etapas etapa = new Etapas();
    etapa.setId_etapa(id_etapa);
    calificacion.setEtapa(etapa);
    
    DetallesConvocatoria detalleconvocatoria = new DetallesConvocatoria();
    detalleconvocatoria.setId_detalleconvocatoria(id_detalleconvocatoria);
    calificacion.setDetalleconvocatoria(detalleconvocatoria);
    
    if (CalificacionesControlador.agregar(calificacion)) {
        tipo = "success";
        mensaje = "Datos agregados.";
        
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_calificaciones", String.valueOf(calificacion.getId_calificaciones()));
    System.out.println("id_calificaciones" + calificacion.getId_calificaciones());
    out.print(obj);
    out.flush();
    
%>