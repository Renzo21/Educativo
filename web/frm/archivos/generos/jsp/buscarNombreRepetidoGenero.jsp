<%@page import="Controladores.GenerosControlador"%>
<%@page import="Modelos.Generos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Frecuencias"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.FrecuenciasControlador"%>
<%

    String nombre_genero = request.getParameter("nombre_genero");

    String tipo = "error";
 
    Generos genero = new Generos();
    genero.setNombre_genero(nombre_genero);
    GenerosControlador.buscarNombreRepetidoGenero(genero);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_genero", genero.getNombre_genero());
    System.out.println(genero.getNombre_genero());
    out.print(obj);
    out.flush();
%>