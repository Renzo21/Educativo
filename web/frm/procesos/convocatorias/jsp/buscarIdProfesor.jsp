<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Profesores"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.ProfesoresControlador"%>
<%

    int id_profesor = Integer.parseInt(request.getParameter("id_profesor"));

    String tipo = "error";
    String mensaje = "Error";
    String nuevo = "true";
    Profesores profesor = new Profesores();
    profesor.setId_profesor(id_profesor);
    ProfesoresControlador.buscarId(profesor);

    if (profesor.getId_profesor() != 0) {

        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_profesor", profesor.getId_profesor());
    obj.put("nombre_profesor", profesor.getNombre_profesor());
    obj.put("apellido_profesor", profesor.getApellido_profesor());
    out.print(obj);
    out.flush();
%>