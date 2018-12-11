
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.FrecuenciasControlador"%>
<%@page import="Modelos.Frecuencias"%>
<%
    int id_frecuencia= Integer.parseInt(request.getParameter("id_frecuencia"));
    String nombre_frecuencia= request.getParameter("nombre_frecuencia");
    

String tipo ="error";
String mensaje="Datos no agregados";
Frecuencias frecuencia =new Frecuencias();
frecuencia.setId_frecuencia(id_frecuencia);
frecuencia.setNombre_frecuencia(nombre_frecuencia);
if (FrecuenciasControlador.agregar(frecuencia)){
    tipo="success";
    mensaje="Datos agregados";         
}
JSONObject obj=new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", mensaje);
out.print(obj);
out.flush();
%>