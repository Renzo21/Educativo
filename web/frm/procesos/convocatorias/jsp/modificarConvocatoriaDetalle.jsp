<%@page import="Modelos.Aulas"%>
<%@page import="Modelos.Convocatorias"%>
<%@page import="Modelos.Horarios"%>
<%@page import="Modelos.Frecuencias"%>
<%@page import="Modelos.Asignaturas"%>
<%@page import="Modelos.Profesores"%>
<%@page import="Modelos.DetallesConvocatoria"%>
<%@page import="Controladores.DetallesConvocatoriasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_detalleconvocatoria = Integer.parseInt(request.getParameter("id_detalleconvocatoria"));
    int id_profesor = Integer.parseInt(request.getParameter("id_profesor"));
    int id_asignatura = Integer.parseInt(request.getParameter("id_asignatura")); 
    int id_horario = Integer.parseInt(request.getParameter("id_horario")); 
    int id_frecuencia = Integer.parseInt(request.getParameter("id_frecuencia")); 
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria")); 
    int id_aula = Integer.parseInt(request.getParameter("id_aula")); 

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    DetallesConvocatoria detalleconvocatoria = new DetallesConvocatoria();
    detalleconvocatoria.setId_detalleconvocatoria(id_detalleconvocatoria);
    
    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);
    
    Profesores profesor = new Profesores();
    profesor.setId_profesor(id_profesor);
    
    Asignaturas asignatura = new Asignaturas();
    asignatura.setId_asignatura(id_asignatura);
    
    Horarios horario = new Horarios();
    horario.setId_horario(id_horario);
    
    Frecuencias frecuencia = new Frecuencias();
    frecuencia.setId_frecuencia(id_frecuencia);
    
    Aulas aula = new Aulas();
    aula.setId_aula(id_aula);
    
    detalleconvocatoria.setProfesor(profesor);
    detalleconvocatoria.setAsignatura(asignatura);
    detalleconvocatoria.setHorario(horario);
    detalleconvocatoria.setFrecuencia(frecuencia);
    detalleconvocatoria.setConvocatoria(convocatoria);
    detalleconvocatoria.setAula(aula);
      
    if (DetallesConvocatoriasControlador.modificar(detalleconvocatoria)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>