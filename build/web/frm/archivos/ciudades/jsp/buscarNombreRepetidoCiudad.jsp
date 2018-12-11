<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Ciudades"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.CiudadesControlador"%>
<%

    String nombre_ciudad = request.getParameter("nombre_ciudad");

    String tipo = "error";
 
    Ciudades ciudad = new Ciudades();
    ciudad.setNombre_ciudad(nombre_ciudad);
    CiudadesControlador.buscarNombreRepetidoCiudad(ciudad);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_ciudad", ciudad.getNombre_ciudad());
    System.out.println(ciudad.getNombre_ciudad());
    out.print(obj);
    out.flush();
%>