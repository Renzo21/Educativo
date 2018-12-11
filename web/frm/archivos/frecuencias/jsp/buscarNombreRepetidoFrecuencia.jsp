<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Frecuencias"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.FrecuenciasControlador"%>
<%

    String nombre_frecuencia = request.getParameter("nombre_frecuencia");

    String tipo = "error";
 
    Frecuencias frecuencia = new Frecuencias();
    frecuencia.setNombre_frecuencia(nombre_frecuencia);
    FrecuenciasControlador.buscarNombreRepetidoFrecuencia(frecuencia);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_frecuencia", frecuencia.getNombre_frecuencia());
    System.out.println(frecuencia.getNombre_frecuencia());
    out.print(obj);
    out.flush();
%>