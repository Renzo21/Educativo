



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
            $("#id_genero").focus();
            $("#id_genero").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_genero").focus();
        }
    });

}

function buscarIdGenero() {
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
            $("#id_genero").val(json.id_genero);
            $("#nombre_genero").val(json.nombre_genero);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
            //    siguienteCampo("#nombre_ngenero", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
               // siguienteCampo("#nombre_ngenero", "#botonModificar", true);
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

function buscarNombreGenero() {
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
            $("#id_genero").focus();
            $("#id_genero").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_genero").focus();
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
            $("#id_genero").focus();
            $("#id_genero").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_genero").focus();
        }
    });

}

function validarFormulario(){
    var valor = true;
    if($("#nombre_genero").val().trim()===""){
        valor = false;
        $("#mensajes").html("El genero no puede estar vacio");
        $("#nombre_genero").focus();
    }
    return valor;
}

function limpiarFormulario() {
    $("#id_genero").val("0");
    $("#nombre_genero").val("");
}

function buscarNombreRepetidoGenero() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreRepetidoGenero.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
           
            $("#nombre_genero").val(json.nombre_genero);
            $("#nombre_genero").focus();
            if ($("#nombre_genero").val().trim() === "") {
                $("#mensajes").html("El nombre es repetido");
                $("#nombre_genero").focus();
                 $("#mensajes").html(json.mensaje);
              console.log("repetido");
            } else {
                 $("#mensajes").html("");
                $("#nombre_genero").val(json.nombre_genero);
                $("#genero_alumno").focus();
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