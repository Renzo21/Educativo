
<%@page import="Modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.ResponsablesControlador"%>
<%@page import="Modelos.Responsables"%>
<%
    int id_responsable= Integer.parseInt(request.getParameter("id_responsable"));
    String nombre_responsable= request.getParameter("nombre_responsable");
    String apellido_responsable= request.getParameter("apellido_responsable");
    String ci_responsable= request.getParameter("ci_responsable");
    String direccion_responsable= request.getParameter("direccion_responsable");
    String telefono_responsable= request.getParameter("telefono_responsable");
    int id_ciudad= Integer.parseInt(request.getParameter("id_ciudad"));
    

String tipo ="error";
String mensaje="Datos no agregados";
Responsables responsable =new Responsables();
responsable.setId_responsable(id_responsable);
responsable.setNombre_responsable(nombre_responsable);
responsable.setApellido_responsable(apellido_responsable);
responsable.setCi_responsable(ci_responsable);
responsable.setDireccion_responsable(direccion_responsable);
responsable.setTelefono_responsable(telefono_responsable);
Ciudades ciudad = new Ciudades();
ciudad.setId_ciudad(id_ciudad);
responsable.setCiudad(ciudad);
if (ResponsablesControlador.agregar(responsable)){
    tipo="success";
    mensaje="Datos agregados";         
}
JSONObject obj=new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", mensaje);
out.print(obj);
out.flush();
%>