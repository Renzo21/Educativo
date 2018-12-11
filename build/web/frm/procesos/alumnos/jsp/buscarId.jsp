<%@page import="Modelos.Generos"%>
<%@page import="Modelos.Ciudades"%>
<%@page import="Modelos.Nacionalidades"%>
<%@page import="Modelos.Alumnos"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.DetallesAlumnosControlador"%>
<%@page import="Controladores.AlumnosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_alumno = Integer.parseInt(request.getParameter("id_alumno"));
     //String sfecha_pedido = request.getParameter("fecha_pedido");
    //java.sql.Date fecha_pedido = Utiles.stringToSqlDate(sfecha_pedido);
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Alumnos alumno = AlumnosControlador.buscarId(id_alumno);
    if (alumno != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        alumno = new Alumnos();
        alumno.setId_alumno(0);
  
        Nacionalidades nacionalidad = new Nacionalidades(); 
        alumno.setNacionalidad(nacionalidad);
        
        Ciudades ciudad = new Ciudades();
        alumno.setCiudad(ciudad);
        
        Generos genero = new Generos();
        alumno.setGenero(genero);
        }
    
    String contenido_detalle = DetallesAlumnosControlador.buscarIdAlumno(id_alumno);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_alumno", String.valueOf(alumno.getId_alumno()));
    obj.put("id_nacionalidad", String.valueOf(alumno.getNacionalidad().getId_nacionalidad()));
    obj.put("id_ciudad", String.valueOf(alumno.getCiudad().getId_ciudad()));
    obj.put("id_genero", String.valueOf(alumno.getGenero().getId_genero()));
    obj.put("nombre_alumno", alumno.getNombre_alumno());
    obj.put("apellido_alumno", alumno.getApellido_alumno());
    obj.put("ci_alumno", alumno.getCi_alumno());
    obj.put("telefono_alumno", alumno.getTelefono_alumno());
    obj.put("obs_alumno", alumno.getObs_alumno());
    obj.put("nombre_nacionalidad", alumno.getNacionalidad().getNombre_nacionalidad());
    obj.put("nombre_ciudad", alumno.getCiudad().getNombre_ciudad());
    obj.put("nombre_genero", alumno.getGenero().getNombre_genero());
    obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>