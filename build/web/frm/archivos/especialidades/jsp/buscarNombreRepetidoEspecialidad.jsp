<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Especialidades"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.EspecialidadesControlador"%>
<%

    String nombre_especialidad = request.getParameter("nombre_especialidad");

    String tipo = "error";
 
    Especialidades especialidad = new Especialidades();
    especialidad.setNombre_especialidad(nombre_especialidad);
    EspecialidadesControlador.buscarNombreRepetidoEspecialidad(especialidad);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_especialidad", especialidad.getNombre_especialidad());
    System.out.println(especialidad.getNombre_especialidad());
    out.print(obj);
    out.flush();
%>