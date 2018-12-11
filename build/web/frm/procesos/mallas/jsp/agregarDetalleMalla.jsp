<%@page import="Modelos.DetallesMallas"%>
<%@page import="Modelos.Mallas"%>
<%@page import="Modelos.Asignaturas"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.DetallesMallasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_asignatura = Integer.parseInt(request.getParameter("id_asignatura"));
    int id_malla = Integer.parseInt(request.getParameter("id_malla")); 
    String cargahoraria_total = request.getParameter("cargahoraria_total");

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    DetallesMallas detallemalla = new DetallesMallas();
    detallemalla.setCargahoraria_total(cargahoraria_total);
    
    Mallas malla = new Mallas();
    malla.setId_malla(id_malla);
    detallemalla.setMalla(malla);
    
    Asignaturas asignatura = new Asignaturas();
    asignatura.setId_asignatura(id_asignatura);
    detallemalla.setAsignatura(asignatura);
      
    if (DetallesMallasControlador.agregar(detallemalla)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>