function fechaHoy() {
    alert("hoy");
    var hoy = new new Date().toJSON().slice(0, 10);




    $("#fecha_asistencia").val(hoy);


}
function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}

function buscarIdAsistencia() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_asistencia").val(json.id_asistencia);
            $("#id_convocatoria").val(json.id_convocatoria);
            $("#id_asignatura").val(json.id_asignatura);
            $("#nombre_grado").val(json.nombre_grado);
            $("#nombre_seccion").val(json.nombre_seccion);
            $("#nombre_turno").val(json.nombre_turno);
            $("#id_detalleconvocatoria").val(json.id_detalleconvocatoria);
            $("#nombre_asignatura").val(json.nombre_asignatura);
            $("#nombre_profesor").val(json.nombre_profesor);
            $("#apellido_profesor").val(json.apellido_profesor);
            $("#fecha_asistencia").val(json.fecha_asistencia);
            buscarIdDetalleAsistencia();

            var fecha = $("#fecha_asistencia").serialize();
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                //siguienteCampo("#id_tipopedido", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //siguienteCampo("#id_tipopedido", "#botonModificar", true);
                $("#detalle").prop('hidden', false);
            }

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreAsistencia() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_asistencia").val(id);
                $("#nombre_profesor").focus();
                buscarIdAsistencia();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function agregarAsistencia() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {


            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_asistencia").val(json.id_asistencia);
            buscarIdAsistencia();
            agregarDetalleAsistencia();
            // $("#id_pedido").focus;
            //$("#id_pedido").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarAsistencia() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_asistencia").focus;
            $("#id_asistencia").select();
            buscarIdAsistencia();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarAsistencia() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            eliminarDetalleAsistencia();
            $("#mensajes").html(json.mensaje);
            $("#id_asistencia").focus;
            $("#id_asistencia").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo eliminar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarIdConvocatoria() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdConvocatoria.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            
            $("#mensajes").html(json.mensaje);
            $("#id_convocatoria").val(json.id_convocatoria);
            //$("#fecha_pedido").val(json.fecha_pedido);
            //var fecha = $("#fecha_pedido").serialize();

            $("#id_grado").val(json.id_grado);
            $("#nombre_grado").val(json.nombre_grado);
            $("#id_seccion").val(json.id_seccion);
            $("#nombre_seccion").val(json.nombre_seccion);
            $("#id_turno").val(json.id_turno);
            $("#nombre_turno").val(json.nombre_turno);
            $("#contenidoDetalle").html(json.contenido_detalle);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreConvocatoria() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreConvocatoria.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");

                $("#id_convocatoria").val(id);
                $("#nombre_grado").focus();
                buscarIdConvocatoria();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}


