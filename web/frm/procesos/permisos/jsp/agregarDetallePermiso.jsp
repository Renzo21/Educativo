<%@page import="Modelos.Permisos"%>
<%@page import="utiles.Utiles"%>
<%@page import="Modelos.Inscripciones"%>
<%@page import="Modelos.DetallesPermisos"%>
<%@page import="Controladores.DetallesPermisosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    //int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    //int id_detallepermisos = Integer.parseInt(request.getParameter("id_detallepermisos"));
    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
    int id_permiso = Integer.parseInt(request.getParameter("id_permiso")); 
    String descripcion_permiso = request.getParameter("descripcion_permiso");
    String sfecha_permiso = request.getParameter("fecha_permiso");
    java.sql.Date fecha_permiso = Utiles.stringToSqlDate(sfecha_permiso);

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    DetallesPermisos detallepermiso = new DetallesPermisos();
    detallepermiso.setDescripcion_permiso(descripcion_permiso);
    detallepermiso.setFecha_permiso(fecha_permiso);
    
    Permisos permiso = new Permisos();
    permiso.setId_permiso(id_permiso);
    detallepermiso.setPermiso(permiso);
    
    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    detallepermiso.setInscripcion(inscripcion);
      
    if (DetallesPermisosControlador.agregar(detallepermiso)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>