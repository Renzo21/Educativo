<%@page import="Controladores.SedesControlador" %>
<%@page import="Modelos.Sedes" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import="java.sql.ResultSet" %>
<%
    int id_sede = Integer.parseInt(request.getParameter("id_sede"));
    String nombre_sede = request.getParameter("nombre_sede");
    String direccion_sede = request.getParameter("direccion_sede");
    String telefono_sede = request.getParameter("telefono_sede");
    String email_sede = request.getParameter("email_sede");
    String estado_sede = request.getParameter("estado_sede");
    
    String tipo="error";
    String mensaje="Datos no modificados";
    
    Sedes sede = new Sedes();
    sede.setId_sede(id_sede);
    sede.setNombre_sede(nombre_sede);
    sede.setDireccion_sede(direccion_sede);
    sede.setTelefono_sede(telefono_sede);
    sede.setEmail_sede(email_sede);
    sede.setEstado_sede(estado_sede);
    
    if(SedesControlador.modificar(sede)){
        tipo = "succes";
        mensaje = "Datos Modificados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    out.print(obj);
    out.flush();
%>