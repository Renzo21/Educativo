<%@page import="Controladores.AsignaturasControlador"%>
<%@page import="Modelos.Asignaturas"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Asignaturas asignatura = AsignaturasControlador.buscarMateria(id_convocatoria);
    if (asignatura != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        asignatura = new Asignaturas();
        asignatura.setId_asignatura(0);
        asignatura.setNombre_asignatura("");
        
    }
    
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    
    obj.put("id_asignatura", String.valueOf(asignatura.getId_asignatura()));
    obj.put("nombre_asignatura", asignatura.getNombre_asignatura());
    out.print(obj);
    out.flush();
%>