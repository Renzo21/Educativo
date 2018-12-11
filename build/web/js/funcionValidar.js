function login() {

                var usuario = $("#usuario").val();
                var password = $("#password").val();
                var login = "flopy"
                alert(usuario);

                /*no usar el mismo nombre de usuario y contraseña para definir variables*/

                var x = new Date();
                var fecha = $("#fecha_nac").split("-");

                x.setFullYear(fecha[0], fecha[1], fecha[2]);
                var anho = fecha [0];
                var mes = fecha[1];
                var dia = fecha[2];

                var hoy = new Date();
                var ahora_anho = hoy.getYear();
                var ahora_mes = hoy.getMonth();
                var ahora_dia = hoy.getDate();

                var edad = (ahora_anho + 1900) - anho;

                if (usuario == null || usuario.length == 0 || /^\s+$/.test(usuario)) {
                    $("#usuario").focus();//limpiar
                    alert("Debe ingresar un usuario");
                    return false;
                }



                if (!(usuario === login)) {

                    $("#usuario").val(" ");
                   $("#usuario").focus();
                    alert("El usuario es incorrecto");
                    return false;
                }

                if (password == null || password.length == 0 || /^\s+$/.test(password)) {
                    $("#password").focus();
                    alert("Debe ingresar la contraseña");
                    return false;
                }

                if (password !== "123456") {
                       $("#password").val(" ");
                      $("#password").focus();
                    alert("La contraseña ingresada es incorrecta");
                    return false;
                }

                if (!isNaN(fecha)) {
                    alert("debe elegir la edad");
                    $("#fecha_nac").val("dd/mm/aaaa");
                       $("#fecha_nac").focus();
                    return false;
                }


                if (ahora_mes < (mes - 1)) {
                    edad--;
                }

                if (((mes - 1) === ahora_mes) && (ahora_dia < dia)) {
                    edad--;
                }
                if (edad > 1900) {
                    edad -= 1900;
                }

                if (!(edad >= 18)) {
                    alert("Es menor de edad");
                    $("#fecha_nac").val("dd/mm/aaaa");
                   $("#fecha_nac").focus();

                    return false;
                }
            }/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


