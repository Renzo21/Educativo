<%@page import="Controladores.AlumnosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_alumno = request.getParameter("bnombre_alumno");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
   
    String mensaje = "B�squeda exitosa.";
    String contenido = AlumnosControlador.buscarNombre(nombre_alumno, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>