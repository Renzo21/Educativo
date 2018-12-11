
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.TiposfaltasControlador"%>
<%@page import="Modelos.Tiposfaltas"%>
<%
    int id_tipofalta= Integer.parseInt(request.getParameter("id_tipofalta"));
    String descripcion_tipofalta= request.getParameter("descripcion_tipofalta");
    

String tipo ="error";
String mensaje="Datos no agregados";
Tiposfaltas tipofalta = new Tiposfaltas();
tipofalta.setId_tipofalta(id_tipofalta);
tipofalta.setDescripcion_tipofalta(descripcion_tipofalta);
if (TiposfaltasControlador.agregar(tipofalta)){
    tipo="success";
    mensaje="Datos agregados";         
}
JSONObject obj=new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", mensaje);
out.print(obj);
out.flush();
%>