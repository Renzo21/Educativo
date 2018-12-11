<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Especialidades"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.EspecialidadesControlador"%>
<%

    int id_especialidad = Integer.parseInt(request.getParameter("id_especialidad"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Especialidades especialidad = new Especialidades();
    especialidad.setId_especialidad(id_especialidad);
    EspecialidadesControlador.buscarId(especialidad);

    if (especialidad.getId_especialidad() != 0) {

        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_especialidad", especialidad.getId_especialidad());
    obj.put("nombre_especialidad", especialidad.getNombre_especialidad());
    out.print(obj);
    out.flush();
%>