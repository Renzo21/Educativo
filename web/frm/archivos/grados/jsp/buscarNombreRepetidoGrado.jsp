<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Grados"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.GradosControlador"%>
<%

    String nombre_grado = request.getParameter("nombre_grado");

    String tipo = "error";
 
    Grados grado = new Grados();
    grado.setNombre_grado(nombre_grado);
    GradosControlador.buscarNombreRepetidoGrado(grado);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_grado", grado.getNombre_grado());
    System.out.println(grado.getNombre_grado());
    out.print(obj);
    out.flush();
%>