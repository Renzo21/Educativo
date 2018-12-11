<%@page import="Controladores.MenusControlador"%>
<%@page import="Modelos.Menus"%>
<%@page import="Controladores.FormulariosControlador"%>
<%@page import="Modelos.Formularios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Asignaturas"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.AsignaturasControlador"%>
<%

    String nombre_menu = request.getParameter("nombre_menu");

    String tipo = "error";
 
    Menus menu = new Menus();
    menu.setNombre_menu(nombre_menu);
    MenusControlador.buscarNombreRepetidoMenu(menu);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_menu", menu.getNombre_menu());
    System.out.println(menu.getNombre_menu());
    out.print(obj);
    out.flush();
%>