<%@page import="Controladores.FormulariosControlador"%>
<%@page import="Modelos.Formularios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Asignaturas"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.AsignaturasControlador"%>
<%

    String nombre_formulario = request.getParameter("nombre_formulario");

    String tipo = "error";
 
    Formularios formulario = new Formularios();
    formulario.setNombre_formulario(nombre_formulario);
    FormulariosControlador.buscarNombreRepetidoFormulario(formulario);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_formulario", formulario.getNombre_formulario());
    System.out.println(formulario.getNombre_formulario());
    out.print(obj);
    out.flush();
%>