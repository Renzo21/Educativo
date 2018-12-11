<%@page import="Modelos.Generos"%>
<%@page import="Modelos.Ciudades"%>
<%@page import="Modelos.Nacionalidades"%>
<%@page import="utiles.Utiles"%>
<%@page import="Controladores.AlumnosControlador"%>
<%@page import="Modelos.Alumnos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_alumno = Integer.parseInt(request.getParameter("id_alumno"));
    int id_nacionalidad = Integer.parseInt(request.getParameter("id_nacionalidad")); 
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad")); 
    int id_genero = Integer.parseInt(request.getParameter("id_genero")); 
    String nombre_alumno = request.getParameter("nombre_alumno");
    String apellido_alumno = request.getParameter("apellido_alumno");
    String ci_alumno = request.getParameter("ci_alumno");
    String telefono_alumno = request.getParameter("telefono_alumno");
    String obs_alumno = request.getParameter("obs_alumno");
    //String sfecha_pedido = request.getParameter("fecha_pedido");
    //java.sql.Date fecha_pedido = Utiles.stringToSqlDate(sfecha_pedido);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Nacionalidades nacionalidad = new Nacionalidades();
    nacionalidad.setId_nacionalidad(id_nacionalidad);
    
    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    
    Generos genero = new Generos();
    genero.setId_genero(id_genero);
    
    Alumnos alumno = new Alumnos();
    alumno.setId_alumno(id_alumno);
    alumno.setNombre_alumno(nombre_alumno);
    alumno.setApellido_alumno(apellido_alumno);
    alumno.setCi_alumno(ci_alumno);
    alumno.setGenero(genero);
    alumno.setTelefono_alumno(telefono_alumno);
    alumno.setObs_alumno(obs_alumno);
    alumno.setNacionalidad(nacionalidad);
    alumno.setCiudad(ciudad);
      
    
    if (AlumnosControlador.agregar(alumno)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_alumno", String.valueOf(alumno.getId_alumno()));
    out.print(obj);
    out.flush();
    
%>