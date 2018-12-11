<%@page import="Modelos.Permisos"%>
<%@page import="Modelos.Profesores"%>
<%@page import="Modelos.Asignaturas"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.PermisosControlador"%>
<%@page import="Modelos.Asistencias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_permiso = Integer.parseInt(request.getParameter("id_permiso"));
    String motivo_permiso = request.getParameter("motivo_permiso");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Permisos permiso = new Permisos();
    permiso.setId_permiso(id_permiso);
    permiso.setMotivo_permiso(motivo_permiso);
    
    if (PermisosControlador.agregar(permiso)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_permiso", String.valueOf(permiso.getId_permiso()));
    out.print(obj);
    out.flush();
    
%>