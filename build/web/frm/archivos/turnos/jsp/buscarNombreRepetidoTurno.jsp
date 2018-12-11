<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Turnos"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.TurnosControlador"%>
<%

    String nombre_turno = request.getParameter("nombre_turno");

    String tipo = "error";
 
    Turnos turno = new Turnos();
    turno.setNombre_turno(nombre_turno);
    TurnosControlador.buscarNombreRepetidoTurno(turno);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_turno", turno.getNombre_turno());
    System.out.println(turno.getNombre_turno());
    out.print(obj);
    out.flush();
%>