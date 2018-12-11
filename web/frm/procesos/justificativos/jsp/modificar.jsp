<%@page import="Modelos.Justificativos"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.JustificativosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_justificativo = Integer.parseInt(request.getParameter("id_justificativo"));
    //String sfecha_asistencia = request.getParameter("fecha_asistencia");
    //java.sql.Date fecha_asistencia = Utiles.stringToSqlDate(sfecha_asistencia);
    String motivo_justificativo = request.getParameter("motivo_justificativo");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Justificativos justificativo = new Justificativos();
    justificativo.setId_justificativo(id_justificativo);
    justificativo.setMotivo_justificativo(motivo_justificativo);
   
    if (JustificativosControlador.modificar(justificativo)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>