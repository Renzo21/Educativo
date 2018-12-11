<%@page import="Modelos.Convocatorias"%>
<%@page import="Modelos.Asignaturas"%>
<%@page import="Modelos.Profesores"%>
<%@page import="Modelos.DetallesConvocatoria"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.DetallesConvocatoriasControlador"%>
<%@page import="Controladores.AsistenciasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_asignatura = Integer.parseInt(request.getParameter("id_asignatura"));
    
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesConvocatoria detalleconvocatoria = DetallesConvocatoriasControlador.buscarProfesor(id_asignatura);
    if (detalleconvocatoria != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detalleconvocatoria = new DetallesConvocatoria();
        detalleconvocatoria.setId_detalleconvocatoria(0);
        
        Convocatorias convocatoria = new Convocatorias();
        convocatoria.setId_convocatoria(0);
        detalleconvocatoria.setConvocatoria(convocatoria);
        
        Profesores profesor = new Profesores();
        profesor.setId_profesor(0);
        profesor.setNombre_profesor("");
        profesor.setApellido_profesor("");
        detalleconvocatoria.setProfesor(profesor);
    }
    
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    
    obj.put("id_detalleconvocatoria", String.valueOf(detalleconvocatoria.getId_detalleconvocatoria()));
    obj.put("id_asignatura", String.valueOf(detalleconvocatoria.getAsignatura().getId_asignatura()));
    
    out.print(obj);
    out.flush();
%>