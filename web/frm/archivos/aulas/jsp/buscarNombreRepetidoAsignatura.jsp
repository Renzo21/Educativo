<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Aulas"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.AulasControlador"%>
<%

    String nombre_aula = request.getParameter("nombre_aula");

    String tipo = "error";
 
    Aulas aula = new Aulas();
    aula.setNombre_aula(nombre_aula);
    AulasControlador.buscarNombreRepetidoAula(aula);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_aula", aula.getNombre_aula());
    System.out.println(aula.getNombre_aula());
    out.print(obj);
    out.flush();
%>