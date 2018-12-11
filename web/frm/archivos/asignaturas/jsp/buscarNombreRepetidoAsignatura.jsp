<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Asignaturas"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.AsignaturasControlador"%>
<%

    String nombre_asignatura = request.getParameter("nombre_asignatura");

    String tipo = "error";
 
    Asignaturas asignatura = new Asignaturas();
    asignatura.setNombre_asignatura(nombre_asignatura);
    AsignaturasControlador.buscarNombreRepetidoAsignatura(asignatura);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_asignatura", asignatura.getNombre_asignatura());
    System.out.println(asignatura.getNombre_asignatura());
    out.print(obj);
    out.flush();
%>