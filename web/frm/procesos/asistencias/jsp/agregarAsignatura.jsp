
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.AsignaturasControlador"%>
<%@page import="Modelos.Asignaturas"%>
<%
    int id_asignatura= Integer.parseInt(request.getParameter("id_asignatura"));
    String nombre_asignatura= request.getParameter("nombre_asignatura");
    

String tipo ="error";
String mensaje="Datos no agregados";
Asignaturas asignatura =new Asignaturas();
asignatura.setId_asignatura(id_asignatura);
asignatura.setNombre_asignatura(nombre_asignatura);
if (AsignaturasControlador.agregar(asignatura)){
    tipo="success";
    mensaje="Datos agregados";         
}
JSONObject obj=new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", mensaje);
out.print(obj);
out.flush();
%>