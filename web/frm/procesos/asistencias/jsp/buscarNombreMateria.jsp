<%@page import="Controladores.AsignaturasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_asignatura = request.getParameter("bnombre_asignatura");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    int id = Integer.parseInt(request.getParameter("id_convocatoria"));
   
    String mensaje = "Búsqueda exitosa.";
    String contenido = AsignaturasControlador.buscarNombreMateria(nombre_asignatura, pagina, id);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    System.out.println(contenido);
    out.print(obj);
    out.flush();
%>