<%@page import="Modelos.Responsables"%>
<%@page import="Modelos.Alumnos"%>
<%@page import="Modelos.DetallesAlumnos"%>
<%@page import="Controladores.DetallesAlumnosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallealumnos = Integer.parseInt(request.getParameter("id_detallealumnos"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesAlumnos detallealumnos = DetallesAlumnosControlador.buscarId(id_detallealumnos);
    if (detallealumnos != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallealumnos = new DetallesAlumnos();
        detallealumnos.setId_detallealumnos(0);
        detallealumnos.setCategoria_responsable("");
        
        Alumnos alumno = new Alumnos();
        alumno.setId_alumno(0);
        detallealumnos.setAlumno(alumno);
        
        Responsables responsable = new Responsables();
        responsable.setId_responsable(0);
        responsable.setNombre_responsable("");
        responsable.setApellido_responsable("");
        responsable.setCi_responsable("");
        detallealumnos.setResponsable(responsable);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detallealumnos", String.valueOf(detallealumnos.getId_detallealumnos()));
    obj.put("id_alumno", String.valueOf(detallealumnos.getAlumno().getId_alumno()));
    obj.put("id_responsable", String.valueOf(detallealumnos.getResponsable().getId_responsable()));
    obj.put("nombre_responsable", detallealumnos.getResponsable().getNombre_responsable());
    obj.put("apellido_responsable", detallealumnos.getResponsable().getApellido_responsable());
    obj.put("ci_responsable", detallealumnos.getResponsable().getCi_responsable());
    obj.put("categoria_responsable", detallealumnos.getCategoria_responsable());
    
    out.print(obj);
    out.flush();
%>