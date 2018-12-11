<%@page import="Modelos.Especialidades"%>
<%@page import="Modelos.Turnos"%>
<%@page import="Modelos.Convocatorias"%>
<%@page import="Modelos.Periodos"%>
<%@page import="Modelos.Secciones"%>
<%@page import="Modelos.Grados"%>
<%@page import="Modelos.Sedes"%>
<%@page import="Controladores.ConvocatoriasControlador" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import="java.sql.ResultSet" %>
<%
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    int id_sede = Integer.parseInt(request.getParameter("id_sede"));
    int id_grado = Integer.parseInt(request.getParameter("id_grado"));
    int id_especialidad = Integer.parseInt(request.getParameter("id_especialidad"));
    int id_seccion = Integer.parseInt(request.getParameter("id_seccion"));
    int id_turno = Integer.parseInt(request.getParameter("id_turno"));
    int id_periodo = Integer.parseInt(request.getParameter("id_periodo"));
    int cupo_convocatoria = Integer.parseInt(request.getParameter("cupo_convocatoria")); 
    String estado_convocatoria = request.getParameter("estado_convocatoria"); 

    
    //String sfecha_pedido = request.getParameter("fecha_pedido");
    //java.sql.Date fecha_pedido = Utiles.stringToSqlDate(sfecha_pedido);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Sedes sede = new Sedes();
    sede.setId_sede(id_sede);
    Grados grado = new Grados();
    grado.setId_grado(id_grado);
    Especialidades especialidad = new Especialidades();
    especialidad.setId_especialidad(id_especialidad);
    Secciones seccion = new Secciones();
    seccion.setId_seccion(id_seccion);
    Turnos turno = new Turnos();
    turno.setId_turno(id_turno);
    Periodos periodo = new Periodos();
    periodo.setId_periodo(id_periodo);
    
    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);
    convocatoria.setSede(sede);
    convocatoria.setGrado(grado);
    convocatoria.setEspecialidad(especialidad);
    convocatoria.setSeccion(seccion);
    convocatoria.setTurno(turno);
    convocatoria.setPeriodo(periodo);
    convocatoria.setCupo_convocatoria(cupo_convocatoria);
    convocatoria.setEstado_convocatoria(estado_convocatoria);
    
    if(ConvocatoriasControlador.modificar(convocatoria)){
        tipo = "succes";
        mensaje = "Datos Modificados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>