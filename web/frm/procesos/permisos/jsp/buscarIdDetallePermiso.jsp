<%@page import="Modelos.Convocatorias"%>
<%@page import="Modelos.Alumnos"%>
<%@page import="Modelos.Permisos"%>
<%@page import="Modelos.Inscripciones"%>
<%@page import="Modelos.DetallesPermisos"%>
<%@page import="Controladores.DetallesPermisosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallepermiso = Integer.parseInt(request.getParameter("id_detallepermiso"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesPermisos detallepermiso = DetallesPermisosControlador.buscarId(id_detallepermiso);
    if (detallepermiso != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallepermiso = new DetallesPermisos();
        detallepermiso.setDescripcion_permiso("");
        detallepermiso.setId_detallepermiso(0);
        
        Permisos permiso  = new Permisos();
        detallepermiso.setPermiso(permiso);
        
        Alumnos alumno = new Alumnos();
        Convocatorias convocatoria = new Convocatorias();
        
        Inscripciones inscripcion = new Inscripciones();
        inscripcion.setAlumno(alumno);
        inscripcion.setConvocatoria(convocatoria);
        detallepermiso.setInscripcion(inscripcion);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_permiso", String.valueOf(detallepermiso.getPermiso().getId_permiso()));
    obj.put("id_inscripcion", String.valueOf(detallepermiso.getInscripcion().getId_inscripcion()));
    obj.put("fecha_permiso", String.valueOf(detallepermiso.getFecha_permiso()));
    obj.put("nombre_alumno", detallepermiso.getInscripcion().getAlumno().getNombre_alumno());
    obj.put("apellido_alumno", detallepermiso.getInscripcion().getAlumno().getApellido_alumno());
    obj.put("nombre_grado", detallepermiso.getInscripcion().getConvocatoria().getGrado().getNombre_grado());
    obj.put("nombre_seccion", detallepermiso.getInscripcion().getConvocatoria().getSeccion().getNombre_seccion());
    obj.put("nombre_turno", detallepermiso.getInscripcion().getConvocatoria().getTurno().getNombre_turno());
    obj.put("descripcion_permiso", detallepermiso.getDescripcion_permiso());
    
    out.print(obj);
    out.flush();
%>