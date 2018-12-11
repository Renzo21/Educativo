<%@page import ="Controladores.TurnosControlador" %>
<%@page import ="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>

<%
    String nombre_turno = request.getParameter("bnombre_turno");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Busqueda Exitosa";
    String contenido = TurnosControlador.buscarNombre(nombre_turno, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>