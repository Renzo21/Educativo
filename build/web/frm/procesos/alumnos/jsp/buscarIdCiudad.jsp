<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Ciudades"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.CiudadesControlador"%>
<%

    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    CiudadesControlador.buscarId(ciudad);

    if (ciudad.getId_ciudad() != 0) {

        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        tipo = "success";
        mensaje = "Datos no encontrados";
        nuevo = "true";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_ciudad", ciudad.getId_ciudad());
    obj.put("nombre_ciudad", ciudad.getNombre_ciudad());
    out.print(obj);
    out.flush();
%>