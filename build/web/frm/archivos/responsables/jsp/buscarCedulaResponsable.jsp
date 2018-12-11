<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Responsables"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.ResponsablesControlador"%>
<%

    String ci_responsable = request.getParameter("ci_responsable");

    String tipo = "error";
 
    Responsables responsable = new Responsables();
    responsable.setCi_responsable(ci_responsable);
    ResponsablesControlador.buscarCedula(responsable);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("ci_responsable", responsable.getCi_responsable());
    System.out.println(responsable.getCi_responsable());
    out.print(obj);
    out.flush();
%>