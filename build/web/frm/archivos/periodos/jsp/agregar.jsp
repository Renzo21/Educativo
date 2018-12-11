
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.PeriodosControlador"%>
<%@page import="Modelos.Periodos"%>
<%
    int id_periodo= Integer.parseInt(request.getParameter("id_periodo"));
    String nombre_periodo= request.getParameter("nombre_periodo");
    

String tipo ="error";
String mensaje="Datos no agregados";
Periodos periodo =new Periodos();
periodo.setId_periodo(id_periodo);
periodo.setNombre_periodo(nombre_periodo);
if (PeriodosControlador.agregar(periodo)){
    tipo="success";
    mensaje="Datos agregados";         
}
JSONObject obj=new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", mensaje);
out.print(obj);
out.flush();
%>