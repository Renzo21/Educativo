<%@page import="Modelos.Periodos"%>
<%@page import="Modelos.Turnos"%>
<%@page import="Modelos.Secciones"%>
<%@page import="Modelos.Especialidades"%>
<%@page import="Modelos.Grados"%>
<%@page import="Modelos.Sedes"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.DetallesConvocatoriasControlador"%>
<%@page import="Controladores.ConvocatoriasControlador"%>
<%@page import="Modelos.Convocatorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
     //String sfecha_pedido = request.getParameter("fecha_pedido");
    //java.sql.Date fecha_pedido = Utiles.stringToSqlDate(sfecha_pedido);
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Convocatorias convocatorias = ConvocatoriasControlador.buscarId(id_convocatoria);
    if (convocatorias != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        Convocatorias convocatoria = new Convocatorias();
        convocatoria.setId_convocatoria(0);
  
        Sedes sede = new Sedes();
        Grados grado = new Grados();
        Especialidades especialidad = new Especialidades();
        Secciones seccion = new Secciones();
        Turnos turno = new Turnos();
        Periodos periodo = new Periodos();
        convocatoria.setSede(sede);
        convocatoria.setGrado(grado);
        convocatoria.setEspecialidad(especialidad);
        convocatoria.setSeccion(seccion);
        convocatoria.setTurno(turno);
        convocatoria.setPeriodo(periodo);
        }
    
    String contenido_detalle = DetallesConvocatoriasControlador.buscarIdConvocatoria(id_convocatoria);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_convocatoria", String.valueOf(convocatorias.getId_convocatoria()));
    obj.put("cupo_convocatoria", String.valueOf(convocatorias.getCupo_convocatoria()));
    obj.put("estado_convocatoria",convocatorias.getEstado_convocatoria());
    obj.put("id_sede", String.valueOf(convocatorias.getSede().getId_sede()));
    obj.put("id_grado", String.valueOf(convocatorias.getGrado().getId_grado()));
    obj.put("id_especialidad", String.valueOf(convocatorias.getEspecialidad().getId_especialidad()));
    obj.put("id_seccion", String.valueOf(convocatorias.getSeccion().getId_seccion()));
    obj.put("id_turno", String.valueOf(convocatorias.getTurno().getId_turno()));
    obj.put("id_periodo", String.valueOf(convocatorias.getPeriodo().getId_periodo()));
    obj.put("nombre_sede", convocatorias.getSede().getNombre_sede());
    obj.put("nombre_grado", convocatorias.getGrado().getNombre_grado());
    obj.put("nombre_especialidad", convocatorias.getEspecialidad().getNombre_especialidad());
    obj.put("nombre_seccion", convocatorias.getSeccion().getNombre_seccion());
    obj.put("nombre_turno", convocatorias.getTurno().getNombre_turno());
    obj.put("nombre_periodo", convocatorias.getPeriodo().getNombre_periodo());
    obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>