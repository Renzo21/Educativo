<%@page import="Controladores.HorariosControlador" %>
<%@page import="Modelos.Horarios" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import ="java.sql.ResultSet" %>
<%
    int id_horario = Integer.parseInt(request.getParameter("id_horario"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados";
    
    Horarios horario = new Horarios();
    horario.setId_horario(id_horario);
    
    if(HorariosControlador.eliminar(horario)){
        tipo = "succes";
        mensaje = "Datos Eliminados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>