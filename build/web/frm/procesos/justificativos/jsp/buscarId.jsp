<%@page import="Modelos.Justificativos"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.DetallesJustificativosControlador"%>
<%@page import="Controladores.JustificativosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_justificativo = Integer.parseInt(request.getParameter("id_justificativo"));
     //String sfecha_justificativo = request.getParameter("fecha_justificativo");
    //java.sql.Date fecha_justificativo = Utiles.stringToSqlDate(sfecha_justificativo);
    String motivo_justificativo = request.getParameter("motivo_justificativo");
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Justificativos justificativo = JustificativosControlador.buscarId(id_justificativo);
    if (justificativo != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        justificativo = new Justificativos();
        justificativo.setId_justificativo(0);
        justificativo.setMotivo_justificativo(motivo_justificativo);
        }
    
    String contenido_detalle = DetallesJustificativosControlador.buscarIdJustificativo(id_justificativo);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_justificativo", String.valueOf(justificativo.getId_justificativo()));
    obj.put("motivo_justificativo", justificativo.getMotivo_justificativo());
    obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>