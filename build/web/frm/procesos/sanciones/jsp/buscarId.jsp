<%@page import="Modelos.Tiposfaltas"%>
<%@page import="Modelos.Sanciones"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.DetallesSancionesControlador"%>
<%@page import="Controladores.SancionesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_sancion = Integer.parseInt(request.getParameter("id_sancion"));
     //String sfecha_permiso = request.getParameter("fecha_permiso");
    //java.sql.Date fecha_permiso = Utiles.stringToSqlDate(sfecha_permiso);
    String motivo_permiso = request.getParameter("motivo_permiso");
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Sanciones sancion = SancionesControlador.buscarId(id_sancion);
    if (sancion != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        
        Tiposfaltas tipofalta = new Tiposfaltas();
        sancion.setTipofalta(tipofalta);
        
        sancion = new Sanciones();
        sancion.setId_sancion(0);
        sancion.setObs_sancion("");
        }
    
    String contenido_detalle = DetallesSancionesControlador.buscarIdSancion(id_sancion);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_sancion", String.valueOf(sancion.getId_sancion()));
    obj.put("id_tipofalta", String.valueOf(sancion.getTipofalta().getId_tipofalta()));
    obj.put("obs_sancion", sancion.getObs_sancion());
    obj.put("descripcion_tipofalta", sancion.getTipofalta().getDescripcion_tipofalta());
    obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>