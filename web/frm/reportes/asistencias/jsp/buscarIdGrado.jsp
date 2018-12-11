<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Grados"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.GradosControlador"%>
<%

    int id_grado = Integer.parseInt(request.getParameter("id_grado"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Grados grado = new Grados();
    grado.setId_grado(id_grado);
    GradosControlador.buscarId(grado);

    if (grado.getId_grado() != 0) {

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
    obj.put("id_grado", grado.getId_grado());
    obj.put("nombre_grado", grado.getNombre_grado());
    out.print(obj);
    out.flush();
%>