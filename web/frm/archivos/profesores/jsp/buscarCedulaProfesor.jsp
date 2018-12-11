<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Profesores"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.ProfesoresControlador"%>
<%

    String ci_profesor = request.getParameter("ci_profesor");

    String tipo = "error";
 
    Profesores profesor = new Profesores();
    profesor.setCi_profesor(ci_profesor);
    ProfesoresControlador.buscarCedula(profesor);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("ci_profesor", profesor.getCi_profesor());
    System.out.println(profesor.getCi_profesor());
    out.print(obj);
    out.flush();
%>