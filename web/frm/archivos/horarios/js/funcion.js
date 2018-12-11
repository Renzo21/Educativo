
function agregar() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviandos datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_horario").focus();
            $("#id_horario").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_horario").focus();
        }
    });

}

function buscarIdHorario() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_horario").val(json.id_horario);
            $("#hora_inicio").val(json.hora_inicio);
            $("#hora_fin").val(json.hora_fin);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                //    siguienteCampo("#nombre_nhorario", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                // siguienteCampo("#nombre_nhorario", "#botonModificar", true);
            }
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

function buscarNombreHorario() {
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
                $("#id_horario").val(id);
                $("#nombre_horario").focus();
                buscarIdHorario();
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

function modificar() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviandos datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_horario").focus();
            $("#id_horario").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_horario").focus();
        }
    });

}

function eliminar() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviandos datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_horario").focus();
            $("#id_horario").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_horario").focus();
        }
    });

}

function validarFormulario() {
    var valor = true;
    if ($("#hora_inicio").val().trim() === "") {
        valor = false;
        $("#mensajes").html("La hora inicial no puede estar vacia");
        $("#hora_inicio").focus();

    } else {
        if ($("#hora_fin").val().trim() === "") {
            valor = false;
            $("#mensajes").html("La hora final no puede estar vacia");
            $("#hora_fin").focus();

        }
    }
    return valor;
}

function limpiarFormulario() {
    $("#id_horario").val("0");
    $("#hora_inicio").val("");
    $("#hora_fin").val("");
}