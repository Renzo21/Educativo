<%@page import="Modelos.DetallesConvocatoria"%>
<%@page import="Modelos.Convocatorias"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.AsistenciasControlador"%>
<%@page import="Modelos.Asistencias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_asistencia = Integer.parseInt(request.getParameter("id_asistencia"));
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    int id_detalleconvocatoria = Integer.parseInt(request.getParameter("id_detalleconvocatoria"));
    String sfecha_asistencia = request.getParameter("fecha_asistencia");
    java.sql.Date fecha_asistencia = Utiles.stringToSqlDate(sfecha_asistencia);
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Asistencias asistencia = new Asistencias();
    asistencia.setId_asistencia(id_asistencia);
    asistencia.setFecha_asistencia(fecha_asistencia);
    
    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);
    asistencia.setConvocatoria(convocatoria);
    
    DetallesConvocatoria detalleconvocatoria = new DetallesConvocatoria();
    detalleconvocatoria.setId_detalleconvocatoria(id_detalleconvocatoria);
    asistencia.setDetalleconvocatria(detalleconvocatoria);
    
    if (AsistenciasControlador.modificar(asistencia)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>