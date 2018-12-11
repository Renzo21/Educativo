



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
            $("#id_responsable").focus();
            $("#id_responsable").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_responsable").focus();
        }
    });

}

function buscarIdResponsable() {
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
            $("#id_responsable").val(json.id_responsable);
            $("#nombre_responsable").val(json.nombre_responsable);
            $("#apellido_responsable").val(json.apellido_responsable);
            $("#ci_responsable").val(json.ci_responsable);
            $("#direccion_responsable").val(json.direccion_responsable);
            $("#telefono_responsable").val(json.telefono_responsable);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                //    siguienteCampo("#nombre_nresponsable", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                // siguienteCampo("#nombre_nresponsable", "#botonModificar", true);
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
function buscarIdCiudad() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCiudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
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

function buscarNombreResponsable() {
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
                $("#id_responsable").val(id);
                $("#nombre_responsable").focus();
                buscarIdResponsable();
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
function buscarNombreCiudad() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCiudad.jsp',
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
                $("#id_ciudad").val(id);
                $("#nombre_ciudad").focus();
                buscarIdCiudad();
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
            $("#id_responsable").focus();
            $("#id_responsable").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_responsable").focus();
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
            $("#id_responsable").focus();
            $("#id_responsable").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_responsable").focus();
        }
    });

}

function validarFormulario() {
    var valor = true;
    if ($("#nombre_responsable").val().trim() === "") {
        valor = false;
        $("#mensajes").html("El nombre del responsable no puede estar vacio");
        $("#nombre_responsable").focus();
    } else {
        if ($("#apellido_responsable").val().trim() === "") {
            valor = false;
            $("#mensajes").html("El apellido del responsable no puede estar vacio");
            $("#apellido_responsable").focus();
        } else {
            if ($("#ci_responsable").val().trim() === "") {
                valor = false;
                $("#mensajes").html("La cedula del responsable no puede estar vacio");
                $("#ci_responsable").focus();
            } else {
                if ($("#direccion_responsable").val().trim() === "") {
                    valor = false;
                    $("#mensajes").html("La direccion del responsable no puede estar vacio");
                    $("#direccion_responsable").focus();
                } else {
                    if ($("#telefono_responsable").val().trim() === "") {
                        valor = false;
                        $("#mensajes").html("El telefono del responsable no puede estar vacio");
                        $("#telefono_responsable").focus();
                    } else {
                        if ($("#nombre_ciudad").val().trim() === "") {
                            valor = false;
                            $("#mensajes").html("La ciudad no puede estar vacio");
                            $("#nombre_ciudad").focus();
                        }
                    }
                }
            }
        }
    }
     return valor;
}

function limpiarFormulario() {
    $("#id_responsable").val("0");
    $("#nombre_responsable").val("");
    $("#apellido_responsable").val("");
    $("#ci_responsable").val("");
    $("#direccion_responsable").val("");
    $("#telefono_responsable").val("");
    $("#id_ciudad").val("0");
    $("#nombre_ciudad").val("");
}

function buscarCedulaResponsable() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCedulaResponsable.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
           
            $("#ci_responsable").val(json.ci_responsable);
            $("#ci_responsable").focus();
            if ($("#ci_responsable").val().trim() === "") {
                $("#mensajes").html("La cedula es repetida");
                $("#ci_responsable").focus();
                 $("#mensajes").html(json.mensaje);
              console.log("repetido");
            } else {
                 $("#mensajes").html("");
                $("#ci_responsable").val(json.ci_responsable);
                $("#direccion_responsable").focus();
                 $("#mensajes").html(json.mensaje);
              console.log("no repetido");
            }
        },
        error: function (e) {
            $("#mensajes").html("La cedula es repetida");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}       