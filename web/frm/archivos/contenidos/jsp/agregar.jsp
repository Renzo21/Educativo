
<%@page import="Modelos.Unidades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.ContenidosControlador"%>
<%@page import="Modelos.Contenidos"%>
<%
    int id_contenido= Integer.parseInt(request.getParameter("id_contenido"));
    int id_unidad= Integer.parseInt(request.getParameter("id_unidad"));
    String descripcion_contenido= request.getParameter("descripcion_contenido");
    

String tipo ="error";
String mensaje="Datos no agregados";

Unidades unidad = new Unidades();
unidad.setId_unidad(id_unidad);

Contenidos contenido =new Contenidos();
contenido.setId_contenido(id_contenido);
contenido.setDescripcion_contenido(descripcion_contenido);
contenido.setUnidad(unidad);
if (ContenidosControlador.agregar(contenido)){
    tipo="success";
    mensaje="Datos agregados";         
}
JSONObject obj=new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", mensaje);
out.print(obj);
out.flush();
%>