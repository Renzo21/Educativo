<%@page import="Modelos.Justificativos"%>
<%@page import="utiles.Utiles"%>
<%@page import="Modelos.Inscripciones"%>
<%@page import="Modelos.DetallesJustificativos"%>
<%@page import="Controladores.DetallesJustificativosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    //int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    //int id_detallejustificativos = Integer.parseInt(request.getParameter("id_detallejustificativos"));
    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
    int id_justificativo = Integer.parseInt(request.getParameter("id_justificativo")); 
    String sfecha_justificativo = request.getParameter("fecha_justificativo");
    java.sql.Date fecha_justificativo = Utiles.stringToSqlDate(sfecha_justificativo);

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    DetallesJustificativos detallejustificativo = new DetallesJustificativos();
    detallejustificativo.setFecha_justificativo(fecha_justificativo);
    
    Justificativos justificativo = new Justificativos();
    justificativo.setId_justificativo(id_justificativo);
    detallejustificativo.setJustificativo(justificativo);
    
    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    detallejustificativo.setInscripcion(inscripcion);
      
    if (DetallesJustificativosControlador.agregar(detallejustificativo)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>