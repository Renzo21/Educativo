



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
            $("#id_frecuencia").focus();
            $("#id_frecuencia").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_frecuencia").focus();
        }
    });

}

function buscarIdFrecuencia() {
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
            $("#id_frecuencia").val(json.id_frecuencia);
            $("#nombre_frecuencia").val(json.nombre_frecuencia);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
            //    siguienteCampo("#nombre_nfrecuencia", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
               // siguienteCampo("#nombre_nfrecuencia", "#botonModificar", true);
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

function buscarNombreFrecuencia() {
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
                $("#id_frecuencia").val(id);
                $("#nombre_frecuencia").focus();
                buscarIdFrecuencia();
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
            $("#id_frecuencia").focus();
            $("#id_frecuencia").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_frecuencia").focus();
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
            $("#id_frecuencia").focus();
            $("#id_frecuencia").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");

        },
        complete: function (objeto, exito, error) {
            $("#id_frecuencia").focus();
        }
    });

}

function validarFormulario(){
    var valor = true;
    if($("#nombre_frecuencia").val().trim()===""){
        valor = false;
        $("#mensajes").html("El nombre de la frecuencia no puede estar vacio");
        $("#nombre_frecuencia").focus();
        
    }
    return valor;
}

function limpiarFormulario() {
    $("#id_frecuencia").val("0");
    $("#nombre_frecuencia").val("");
}

function buscarNombreRepetidoFrecuencia() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreRepetidoFrecuencia.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
           
            $("#nombre_frecuencia").val(json.nombre_frecuencia);
            $("#nombre_frecuencia").focus();
            if ($("#nombre_frecuencia").val().trim() === "") {
                $("#mensajes").html("El nombre es repetido");
                $("#nombre_frecuencia").focus();
                 $("#mensajes").html(json.mensaje);
              console.log("repetido");
            } else {
                 $("#mensajes").html("");
                $("#nombre_frecuencia").val(json.nombre_frecuencia);
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