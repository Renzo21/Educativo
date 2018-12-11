<%@page import="Modelos.Tiposfaltas"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.SancionesControlador"%>
<%@page import="Modelos.Sanciones"%>
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
    
    if (SancionesControlador.agregar(sancion)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_sancion", String.valueOf(sancion.getId_sancion()));
    out.print(obj);
    out.flush();
    
%>