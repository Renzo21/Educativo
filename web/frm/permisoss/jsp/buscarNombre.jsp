
<%@page import="Controladores.PermisossControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_permisoss = request.getParameter("bnombre_permisoss");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    
    String mensaje = "Búsqueda exitosa.";
    String contenido = PermisossControlador.buscarNombre(nombre_permisoss, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>