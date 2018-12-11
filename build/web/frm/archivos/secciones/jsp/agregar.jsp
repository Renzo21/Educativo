
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.SeccionesControlador"%>
<%@page import="Modelos.Secciones"%>
<%
    int id_seccion= Integer.parseInt(request.getParameter("id_seccion"));
    String nombre_seccion= request.getParameter("nombre_seccion");
    

String tipo ="error";
String mensaje="Datos no agregados";
Secciones seccion =new Secciones();
seccion.setId_seccion(id_seccion);
seccion.setNombre_seccion(nombre_seccion);
if (SeccionesControlador.agregar(seccion)){
    tipo="success";
    mensaje="Datos agregados";         
}
JSONObject obj=new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", mensaje);
out.print(obj);
out.flush();
%>