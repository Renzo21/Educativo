<%@page import="Controladores.GradosControlador" %>
<%@page import="Modelos.Grados" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_grado = Integer.parseInt(request.getParameter("id_grado"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Grados grado = new Grados();
    grado.setId_grado(id_grado);
    
    if(GradosControlador.eliminar(grado)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>