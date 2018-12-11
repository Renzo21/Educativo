<%@page import="java.util.StringTokenizer"%>
<%@page import="Modelos.Asistencias"%>
<%@page import="Modelos.Inscripciones"%>
<%@page import="Modelos.DetallesCalificaciones"%>
<%@page import="Controladores.DetallesCalificacionesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    //int id_detallecalificacion = Integer.parseInt(request.getParameter("id_detallecalificacion"));

    String detalle = request.getParameter("detalle");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    String registro = "";
    String campo = "";
    System.out.println("detalles --->" + detalle);

    StringTokenizer tokens = new StringTokenizer(detalle, ";");
    while (tokens.hasMoreTokens()) {
        registro = tokens.nextToken();
        System.out.println("registro--->" + registro);

        StringTokenizer tokens2 = new StringTokenizer(registro, ":");
        int contador = 0;
        int id_detallecalificacion = 0;
        int nota_final = 0;
        while (tokens2.hasMoreTokens()) {
            campo = tokens2.nextToken();
            System.out.println(" campo--->" + campo);
            if (contador == 0) {
                id_detallecalificacion = Integer.parseInt(campo);
            } else {
                nota_final = Integer.parseInt(campo);
            }
            contador++;
        }
        DetallesCalificaciones detallecalificacion = new DetallesCalificaciones();
        detallecalificacion.setId_detallecalificacion(id_detallecalificacion);
        detallecalificacion.setNota_final(nota_final);

        if (DetallesCalificacionesControlador.modificar(detallecalificacion)) {
            tipo = "success";
            mensaje = "Datos modificados.";
        }
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>