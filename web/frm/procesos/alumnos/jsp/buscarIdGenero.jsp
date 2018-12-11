<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Generos"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.GenerosControlador"%>
<%

    int id_genero = Integer.parseInt(request.getParameter("id_genero"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Generos genero = new Generos();
    genero.setId_genero(id_genero);
    GenerosControlador.buscarId(genero);

    if (genero.getId_genero() != 0) {

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
    obj.put("id_genero", genero.getId_genero());
    obj.put("nombre_genero", genero.getNombre_genero());
    out.print(obj);
    out.flush();
%>