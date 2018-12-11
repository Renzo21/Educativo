<%@page import="java.util.StringTokenizer"%>
<%@page import="Modelos.Asistencias"%>
<%@page import="Modelos.Inscripciones"%>
<%@page import="Modelos.DetallesAsistencias"%>
<%@page import="Controladores.DetallesAsistenciasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    //int id_detalleasistencias = Integer.parseInt(request.getParameter("id_detalleasistencias"));

    String detalle = request.getParameter("detalle");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    String registro = "";
    String campo = "";
    

    StringTokenizer tokens = new StringTokenizer(detalle, ";");
    while (tokens.hasMoreTokens()) {
        registro = tokens.nextToken();
        System.out.println("registro--->" + registro);

        StringTokenizer tokens2 = new StringTokenizer(registro, ":");
        int contador = 0;
        int id_detalleasistencias = 0;
        String estado_asistencia = "";
        while (tokens2.hasMoreTokens()) {
            campo = tokens2.nextToken();
            System.out.println(" campo--->" + campo);
            if (contador == 0) {
                id_detalleasistencias = Integer.parseInt(campo);
            } else {
                estado_asistencia = (campo);
            }
            contador++;
        }
        DetallesAsistencias detalleasistencia = new DetallesAsistencias();
        detalleasistencia.setId_detalleasistencias(id_detalleasistencias);
        detalleasistencia.setEstado_asistencia(estado_asistencia);

        if (DetallesAsistenciasControlador.modificar(detalleasistencia)) {
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