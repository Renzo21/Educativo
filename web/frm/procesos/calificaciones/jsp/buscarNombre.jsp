<%@page import="utiles.Utiles"%>
<%@page import="Controladores.CalificacionesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String snombre_profesor = request.getParameter("bnombre_profesor");
    java.sql.Date nombre_profesor = Utiles.stringToSqlDate(snombre_profesor);
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
   
    String mensaje = "Búsqueda exitosa.";
    String contenido = CalificacionesControlador.buscarNombre(nombre_profesor, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>