<%@page import="Controladores.PeriodosControlador" %>
<%@page import="Modelos.Periodos" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_periodo = Integer.parseInt(request.getParameter("id_periodo"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Periodos periodo = new Periodos();
    periodo.setId_periodo(id_periodo);
    
    if(PeriodosControlador.eliminar(periodo)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>