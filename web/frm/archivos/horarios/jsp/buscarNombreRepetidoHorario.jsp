<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Horarios"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.HorariosControlador"%>
<%

    String nombre_horario = request.getParameter("nombre_horario");

    String tipo = "error";
 
    Horarios horario = new Horarios();
    horario.setNombre_horario(nombre_horario);
    HorariosControlador.buscarNombreRepetidoHorario(horario);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_horario", horario.getNombre_horario());
    System.out.println(horario.getNombre_horario());
    out.print(obj);
    out.flush();
%>