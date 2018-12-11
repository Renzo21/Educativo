<%@page import="Modelos.Especialidades"%>
<%@page import="Modelos.Grados"%>
<%@page import="Modelos.Mallas"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.DetallesMallasControlador"%>
<%@page import="Controladores.MallasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_malla = Integer.parseInt(request.getParameter("id_malla"));
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Mallas malla = MallasControlador.buscarId(id_malla);
    if (malla != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        
        Grados grado = new Grados();
        malla.setGrado(grado);
        
        Especialidades especialidad = new Especialidades();
        malla.setEspecialidad(especialidad);

        malla = new Mallas();
        malla.setId_malla(0);
        malla.setModalidad_malla("");
        }
    
    String contenido_detalle = DetallesMallasControlador.buscarIdMalla(id_malla);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_malla", String.valueOf(malla.getId_malla()));
    obj.put("id_grado", String.valueOf(malla.getGrado().getId_grado()));
    obj.put("id_especialidad", String.valueOf(malla.getEspecialidad().getId_especialidad()));
    obj.put("nombre_grado", malla.getGrado().getNombre_grado());
    obj.put("nombre_especialidad", malla.getEspecialidad().getNombre_especialidad());
    obj.put("modalidad_malla", malla.getModalidad_malla());
    obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>