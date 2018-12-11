<%@page import="Modelos.Asignaturas"%>
<%@page import="Modelos.Mallas"%>
<%@page import="Modelos.DetallesMallas"%>
<%@page import="utiles.Utiles"%>
<%@page import="Modelos.DetallesSanciones"%>
<%@page import="Controladores.DetallesMallasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_detallemalla = Integer.parseInt(request.getParameter("id_detallemalla"));
    int id_asignatura = Integer.parseInt(request.getParameter("id_asignatura"));
    int id_malla = Integer.parseInt(request.getParameter("id_malla")); 
    String cargahoraria_total = request.getParameter("cargahoraria_total");

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    DetallesMallas detallemalla = new DetallesMallas();
    detallemalla.setId_detallemalla(id_detallemalla);
    detallemalla.setCargahoraria_total(cargahoraria_total);
    
    Mallas malla = new Mallas();
    malla.setId_malla(id_malla);
    detallemalla.setMalla(malla);
    
    Asignaturas asignatura = new Asignaturas();
    asignatura.setId_asignatura(id_asignatura);
    detallemalla.setAsignatura(asignatura);
      
    if (DetallesMallasControlador.modificar(detallemalla)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>