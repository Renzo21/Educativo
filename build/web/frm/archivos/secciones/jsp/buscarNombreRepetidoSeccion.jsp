<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Secciones"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.SeccionesControlador"%>
<%

    String nombre_seccion = request.getParameter("nombre_seccion");

    String tipo = "error";
 
    Secciones seccion = new Secciones();
    seccion.setNombre_seccion(nombre_seccion);
    SeccionesControlador.buscarNombreRepetidoSeccion(seccion);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_seccion", seccion.getNombre_seccion());
    System.out.println(seccion.getNombre_seccion());
    out.print(obj);
    out.flush();
%>