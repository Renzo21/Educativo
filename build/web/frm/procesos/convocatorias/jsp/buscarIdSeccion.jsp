<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Secciones"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.SeccionesControlador"%>
<%

    int id_seccion = Integer.parseInt(request.getParameter("id_seccion"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Secciones seccion = new Secciones();
    seccion.setId_seccion(id_seccion);
    SeccionesControlador.buscarId(seccion);

    if (seccion.getId_seccion() != 0) {

        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_seccion", seccion.getId_seccion());
    obj.put("nombre_seccion", seccion.getNombre_seccion());
    out.print(obj);
    out.flush();
%>