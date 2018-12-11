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
    } else {
        tipo = "success";
        mensaje = "Datos no encontrados";
        nuevo = "true";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_profesor", profesor.getId_profesor());
    obj.put("nombre_profesor", profesor.getNombre_profesor());
    obj.put("apellido_profesor", profesor.getApellido_profesor());
    obj.put("ci_profesor", profesor.getCi_profesor());
    obj.put("direccion_profesor", profesor.getDireccion_profesor());
    obj.put("telefono_profesor", profesor.getTelefono_profesor());
    obj.put("id_ciudad", profesor.getCiudad().getId_ciudad());
    obj.put("nombre_ciudad", profesor.getCiudad().getNombre_ciudad());
    obj.put("id_nacionalidad", profesor.getNacionalidad().getId_nacionalidad());
    obj.put("nombre_nacionalidad", profesor.getNacionalidad().getNombre_nacionalidad());
    obj.put("genero_profesor", profesor.getGenero_profesor());
    out.print(obj);
    out.flush();
%>