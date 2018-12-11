<%@page import="Modelos.Tiposfaltas"%>
<%@page import="Modelos.Sanciones"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.SancionesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_sancion = Integer.parseInt(request.getParameter("id_sancion"));
    int id_tipofalta = Integer.parseInt(request.getParameter("id_tipofalta"));
    String obs_sancion = request.getParameter("obs_sancion");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Tiposfaltas tipofalta = new Tiposfaltas();
    tipofalta.setId_tipofalta(id_tipofalta);
    
    Sanciones sancion = new Sanciones();
    sancion.setId_sancion(id_sancion);
    sancion.setObs_sancion(obs_sancion);
    sancion.setTipofalta(tipofalta);
   
    if (SancionesControlador.modificar(sancion)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>