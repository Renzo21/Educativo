<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Periodos"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.PeriodosControlador"%>
<%

    String nombre_periodo = request.getParameter("nombre_periodo");

    String tipo = "error";
 
    Periodos periodo = new Periodos();
    periodo.setNombre_periodo(nombre_periodo);
    PeriodosControlador.buscarNombreRepetidoPeriodo(periodo);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_periodo", periodo.getNombre_periodo());
    System.out.println(periodo.getNombre_periodo());
    out.print(obj);
    out.flush();
%>