
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.EspecialidadesControlador"%>
<%@page import="Modelos.Especialidades"%>
<%
    int id_especialidad= Integer.parseInt(request.getParameter("id_especialidad"));
    String nombre_especialidad= request.getParameter("nombre_especialidad");
    

String tipo ="error";
String mensaje="Datos no agregados";
Especialidades especialidad =new Especialidades();
especialidad.setId_especialidad(id_especialidad);
especialidad.setNombre_especialidad(nombre_especialidad);
if (EspecialidadesControlador.agregar(especialidad)){
    tipo="success";
    mensaje="Datos agregados";         
}
JSONObject obj=new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", mensaje);
out.print(obj);
out.flush();
%>