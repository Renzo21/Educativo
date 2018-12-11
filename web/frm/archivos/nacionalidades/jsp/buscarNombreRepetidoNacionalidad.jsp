<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Nacionalidades"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.NacionalidadesControlador"%>
<%

    String nombre_nacionalidad = request.getParameter("nombre_nacionalidad");

    String tipo = "error";
 
    Nacionalidades nacionalidad = new Nacionalidades();
    nacionalidad.setNombre_nacionalidad(nombre_nacionalidad);
    NacionalidadesControlador.buscarNombreRepetidoNacionalidad(nacionalidad);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_nacionalidad", nacionalidad.getNombre_nacionalidad());
    System.out.println(nacionalidad.getNombre_nacionalidad());
    out.print(obj);
    out.flush();
%>