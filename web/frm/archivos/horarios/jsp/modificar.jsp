<%@page import="Controladores.HorariosControlador" %>
<%@page import="Modelos.Horarios" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import="java.sql.ResultSet" %>
<%
    int id_horario = Integer.parseInt(request.getParameter("id_horario"));
    String hora_inicio = request.getParameter("hora_inicio");
    String hora_fin = request.getParameter("hora_fin");
    
    String tipo="error";
    String mensaje="Datos no modificados";
    
    Horarios horario = new Horarios();
    horario.setId_horario(id_horario);
    horario.setHora_inicio(hora_inicio);
    horario.setHora_fin(hora_fin);
    
    if(HorariosControlador.modificar(horario)){
        tipo = "succes";
        mensaje = "Datos Modificados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>