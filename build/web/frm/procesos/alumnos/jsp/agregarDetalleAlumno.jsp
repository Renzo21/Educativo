<%@page import="Modelos.Responsables"%>
<%@page import="Modelos.Alumnos"%>
<%@page import="Modelos.DetallesAlumnos"%>
<%@page import="Controladores.DetallesAlumnosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    //int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    int id_detallealumnos = Integer.parseInt(request.getParameter("id_detallealumnos"));
    int id_alumno = Integer.parseInt(request.getParameter("id_alumno"));
    int id_responsable = Integer.parseInt(request.getParameter("id_responsable")); 
    String categoria_responsable = request.getParameter("categoria_responsable");

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    DetallesAlumnos detallealumno = new DetallesAlumnos();
    //detallealumno.setId_detallealumnos(id_detallealumnos);
    detallealumno.setCategoria_responsable(categoria_responsable);
    
    Alumnos alumno = new Alumnos();
    alumno.setId_alumno(id_alumno);
    detallealumno.setAlumno(alumno);
    
    Responsables responsable = new Responsables();
    responsable.setId_responsable(id_responsable);
    detallealumno.setResponsable(responsable);
      
    if (DetallesAlumnosControlador.agregar(detallealumno)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>