function fechaHoy() {

    var hoy = new new Date().toJSON().slice(0, 10);



    console.log(hoy);
    $("#fecha_justificativo").val(hoy);
}
function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}

function buscarIdJustificativo() {
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
            $("#id_justificativo").val(json.id_justificativo);
            $("#motivo_justificativo").val(json.motivo_justificativo);

            //var fecha = $("#fecha_asistencia").serialize();
            $("#contenidoDetalle").html(json.contenido_detalle);
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
function buscarNombreJustificativo() {
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
                $("#id_justificativo").val(id);
                $("#motivo_justificativo").focus();
                buscarIdJustificativo();
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
function agregarJustificativo() {
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
            $("#id_justificativo").val(json.id_justificativo);
            buscarIdJustificativo();
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
function modificarJustificativo() {
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
            $("#id_justificativo").focus;
            $("#id_justificativo").select();
            buscarIdJustificativo();
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
function eliminarJustificativo() {
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
            eliminarDetalleJustificativo();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_justificativo").focus;
            $("#id_justificativo").select();
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
function validarFormulario() {
    var valor = true;
    if ($("#motivo_justificativo").val().length < 3) {
        valor = false;
        $("#mensajes").html("Motivo Justificativo no puede estar vacio.");
        $("#motivo_justificativo").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_justificativo").val("0");
    $("#motivo_justificativo").val("");

}
function agregarLinea() {
    $("#id_detallejustificativos").val("0");
    $("#id_inscripcion").val("0");
    $("#nombre_alumno").val("");
    $("#apellido_alumno").val("");
    $("#nombre_grado").val("");
    $("#nombre_seccion").val("");
    $("#nombre_turno").val("");
    $("#fecha_justificativo").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_inscripcion").focus();
    $("#id_inscripcion").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    //siguienteCampo("#horas_detallepedido", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detallejustificativos").val(id);
    $("#id_inscripcion").val("0");
    $("#nombre_alumno").val("");
    $("#apellido_alumno").val("");
    $("#nombre_grado").val("");
    $("#nombre_seccion").val("");
    $("#nombre_turno").val("");
    $("#fecha_justificativo").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_inscripcion").focus();
    $("#id_inscripcion").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdDetalleJustificativo();
    //siguienteCampo("#cantidad_articulopedido", "#botonModificarLinea", true);
}
// responsablesalumnos
function buscarIdDetalleJustificativo() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdDetalleJustificativo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_inscripcion").val(json.id_inscripcion);
            $("#nombre_alumno").val(json.nombre_alumno);
            $("#apellido_alumno").val(json.apellido_alumno);
            $("#nombre_grado").val(json.nombre_grado);
            $("#nombre_seccion").val(json.nombre_seccion);
            $("#nombre_turno").val(json.nombre_turno);
            $("#fecha_justificativo").val(json.fecha_justificativo);
            
            var fecha = $("#fecha_justificativo").serialize();
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
function agregarDetalleJustificativo() {
    var datosFormulario = $("#formLinea").serialize();
    var id_justificativo = $("#id_justificativo").val();
    datosFormulario += "&id_justificativo=" + id_justificativo;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarDetalleJustificativo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdJustificativo();
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
function modificarDetalleJustificativo() {
    var datosFormulario = $("#formLinea").serialize();
    var id_justificativo = $("#id_justificativo").val();
    datosFormulario += "&id_justificativo=" + id_justificativo;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarDetalleJustificativo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdJustificativo();
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
function eliminarDetalleJustificativo() {
    var datosFormulario = $("#formLinea").serialize();
    var id_justificativo = $("#id_justificativo").val();
    datosFormulario += "&id_justificativo=" + id_justificativo;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarDetalleJustificativo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json)
        {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdJustificativo();

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
    var datosFormulario = $("#formLinea").serialize();
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
                $("#panelLinea").fadeIn("slow");
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