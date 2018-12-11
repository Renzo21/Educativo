<%@page import="Modelos.Frecuencias"%>
<%@page import="Modelos.Horarios"%>
<%@page import="Modelos.Asignaturas"%>
<%@page import="Modelos.Profesores"%>
<%@page import="Modelos.Convocatorias"%>
<%@page import="Controladores.DetallesConvocatoriasControlador"%>
<%@page import="Modelos.DetallesConvocatoria"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleconvocatoria = Integer.parseInt(request.getParameter("id_detalleconvocatoria"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesConvocatoria detalleconvocatoria = DetallesConvocatoriasControlador.buscarId(id_detalleconvocatoria);
    if (detalleconvocatoria != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detalleconvocatoria = new DetallesConvocatoria();
        detalleconvocatoria.setId_detalleconvocatoria(0);
        
        Profesores profesor = new Profesores();
        profesor.setId_profesor(0);
        profesor.setNombre_profesor("");
        profesor.setApellido_profesor("");
        detalleconvocatoria.setProfesor(profesor);
        
        Asignaturas asignatura = new Asignaturas();
        asignatura.setId_asignatura(0);
        asignatura.setNombre_asignatura("");
        detalleconvocatoria.setAsignatura(asignatura);
        
        Horarios horario = new Horarios();
        horario.setId_horario(0);
        horario.setHora_inicio("");
        horario.setHora_fin("");
        detalleconvocatoria.setHorario(horario);
        
        Frecuencias frecuencia = new Frecuencias();
        frecuencia.setId_frecuencia(0);
        frecuencia.setNombre_frecuencia("");
        detalleconvocatoria.setFrecuencia(frecuencia);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detalleconvocatoria", String.valueOf(detalleconvocatoria.getId_detalleconvocatoria()));
    obj.put("id_profesor", String.valueOf(detalleconvocatoria.getProfesor().getId_profesor()));
    obj.put("nombre_profesor", detalleconvocatoria.getProfesor().getNombre_profesor());
    obj.put("apellido_profesor", detalleconvocatoria.getProfesor().getApellido_profesor());
    obj.put("id_asignatura", String.valueOf(detalleconvocatoria.getAsignatura().getId_asignatura()));
    obj.put("nombre_asignatura", detalleconvocatoria.getAsignatura().getNombre_asignatura());
    obj.put("id_horario", String.valueOf(detalleconvocatoria.getHorario().getId_horario()));
    obj.put("hora_inicio", detalleconvocatoria.getHorario().getHora_inicio());
    obj.put("hora_fin", detalleconvocatoria.getHorario().getHora_fin());
    obj.put("id_frecuencia", String.valueOf(detalleconvocatoria.getFrecuencia().getId_frecuencia()));
    obj.put("nombre_frecuencia", detalleconvocatoria.getFrecuencia().getNombre_frecuencia());
    obj.put("id_aula", String.valueOf(detalleconvocatoria.getAula().getId_aula()));
    obj.put("nombre_aula", detalleconvocatoria.getAula().getNombre_aula());
    
    out.print(obj);
    out.flush();
%>