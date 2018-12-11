<%@page import="Modelos.Mallas"%>
<%@page import="Modelos.Especialidades"%>
<%@page import="Modelos.Grados"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.MallasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_malla = Integer.parseInt(request.getParameter("id_malla"));
    int id_grado = Integer.parseInt(request.getParameter("id_grado"));
    int id_especialidad = Integer.parseInt(request.getParameter("id_especialidad"));
    String modalidad_malla = request.getParameter("modalidad_malla");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Grados grado = new Grados();
    grado.setId_grado(id_grado);
    
    Especialidades especialidad = new Especialidades();
    especialidad.setId_especialidad(id_especialidad);
    
    Mallas malla = new Mallas();
    malla.setId_malla(id_malla);
    malla.setModalidad_malla(modalidad_malla);
    malla.setEspecialidad(especialidad);
    malla.setGrado(grado);
    
    if (MallasControlador.agregar(malla)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_malla", String.valueOf(malla.getId_malla()));
    out.print(obj);
    out.flush();
    
%>