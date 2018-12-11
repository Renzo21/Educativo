<%@page import="Controladores.CiudadesControlador" %>
<%@page import="Modelos.Ciudades" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    
    if(CiudadesControlador.eliminar(ciudad)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>