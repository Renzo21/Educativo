<%@page import="Modelos.Permisos"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.PermisosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_permiso = Integer.parseInt(request.getParameter("id_permiso"));
    //String sfecha_asistencia = request.getParameter("fecha_asistencia");
    //java.sql.Date fecha_asistencia = Utiles.stringToSqlDate(sfecha_asistencia);
    String motivo_permiso = request.getParameter("motivo_permiso");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Permisos permiso = new Permisos();
    permiso.setId_permiso(id_permiso);
    permiso.setMotivo_permiso(motivo_permiso);
   
    if (PermisosControlador.modificar(permiso)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>