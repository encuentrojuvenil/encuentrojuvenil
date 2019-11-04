<%-- 
    Document   : Inicio
    Created on : 14/10/2019, 06:52:59 PM
    Author     : Lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cursos</title>
        <link href="CSS/bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="CSS/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="CSS/Inicio.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="Menu.jsp" %>
        <% if (rol != null) {
                if (rol.equals("2") || rol.equals("1")) {
        %>
        <div class="container">

            <h1>Cursos!</h1>
            <div id="separador">

            </div>
            <div id="ss">

            </div>
        </div>
        <script src="JQuery/JQuery.js"></script>
        <script src="JQuery/Alertas.js"></script>
        <script src="CSS/bootstrap/js/bootstrap.min.js"></script>
        <script src="CSS/bootstrap/js/bootstrap.js"></script>
        <script type="text/javascript">
            window.onload = function () {
                $.ajax({
                    type: 'POST',
                    url: "Cursos",
                    success: function (resultado) {
                        $("#ss").html(resultado);
                    }
                });
            }
            function votar() {
                if ($("#IdCurso").val() !== "" || $("#IdCurso").val() != null) {
                    if ($("#voto").val() !== "" || $("#voto").val() != null) {
                        var dato = {
                            IdCurso: $("#IdCurso").val(),
                            Voto: $("#voto").val()
                        };
                        $.ajax({
                            data: dato,
                            url: "Votar",
                            type: 'post',
                            success: function (result) {
                                if (result === 'error') {
                                    swal({
                                        type: 'error',
                                        title: 'Oops...',
                                        text: 'Porfavor vote',
                                        background: 'linear-gradient(#1c7430 , #ffffff)'
                                    });
                                }
                                else if (result === 'ok') {
                                    swal({
                                        type: 'success',
                                        title: 'Accion realizada!',
                                        showConfirmButton: false,
                                        background: 'linear-gradient(#1c7430 , #ffffff)',
                                        timer: 2150
                                    });
                                    setTimeout("window.location.replace ('Inicio.jsp');", 2000);
                                } else {
                                    swal({
                                        type: 'error',
                                        title: 'Oops...',
                                        text: 'Algo salio masl comuniquese con el administrador',
                                        background: 'linear-gradient(#1c7430 , #ffffff)'
                                    });
                                }
                            }

                        });
                    } else {
                        swal({
                            type: 'error',
                            title: 'Oops...',
                            text: 'Porfavor vote',
                            background: 'linear-gradient(#1c7430 , #ffffff)'
                        });
                    }
                } else {
                    swal({
                        type: 'error',
                        title: 'Oops...',
                        text: 'Algo salio masl comuniquese con el administrador',
                        background: 'linear-gradient(#1c7430 , #ffffff)'
                    });
                }
            }
            var v = function (e) {
                var dato ={
                    curso: e
                };
                $.ajax({
                    data: dato,
                    type: 'POST',
                    url: "Votos",
                    success: function (resultado) {
                        $("#pp").html(resultado);
                    }
                });
            };
        </script>
        <%}
            }
        %>
    </body>
</html>
