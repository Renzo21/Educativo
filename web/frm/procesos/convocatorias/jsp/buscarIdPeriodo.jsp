<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Periodos"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.PeriodosControlador"%>
<%

    int id_periodo = Integer.parseInt(request.getParameter("id_periodo"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Periodos periodo = new Periodos();
    periodo.setId_periodo(id_periodo);
    PeriodosControlador.buscarId(periodo);

    if (periodo.getId_periodo() != 0) {

        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_periodo", periodo.getId_periodo());
    obj.put("nombre_periodo", periodo.getNombre_periodo());
    out.print(obj);
    out.flush();
%>