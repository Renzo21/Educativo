<%@page import ="Controladores.GradosControlador" %>
<%@page import ="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>

<%
    String nombre_grado = request.getParameter("bnombre_grado");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Busqueda Exitosa";
    String contenido = GradosControlador.buscarNombre(nombre_grado, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>