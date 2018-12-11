<%@page import="Controladores.ControlclasesControlador"%>
<%@page import="Modelos.Controlclases"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Asignaturas"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.AsignaturasControlador"%>
<%

    int id_controlclase = Integer.parseInt(request.getParameter("id_controlclase"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Controlclases controlclase = new Controlclases();
    controlclase.setId_controlclase(id_controlclase);
    ControlclasesControlador.buscarId(controlclase);

    if (controlclase.getId_controlclase() != 0) {

        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        tipo = "success";
        mensaje = "Datos no encontrados";
        nuevo = "true";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_controlclase", controlclase.getId_controlclase());
    obj.put("id_grado", controlclase.getGrado().getId_grado());
    obj.put("nombre_grado", controlclase.getGrado().getNombre_grado());
    obj.put("id_especialidad", controlclase.getEspecialidad().getId_especialidad());
    obj.put("nombre_especialidad", controlclase.getEspecialidad().getNombre_especialidad());
    obj.put("id_contenido", controlclase.getContenido().getId_contenido());
    obj.put("nombre_asignatura", controlclase.getContenido().getUnidad().getAsignatura().getId_asignatura());
    obj.put("descripcion_unidad", controlclase.getContenido().getUnidad().getDescripcion_unidad());
    obj.put("descripcion_contenido", controlclase.getContenido().getDescripcion_contenido());
    obj.put("id_profesor", controlclase.getProfesor().getId_profesor());
    obj.put("nombre_profesor", controlclase.getProfesor().getNombre_profesor());
    obj.put("apellido_profesor", controlclase.getProfesor().getApellido_profesor());
    obj.put("fecha_clase", String.valueOf(controlclase.getFecha_clase()));
    obj.put("progreso_clase", controlclase.getProgreso_clase());
    out.print(obj);
    out.flush();
%>