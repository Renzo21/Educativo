function fechaHoy(){

var hoy = new  new Date().toJSON().slice(0,10);



console.log(hoy);
 $("#fecha_pedido").val(hoy);
}
function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}

function buscarIdConvocatoria() { 
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
            $("#id_convocatoria").val(json.id_convocatoria);
            //$("#fecha_pedido").val(json.fecha_pedido);
             //var fecha = $("#fecha_pedido").serialize();

            $("#id_sede").val(json.id_sede);
            $("#nombre_sede").val(json.nombre_sede);
            $("#id_grado").val(json.id_grado);
            $("#nombre_grado").val(json.nombre_grado);
            $("#id_especialidad").val(json.id_especialidad);
            $("#nombre_especialidad").val(json.nombre_especialidad);
            $("#id_seccion").val(json.id_seccion);
            $("#nombre_seccion").val(json.nombre_seccion);
            $("#id_turno").val(json.id_turno);
            $("#nombre_turno").val(json.nombre_turno);
            $("#id_periodo").val(json.id_periodo);
            $("#nombre_periodo").val(json.nombre_periodo);
            $("#cupo_convocatoria").val(json.cupo_convocatoria);
            $("#estado_convocatoria").val(json.estado_convocatoria);
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
function buscarNombreConvocatoria() {
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
function agregarConvocatoria() {
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
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_convocatoria").val(json.id_convocatoria);
            buscarIdConvocatoria();
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
function modificarConvocatoria() {
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
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_convocatoria").focus;
            $("#id_convocatoria").select();
            buscarIdConvocatoria();
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
function eliminarConvocatoria() {
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
            eliminarConvocatoriaDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_convocatoria").focus;
            $("#id_convocatoria").select();
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

function buscarIdSedeConvocatoria() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdSede.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_sede").val(json.id_sede);
            $("#nombre_sede").val(json.nombre_sede);
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
function buscarNombreSedeConvocatoria() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreSede.jsp',
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
                $("#id_sede").val(id);
                $("#nombre_sede").focus();
                buscarIdSedeConvocatoria();
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
function buscarIdSeccion() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdSeccion.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_seccion").val(json.id_seccion);
            $("#nombre_seccion").val(json.nombre_seccion);
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
function buscarNombreSeccion() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreSeccion.jsp',
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
                $("#id_seccion").val(id);
                $("#nombre_seccion").focus();
                buscarIdSeccion();
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
function buscarIdTurno() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTurno.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_turno").val(json.id_turno);
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
function buscarNombreTurno() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreTurno.jsp',
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
                $("#id_turno").val(id);
                $("#nombre_turno").focus();
                buscarIdTurno();
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
function buscarIdPeriodo() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPeriodo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_periodo").val(json.id_periodo);
            $("#nombre_periodo").val(json.nombre_periodo);
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
function buscarNombrePeriodo() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombrePeriodo.jsp',
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
                $("#id_periodo").val(id);
                $("#nombre_periodo").focus();
                buscarIdPeriodo();
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
    if ($("#nombre_sede").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre Sede no puede estar vacio.");
        $("#id_sede").focus();
    }

    if ($("#nombre_grado").val().length < 2) {
        valor = false;
        $("#mensajes").html("Grado no puede estar vacio.");
        $("#id_grado").focus();
    }

    if ($("#nombre_especialidad").val().length < 2) {
        valor = false;
        $("#mensajes").html("Especialidad no puede estar vacio.");
        $("#id_especialidad").focus();
    }
    
    if ($("#nombre_seccion").val().length < 2) {
        valor = false;
        $("#mensajes").html("Seccion no puede estar vacio.");
        $("#id_seccion").focus();
    }
    
    if ($("#nombre_turno").val().length < 2) {
        valor = false;
        $("#mensajes").html("Turno no puede estar vacio.");
        $("#id_turno").focus();
    }
    
    if ($("#nombre_periodo").val().length < 2) {
        valor = false;
        $("#mensajes").html("Periodo no puede estar vacio.");
        $("#id_periodo").focus();
    }
    
    if ($("#cupo_convocatoria").val().length < 2) {
        valor = false;
        $("#mensajes").html("Cupo no puede estar vacio.");
        $("#cupo_convocatoria").focus();
    }
    if ($("#estado_convocatoria").val().length < 2) {
        valor = false;
        $("#mensajes").html("Estado de la convocatoria no puede estar vacio.");
        $("#estado_convocatoria").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_convocatoria").val("0");
    $("#id_sede").val("0");
    $("#nombre_sede").val("");
    $("#id_grado").val("0");
    $("#nombre_grado").val("");
    $("#id_especialidad").val("0");
    $("#nombre_especialidad").val("");
    $("#id_seccion").val("0");
    $("#nombre_seccion").val("");
    $("#id_turno").val("0");
    $("#nombre_turno").val("");
    $("#id_periodo").val("0");
    $("#nombre_periodo").val("");
    $("#cupo_convocatoria").val("0");
    $("#estado_convocatoria").val("");

}
function agregarLinea() {
    $("#id_detalleconvocatoria").val("0");
    $("#id_profesor").val("0");
    $("#nombre_profesor").val("");
    $("#apellido_profesor").val("");
    $("#id_asignatura").val("0");
    $("#nombre_asignatura").val("");
    $("#id_horario").val("0");
    $("#hora_inicio").val("");
    $("#hora_fin").val("");
    $("#id_frecuencia").val("0");
    $("#nombre_frecuencia").val("");
    $("#id_aula").val("0");
    $("#nombre_aula").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_profesor").focus();
    $("#id_profesor").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    //siguienteCampo("#horas_detallepedido", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detalleconvocatoria").val(id);
    $("#id_profesor").val("0");
    $("#nombre_profesor").val("");
    $("#apellido_profesor").val("");
    $("#id_asignatura").val("0");
    $("#nombre_asignatura").val("");
    $("#id_horario").val("0");
    $("#hora_inicio").val("");
    $("#hora_fin").val("");
    $("#id_frecuencia").val("0");
    $("#nombre_frecuencia").val("");
    $("#id_aula").val("0");
    $("#nombre_aula").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_profesor").focus();
    $("#id_profesor").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdConvocatoriaDetalle();
    //siguienteCampo("#cantidad_articulopedido", "#botonModificarLinea", true);
}
// convocatoriasprofesorasignaturahorariofrecuencia
function buscarIdConvocatoriaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdConvocatoriaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_profesor").val(json.id_profesor);
            $("#nombre_profesor").val(json.nombre_profesor);
            $("#apellido_profesor").val(json.apellido_profesor);
            $("#id_asignatura").val(json.id_asignatura);
            $("#nombre_asignatura").val(json.nombre_asignatura);
            $("#id_horario").val(json.id_horario);
            $("#hora_inicio").val(json.hora_inicio);
            $("#hora_fin").val(json.hora_fin);
            $("#id_frecuencia").val(json.id_frecuencia);
            $("#nombre_frecuencia").val(json.nombre_frecuencia);
            $("#id_aula").val(json.id_aula);
            $("#nombre_aula").val(json.nombre_aula);
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
function buscarIdConvocatoriaConvocatoriaDetalle() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdConvocatoriaConvocatoriaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
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
function agregarConvocatoriaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_convocatoria = $("#id_convocatoria").val();
    datosFormulario += "&id_convocatoria=" + id_convocatoria;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarConvocatoriaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdConvocatoria();
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
function modificarConvocatoriaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_convocatoria = $("#id_convocatoria").val();
    datosFormulario += "&id_convocatoria=" + id_convocatoria;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarConvocatoriaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdConvocatoria();
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
function eliminarConvocatoriaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_convocatoria = $("#id_convocatoria").val();
    datosFormulario += "&id_convocatoria=" + id_convocatoria;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarConvocatoriaDetalle.jsp',
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
            buscarIdConvocatoria();

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

function buscarIdProfesor() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdProfesor.jsp',
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
        url: 'jsp/buscarNombreProfesor.jsp',
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
function buscarIdHorario() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdHorario.jsp',
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
        url: 'jsp/buscarNombreHorario.jsp',
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
function buscarIdFrecuencia() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdFrecuencia.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_frecuencia").val(json.id_frecuencia);
            $("#nombre_frecuencia").val(json.nombre_frecuencia);
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
        url: 'jsp/buscarNombreFrecuencia.jsp',
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

function buscarIdAula() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdAula.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_aula").val(json.id_aula);
            $("#nombre_aula").val(json.nombre_aula);
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

function buscarNombreAula() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreAula.jsp',
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
                $("#id_aula").val(id);
                $("#nombre_aula").focus();
                buscarIdAula();
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