<%@page import ="Controladores.UnidadesControlador" %>
<%@page import ="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>

<%
    String nombre_unidad = request.getParameter("bnombre_unidad");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Busqueda Exitosa";
    String contenido = UnidadesControlador.buscarNombre(nombre_unidad, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>