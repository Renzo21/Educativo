<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Etapas"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controladores.EtapasControlador"%>
<%

    String nombre_etapa = request.getParameter("nombre_etapa");

    String tipo = "error";
 
    Etapas etapa = new Etapas();
    etapa.setNombre_etapa(nombre_etapa);
    EtapasControlador.buscarNombreRepetidoEtapa(etapa);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
  
    obj.put("nombre_etapa", etapa.getNombre_etapa());
    System.out.println(etapa.getNombre_etapa());
    out.print(obj);
    out.flush();
%>