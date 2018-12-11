<%@page import="Controladores.InscripcionesControlador" %>
<%@page import="Modelos.Inscripciones" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    
    if(InscripcionesControlador.eliminar(inscripcion)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>