<%@page import="Controladores.PermisossControlador"%>
<%@page import="Modelos.Permisoss"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
     int id_permisoss = Integer.parseInt(request.getParameter("id_permisoss"));
     
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Permisoss permisoss = new Permisoss();
    permisoss.setId_permisoss(id_permisoss);
    
    if (PermisossControlador.eliminar(permisoss)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>