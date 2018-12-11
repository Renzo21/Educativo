
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.GradosControlador"%>
<%@page import="Modelos.Grados"%>
<%
    int id_grado= Integer.parseInt(request.getParameter("id_grado"));
    String nombre_grado= request.getParameter("nombre_grado");
    

String tipo ="error";
String mensaje="Datos no agregados";
Grados grado =new Grados();
grado.setId_grado(id_grado);
grado.setNombre_grado(nombre_grado);
if (GradosControlador.agregar(grado)){
    tipo="success";
    mensaje="Datos agregados";         
}
JSONObject obj=new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", mensaje);
out.print(obj);
out.flush();
%>