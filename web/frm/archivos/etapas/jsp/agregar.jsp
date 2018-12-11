
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.EtapasControlador"%>
<%@page import="Modelos.Etapas"%>
<%
    int id_etapa= Integer.parseInt(request.getParameter("id_etapa"));
    String nombre_etapa= request.getParameter("nombre_etapa");
    

String tipo ="error";
String mensaje="Datos no agregados";
Etapas etapa =new Etapas();
etapa.setId_etapa(id_etapa);
etapa.setNombre_etapa(nombre_etapa);
if (EtapasControlador.agregar(etapa)){
    tipo="success";
    mensaje="Datos agregados";         
}
JSONObject obj=new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", mensaje);
out.print(obj);
out.flush();
%>