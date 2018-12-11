<%@page import="Modelos.Permisos"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.DetallesPermisosControlador"%>
<%@page import="Controladores.PermisosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_permiso = Integer.parseInt(request.getParameter("id_permiso"));
     //String sfecha_permiso = request.getParameter("fecha_permiso");
    //java.sql.Date fecha_permiso = Utiles.stringToSqlDate(sfecha_permiso);
    String motivo_permiso = request.getParameter("motivo_permiso");
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Permisos permiso = PermisosControlador.buscarId(id_permiso);
    if (permiso != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        permiso = new Permisos();
        permiso.setId_permiso(0);
        permiso.setMotivo_permiso(motivo_permiso);
        }
    
    String contenido_detalle = DetallesPermisosControlador.buscarIdPermiso(id_permiso);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_permiso", String.valueOf(permiso.getId_permiso()));
    obj.put("motivo_permiso", permiso.getMotivo_permiso());
    obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>