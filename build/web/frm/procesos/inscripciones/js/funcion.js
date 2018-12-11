



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
                buscarIdInscripcion();
            if (json.cupo_convocatoria === 0) {
                $("#mensajes").html("El cupo esta lleno");
            } else {
                $("#mensajes").html(json.mensaje);
                //buscarIdInscripcion();
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_inscripcion").focus();
        }
    });

}

function buscarIdInscripcion() {
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
            $("#id_inscripcion").val(json.id_inscripcion);
            $("#id_convocatoria").val(json.id_convocatoria);
            $("#id_alumno").val(json.id_alumno);
            $("#nombre_alumno").val(json.nombre_alumno);
            $("#apellido_alumno").val(json.apellido_alumno);
            $("#ci_alumno").val(json.ci_alumno);
            $("#nombre_sede").val(json.nombre_sede);
            $("#nombre_grado").val(json.nombre_grado);
            $("#nombre_especialidad").val(json.nombre_especialidad);
            $("#nombre_seccion").val(json.nombre_seccion);
            $("#nombre_turno").val(json.nombre_turno);
            $("#fecha_inscripcion").val(json.fecha_inscripcion);
            var fecha = $("#fecha_inscripcion").serialize();
            $("#estado_inscripcion").val(json.estado_inscripcion);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                //    siguienteCampo("#nombre_ninscripcion", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                // siguienteCampo("#nombre_ninscripcion", "#botonModificar", true);
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
function buscarIdAlumno() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdAlumno.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_alumno").val(json.id_alumno);
            $("#nombre_alumno").val(json.nombre_alumno);
            $("#apellido_alumno").val(json.apellido_alumno);
            $("#ci_alumno").val(json.ci_alumno);
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
            $("#nombre_sede").val(json.nombre_sede);
            $("#nombre_grado").val(json.nombre_grado);
            $("#nombre_especialidad").val(json.nombre_especialidad);
            $("#nombre_seccion").val(json.nombre_seccion);
            $("#nombre_turno").val(json.nombre_turno);

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

function buscarNombreInscripcion() {
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
                $("#id_inscripcion").val(id);
                $("#id_alumno").focus();
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
function buscarNombreAlumno() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreAlumno.jsp',
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
                $("#id_alumno").val(id);
                $("#nombre_alumno").focus();
                buscarIdAlumno();
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
                $("#nombre_sede").focus();
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
            $("#id_inscripcion").focus();
            $("#id_inscripcion").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_inscripcion").focus();
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
            $("#id_inscripcion").focus();
            $("#id_inscripcion").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_inscripcion").focus();
        }
    });

}

function validarFormulario() {
    var valor = true;
    if ($("#nombre_alumno").val().trim() === "") {
        valor = false;
        $("#mensajes").html("El nombre del alumno no puede estar vacio");
        $("#id_alumno").focus();
    } else {
        if ($("#nombre_sede").val().trim() === "") {
            valor = false;
            $("#mensajes").html("El nombre de la sede no puede estar vacio");
            $("#id_convocatoria").focus();
        }
    }
    return valor;
}

function limpiarFormulario() {
    $("#id_inscripcion").val("0");
    $("#id_alumno").val("0");
    $("#nombre_alumno").val("");
    $("#apellido_alumno").val("");
    $("#ci_alumno").val("");
    $("#id_convocatoria").val("0");
    $("#nombre_sede").val("");
    $("#nombre_grado").val("");
    $("#nombre_especialidad").val("");
    $("#nombre_seccion").val("");
    $("#nombre_turno").val("");
    $("#fecha_inscripcion").val("");
    $("#estado_inscripcion").val("");
}

function doble(){
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/doble.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_inscripcion").val(json.id_inscripcion);
            $("#id_alumno").val(json.id_alumno);
            $("#id_convocatoria").val(json.id_convocatoria);

        },
        error: function (e) {
            $("#mensajes").html("El alumno ya esta inscripto.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}