<%@page import="Modelos.Mallas"%>
<%@page import="Modelos.Asignaturas"%>
<%@page import="Modelos.DetallesMallas"%>
<%@page import="Controladores.DetallesMallasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallemalla = Integer.parseInt(request.getParameter("id_detallemalla"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesMallas detallemalla = DetallesMallasControlador.buscarId(id_detallemalla);
    if (detallemalla != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallemalla = new DetallesMallas();
        detallemalla.setId_detallemalla(0);
        
        Mallas malla = new Mallas();
        detallemalla.setMalla(malla);
        
        Asignaturas asignatura = new Asignaturas();
        detallemalla.setAsignatura(asignatura);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_malla", String.valueOf(detallemalla.getMalla().getId_malla()));
    obj.put("id_asignatura", String.valueOf(detallemalla.getAsignatura().getId_asignatura()));
    obj.put("nombre_asignatura", detallemalla.getAsignatura().getNombre_asignatura());
    obj.put("cargahoraria_total", detallemalla.getCargahoraria_total());
    
    out.print(obj);
    out.flush();
%>