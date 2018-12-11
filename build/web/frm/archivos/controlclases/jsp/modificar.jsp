<%@page import="Controladores.ControlclasesControlador"%>
<%@page import="Modelos.Profesores"%>
<%@page import="Modelos.Contenidos"%>
<%@page import="Modelos.Especialidades"%>
<%@page import="Modelos.Grados"%>
<%@page import="Modelos.Controlclases"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.AsignaturasControlador" %>
<%@page import="Modelos.Asignaturas" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import="java.sql.ResultSet" %>
<%
   int id_controlclase= Integer.parseInt(request.getParameter("id_controlclase"));
    int id_contenido= Integer.parseInt(request.getParameter("id_contenido"));
    int id_grado= Integer.parseInt(request.getParameter("id_grado"));
    int id_especialidad= Integer.parseInt(request.getParameter("id_especialidad"));
    int id_profesor= Integer.parseInt(request.getParameter("id_profesor"));
    String progreso_clase= request.getParameter("progreso_clase");
    String sfecha_clase= request.getParameter("fecha_clase");
    java.sql.Date fecha_clase = Utiles.stringToSqlDate(sfecha_clase);
    

String tipo ="error";
String mensaje="Datos no agregados";
Controlclases controlclase = new Controlclases();
controlclase.setId_controlclase(id_controlclase);
controlclase.setFecha_clase(fecha_clase);
controlclase.setProgreso_clase(progreso_clase);

Grados grado = new Grados();
grado.setId_grado(id_grado);
controlclase.setGrado(grado);

Especialidades especialidad = new Especialidades();
especialidad.setId_especialidad(id_especialidad);
controlclase.setEspecialidad(especialidad);

Contenidos contenido = new Contenidos();
contenido.setId_contenido(id_contenido);
controlclase.setContenido(contenido);

Profesores profesor = new Profesores();
profesor.setId_profesor(id_profesor);
controlclase.setProfesor(profesor);
    
    if(ControlclasesControlador.modificar(controlclase)){
        tipo = "succes";
        mensaje = "Datos Modificados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>