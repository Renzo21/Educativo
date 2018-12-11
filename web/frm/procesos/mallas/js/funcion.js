function fechaHoy() {

    var hoy = new new Date().toJSON().slice(0, 10);



    console.log(hoy);
    $("#fecha_permiso").val(hoy);
}
function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}

function buscarIdMalla() {
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
            $("#id_malla").val(json.id_malla);
            $("#id_grado").val(json.id_grado);
            $("#nombre_grado").val(json.nombre_grado);
            $("#id_especialidad").val(json.id_especialidad);
            $("#nombre_especialidad").val(json.nombre_especialidad);
            $("#modalidad_malla").val(json.modalidad_malla);

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
function buscarNombreMalla() {
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
                $("#id_malla").val(id);
                $("#modalidad_malla").focus();
                buscarIdMalla();
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
function agregarMalla() {
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
            $("#id_malla").val(json.id_malla);
            buscarIdMalla;
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
function modificarMalla() {
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
            $("#id_malla").focus;
            $("#id_malla").select();
            buscarIdMalla();
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
function eliminarMalla() {
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
            eliminarDetalleMalla;
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_malla").focus;
            $("#id_malla").select();
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
function buscarIdGrado() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdGrado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_grado").val(json.id_grado);
            $("#nombre_grado").val(json.nombre_grado);
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

function buscarNombreGrado() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreGrado.jsp',
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
                $("#id_grado").val(id);
                $("#nombre_grado").focus();
                buscarIdGrado();
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
function buscarIdEspecialidad() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdEspecialidad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_especialidad").val(json.id_especialidad);
            $("#nombre_especialidad").val(json.nombre_especialidad);
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

function buscarNombreEspecialidad() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreEspecialidad.jsp',
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
                $("#id_especialidad").val(id);
                $("#nombre_especialidad").focus();
                buscarIdEspecialidad();
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

function validarFormulario() {
    var valor = true;
    if ($("#nombre_grado").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre Grado no puede estar vacio.");
        $("#id_grado").focus();
    }
    if ($("#nombre_especialidad").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre Especialidad no puede estar vacio.");
        $("#id_especialidad").focus();
    }
    if ($("#modalidad_malla").val().length < 3) {
        valor = false;
        $("#mensajes").html("La modalidad de la malla no puede estar vacio.");
        $("#modalidad_malla").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_malla").val("0");
    $("#id_grado").val("0");
    $("#id_especialidad").val("0");
    $("#nombre_grado").val("");
    $("#nombre_especialidad").val("");
    $("#modalidad_malla").val("");

}
function agregarLinea() {
    $("#id_detallemalla").val("0");
    $("#id_asignatura").val("0");
    $("#nombre_asignatura").val("");
    $("#cargahoraria_total").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_asignatura").focus();
    $("#id_asignatura").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    //siguienteCampo("#horas_detallepedido", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detallemalla").val(id);
    $("#id_asignatura").val("0");
    $("#nombre_asignatura").val("");
    $("#cargahoraria_total").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_asignatura").focus();
    $("#id_asignatura").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdDetalleMalla();
    //siguienteCampo("#cantidad_articulopedido", "#botonModificarLinea", true);
}
// responsablesalumnos
function buscarIdDetalleMalla() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdDetalleMalla.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_asignatura").val(json.id_asignatura);
            $("#nombre_asignatura").val(json.nombre_asignatura);
            $("#cargahoraria_total").val(json.cargahoraria_total);
            
            //var fecha = $("#fecha_sancion").serialize();
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
function agregarDetalleMalla() {
    var datosFormulario = $("#formLinea").serialize();
    var id_malla = $("#id_malla").val();
    datosFormulario += "&id_malla=" + id_malla;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarDetalleMalla.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdMalla();
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
function modificarDetalleMalla() {
    var datosFormulario = $("#formLinea").serialize();
    var id_malla = $("#id_malla").val();
    datosFormulario += "&id_malla=" + id_malla;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarDetalleMalla.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdMalla();
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
function eliminarDetalleMalla() {
    var datosFormulario = $("#formLinea").serialize();
    var id_malla = $("#id_malla").val();
    datosFormulario += "&id_malla=" + id_malla;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarDetalleMalla.jsp',
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
            buscarIdMalla();

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
//// asignaturas
function buscarIdAsignatura() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdAsignatura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
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

function buscarNombreAsignatura() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreAsignatura.jsp',
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
                $("#panelLinea").fadeIn("slow");
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