
<%@page import="Modelos.Generos"%>
<%@page import="Modelos.Nacionalidades"%>
<%@page import="Modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.ProfesoresControlador"%>
<%@page import="Modelos.Profesores"%>
<%
    int id_profesor= Integer.parseInt(request.getParameter("id_profesor"));
    String nombre_profesor= request.getParameter("nombre_profesor");
    String apellido_profesor= request.getParameter("apellido_profesor");
    String ci_profesor= request.getParameter("ci_profesor");
    String direccion_profesor= request.getParameter("direccion_profesor");
    String telefono_profesor= request.getParameter("telefono_profesor");
    int id_ciudad= Integer.parseInt(request.getParameter("id_ciudad"));
    int id_nacionalidad= Integer.parseInt(request.getParameter("id_nacionalidad"));
    int id_genero= Integer.parseInt(request.getParameter("id_genero"));
    

String tipo ="error";
String mensaje="Datos no agregados";
Profesores profesor =new Profesores();
profesor.setId_profesor(id_profesor);
profesor.setNombre_profesor(nombre_profesor);
profesor.setApellido_profesor(apellido_profesor);
profesor.setCi_profesor(ci_profesor);
profesor.setDireccion_profesor(direccion_profesor);
profesor.setTelefono_profesor(telefono_profesor);

Ciudades ciudad = new Ciudades();
ciudad.setId_ciudad(id_ciudad);

Nacionalidades nacionalidad = new Nacionalidades();
nacionalidad.setId_nacionalidad(id_nacionalidad);

Generos genero = new Generos();
genero.setId_genero(id_genero);

profesor.setCiudad(ciudad);
profesor.setNacionalidad(nacionalidad);
profesor.setGenero(genero);

if (ProfesoresControlador.agregar(profesor)){
    tipo="success";
    mensaje="Datos agregados";         
}
JSONObject obj=new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", mensaje);
out.print(obj);
out.flush();
%>