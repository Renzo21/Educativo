<%@page import="Modelos.Justificativos"%>
<%@page import="Modelos.Profesores"%>
<%@page import="Modelos.Asignaturas"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.JustificativosControlador"%>
<%@page import="Modelos.Asistencias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_justificativo = Integer.parseInt(request.getParameter("id_justificativo"));
    String motivo_justificativo = request.getParameter("motivo_justificativo");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Justificativos justificativo = new Justificativos();
    justificativo.setId_justificativo(id_justificativo);
    justificativo.setMotivo_justificativo(motivo_justificativo);
    
    if (JustificativosControlador.agregar(justificativo)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_justificativo", String.valueOf(justificativo.getId_justificativo()));
    out.print(obj);
    out.flush();
    
%>