function validarFormulario() {
    var valor = true;

    if ($("#nombre_grado").val().length < 2) {
        valor = false;
        $("#mensajes").html("Nombre Grado no puede estar vacio.");
        $("#id_convocatoria").focus();
    }

    if ($("#fecha_asistencia").val().length < 2) {
        valor = false;
        $("#mensajes").html("Fecha Asistencia no puede estar vacio.");
        $("#fecha_asistencia").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_asistencia").val("0");
    $("#nombre_asignatura").val("");
    $("#nombre_profesor").val("");
    $("#apellido_profesor").val("");
    $("#nombre_grado").val("");
    $("#nombre_seccion").val("");
    $("#nombre_turno").val("");
    $("#id_convocatoria").val("0");
    $("#id_detalleconvocatoria").val("0");
    $("#fecha_asistencia").val("");

}
function agregarLinea() {
    $("#id_detalleasistencias").val("0");
    $("#id_inscripcion").val("0");
    $("#nombre_alumno").val("");
    $("#apellido_alumno").val("");
    $("#nombre_grado").val("");
    $("#nombre_especialidad").val("");
    $("#nombre_seccion").val("");
    $("#nombre_turno").val("");
    $("#estado_asistencia").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_inscripcion").focus();
    $("#id_inscripcion").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    //siguienteCampo("#horas_detallepedido", "#botonAgregarLinea", true);
}
function editarLinea(Linea) {
   
  
    $("#id_detalleasistencias").val(Linea.id_det);
    $("#estado_asistencia").val(Linea.estado);
    modificarDetalleAsistencia();
    alert(Linea.id_det);
    alert(Linea.estado);
    //siguienteCampo("#cantidad_articulopedido", "#botonModificarLinea", true);
}
// responsablesalumnos
function buscarIdDetalleAsistencia() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdDetalleAsistencia.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#estado_asistencia").val(json.estado_asistencia);
            $("#contenidoDetalle").html(json.contenido_detalle);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function agregarDetalleAsistencia() {
    var datosFormulario = $("#formPrograma").serialize();
    var id_asistencia = $("#id_asistencia").val();
    datosFormulario += "&id_asistencia=" + id_asistencia;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarDetalleAsistencia.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdAsistencia();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarDetalleAsistencia() {
    
    var datosFormulario = $("#formPrograma").serialize();
    var DetalleAsistencia = "";
    
    $("#contenidoDetalle tr").each(function(){
       
        var id_detalle = $(this).find("td").eq(0).html();
        var estado = $(this).find("td").eq(4).find("input").val();
        DetalleAsistencia += id_detalle + ":" + estado + ";";
        
    });
    
    datosFormulario += "&detalle=" + DetalleAsistencia;
    
  alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarDetalleAsistencia.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            //$("#panelLinea").fadeOut("slow");
            //$("#panelPrograma").fadeIn("slow");
           // buscarIdAsistencia();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarDetalleAsistencia() {
    var datosFormulario = $("#formPrograma").serialize();
    var id_asistencia = $("#id_asistencia").val();
    datosFormulario += "&id_asistencia=" + id_asistencia;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarDetalleAsistencia.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json)
        {
            $("#mensajes").html(json.mensaje);
            //$("#panelLinea").fadeOut("slow");
            //$("#panelPrograma").fadeIn("slow");
            buscarIdAsistencia();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
//// inscriptos
function buscarIdInscripcion() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdInscripcion.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_inscripcion").val(json.id_inscripcion);
            $("#nombre_alumno").val(json.nombre_alumno);
            $("#apellido_alumno").val(json.apellido_alumno);
            $("#nombre_grado").val(json.nombre_grado);
            $("#nombre_especialidad").val(json.nombre_especialidad);
            $("#nombre_seccion").val(json.nombre_seccion);
            $("#nombre_turno").val(json.nombre_turno);
        },
        error: function (e) {
            $("#mensajes").html("No se puede recuperar los datos");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreInscripcion() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreInscripcion.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_inscripcion").val(id);
                $("#ci_alumno").focus();
                buscarIdInscripcion();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function buscarMateria() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarMateria.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            //$("#fecha_pedido").val(json.fecha_pedido);
            //var fecha = $("#fecha_pedido").serialize();
            $("#id_asignatura").val(json.id_asignatura);
            $("#nombre_asignatura").val(json.nombre_asignatura);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreMateria() {


    var datosFormulario = $("#formBuscar").serialize();
    var id_convocatoria = $("#id_convocatoria").val();
    datosFormulario += "&id_convocatoria=" + id_convocatoria;
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreMateria.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");

                $("#id_asignatura").val(id);
                $("#nombre_asignatura").focus();
                buscarIdAsignatura();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function buscarProfesor() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarProfesor.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            //$("#fecha_pedido").val(json.fecha_pedido);
            //var fecha = $("#fecha_pedido").serialize();
            $("#id_detalleconvocatoria").val(json.id_detalleconvocatoria);
            $("#id_asignatura").val(json.id_asignatura);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarIdAsignatura() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdAsignatura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            buscarProfesor();
            $("#mensajes").html(json.mensaje);
            $("#id_asignatura").val(json.id_asignatura);
            $("#nombre_asignatura").val(json.nombre_asignatura);
        },
        error: function (e) {
            $("#mensajes").html("No se puede recuperar los datos");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function agregarAsignatura() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarAsignatura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviandos datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_asignatura").focus();
            $("#id_asignatura").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_asignatura").focus();
        }
    });

}