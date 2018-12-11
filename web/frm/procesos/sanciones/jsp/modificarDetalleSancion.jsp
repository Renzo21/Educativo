<%@page import="Modelos.Sanciones"%>
<%@page import="Modelos.DetallesSanciones"%>
<%@page import="utiles.Utiles"%>
<%@page import="Modelos.Inscripciones"%>
<%@page import="Modelos.DetallesSanciones"%>
<%@page import="Controladores.DetallesSancionesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_detallesancion = Integer.parseInt(request.getParameter("id_detallesancion"));
    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
    int id_sancion = Integer.parseInt(request.getParameter("id_sancion")); 
    String sfecha_sancion = request.getParameter("fecha_sancion");
    java.sql.Date fecha_sancion = Utiles.stringToSqlDate(sfecha_sancion);

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    DetallesSanciones detallesancion = new DetallesSanciones();
    detallesancion.setId_detallesancion(id_detallesancion);
    detallesancion.setFecha_sancion(fecha_sancion);
    
    Sanciones sancion = new Sanciones();
    sancion.setId_sancion(id_sancion);
    detallesancion.setSancion(sancion);
    
    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    detallesancion.setInscripcion(inscripcion);
      
    if (DetallesSancionesControlador.modificar(detallesancion)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>