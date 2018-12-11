
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
            $("#id_profesor").focus();
            $("#id_profesor").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_profesor").focus();
        }
    });

}

function buscarIdProfesor() {
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
            $("#id_profesor").val(json.id_profesor);
            $("#nombre_profesor").val(json.nombre_profesor);
            $("#apellido_profesor").val(json.apellido_profesor);
            $("#ci_profesor").val(json.ci_profesor);
            $("#direccion_profesor").val(json.direccion_profesor);
            $("#telefono_profesor").val(json.telefono_profesor);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            $("#id_nacionalidad").val(json.id_nacionalidad);
            $("#nombre_nacionalidad").val(json.nombre_nacionalidad);
            $("#id_genero").val(json.id_genero);
            $("#nombre_genero").val(json.nombre_genero);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                //    siguienteCampo("#nombre_nprofesor", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                // siguienteCampo("#nombre_nprofesor", "#botonModificar", true);
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
function buscarIdNacionalidad() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdNacionalidad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_nacionalidad").val(json.id_nacionalidad);
            $("#nombre_nacionalidad").val(json.nombre_nacionalidad);
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
function buscarIdGenero() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdGenero.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_genero").val(json.id_genero);
            $("#nombre_genero").val(json.nombre_genero);
            
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

function buscarNombreProfesor() {
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
                $("#id_profesor").val(id);
                $("#nombre_profesor").focus();
                buscarIdProfesor();
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
function buscarNombreNacionalidad() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreNacionalidad.jsp',
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
                $("#id_nacionalidad").val(id);
                $("#nombre_nacionalidad").focus();
                buscarIdNacionalidad();
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
function buscarNombreGenero() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreGenero.jsp',
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
                $("#id_genero").val(id);
                $("#nombre_genero").focus();
                buscarIdGenero();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function(objeto,exito,error){
            if(exito==="success"){
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
            $("#id_profesor").focus();
            $("#id_profesor").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_profesor").focus();
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
            $("#id_profesor").focus();
            $("#id_profesor").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_profesor").focus();
        }
    });

}

function validarFormulario() {
    var valor = true;
    if ($("#nombre_profesor").val().trim() === "") {
        valor = false;
        $("#mensajes").html("El nombre del profesor no puede estar vacio");
        $("#nombre_profesor").focus();
    } else {
        if ($("#apellido_profesor").val().trim() === "") {
            valor = false;
            $("#mensajes").html("El apellido del profesor no puede estar vacio");
            $("#apellido_profesor").focus();
        } else {
            if ($("#ci_profesor").val().trim() === "") {
                valor = false;
                $("#mensajes").html("La cedula del profesor no puede estar vacio");
                $("#ci_profesor").focus();
            } else {
                if ($("#direccion_profesor").val().trim() === "") {
                    valor = false;
                    $("#mensajes").html("La direccion del profesor no puede estar vacio");
                    $("#direccion_profesor").focus();
                } else {
                    if ($("#telefono_profesor").val().trim() === "") {
                        valor = false;
                        $("#mensajes").html("El telefono del profesor no puede estar vacio");
                        $("#telefono_profesor").focus();
                    } else {
                        if ($("#nombre_ciudad").val().trim() === "") {
                            valor = false;
                            $("#mensajes").html("El nombre de la ciudad no puede estar vacio");
                            $("#nombre_ciudad").focus();
                        } else {
                            if ($("#nombre_nacionalidad").val().trim() === "") {
                                valor = false;
                                $("#mensajes").html("El nombre de la nacionalidad no puede estar vacio");
                                $("#nombre_nacionalidad").focus();
                            } else {
                                if ($("#nombre_genero").val().trim() === "") {
                                    valor = false;
                                    $("#mensajes").html("El genero del profesor no puede estar vacio");
                                    $("#nombre_genero").focus();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return valor;
}

function limpiarFormulario() {
    $("#id_profesor").val("0");
    $("#nombre_profesor").val("");
    $("#apellido_profesor").val("");
    $("#ci_profesor").val("");
    $("#direccion_profesor").val("");
    $("#telefono_profesor").val("");
    $("#id_ciudad").val("0");
    $("#nombre_ciudad").val("");
    $("#id_nacionalidad").val("0");
    $("#nombre_nacionalidad").val("");
    $("#id_genero").val("0");
    $("#nombre_genero").val("");
}

function buscarCedulaProfesor() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCedulaProfesor.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
           
            $("#ci_profesor").val(json.ci_profesor);
            $("#ci_profesor").focus();
            if ($("#ci_profesor").val().trim() === "") {
                $("#mensajes").html("La cedula es repetida");
                $("#ci_profesor").focus();
                 $("#mensajes").html(json.mensaje);
              console.log("repetido");
            } else {
                 $("#mensajes").html("");
                $("#ci_profesor").val(json.ci_profesor);
                $("#direccion_profesor").focus();
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