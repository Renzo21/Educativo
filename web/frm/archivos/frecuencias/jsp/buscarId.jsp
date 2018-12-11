<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Frecuencias"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.FrecuenciasControlador"%>
<%

    int id_frecuencia = Integer.parseInt(request.getParameter("id_frecuencia"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Frecuencias frecuencia = new Frecuencias();
    frecuencia.setId_frecuencia(id_frecuencia);
    FrecuenciasControlador.buscarId(frecuencia);

    if (frecuencia.getId_frecuencia() != 0) {

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
    obj.put("id_frecuencia", frecuencia.getId_frecuencia());
    obj.put("nombre_frecuencia", frecuencia.getNombre_frecuencia());
    out.print(obj);
    out.flush();
%>