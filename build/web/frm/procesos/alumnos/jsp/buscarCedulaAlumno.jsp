<%@page import="Controladores.AlumnosControlador"%>
<%@page import="Modelos.Alumnos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Responsables"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.ResponsablesControlador"%>
<%

    String ci_alumno = request.getParameter("ci_alumno");

    String tipo = "error";
 
    Alumnos alumno = new Alumnos();
    alumno.setCi_alumno(ci_alumno);
    AlumnosControlador.buscarCedula(alumno);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("ci_alumno", alumno.getCi_alumno());
    System.out.println(alumno.getCi_alumno());
    out.print(obj);
    out.flush();
%>