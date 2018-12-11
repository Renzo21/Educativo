<%@page import="Controladores.UnidadesControlador" %>
<%@page import="Modelos.Unidades" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_unidad = Integer.parseInt(request.getParameter("id_unidad"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Unidades unidad = new Unidades();
    unidad.setId_unidad(id_unidad);
    
    if(UnidadesControlador.eliminar(unidad)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>