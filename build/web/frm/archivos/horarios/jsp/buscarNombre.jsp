<%@page import ="Controladores.HorariosControlador" %>
<%@page import ="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>

<%
    String nombre_horario = request.getParameter("bnombre_horario");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Busqueda Exitosa";
    String contenido = HorariosControlador.buscarNombre(nombre_horario, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>