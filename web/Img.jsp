<%-- 
    Document   : Img
    Created on : 16/10/2019, 11:22:36 PM
    Author     : Lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="CSS/bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="CSS/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="CSS/Img.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="Menu.jsp" %>
        <% String id = (String) session.getAttribute("Id");
            if (rol != null) {
                if (rol.equals("2") || rol.equals("1")) {
        %>
        <div class="container">
            <input id="Usua" type="hidden" value="<%=id%>">
            <form enctype="multipart/form-data" id="formuploadajax"  method="post"   >  
                <button class="btn-masscota"  id="btnFile">                                   
                    <input type="file" id="fotomascota" name="fotomascota" name="fotomascota" >
                    
                </button>    
            </form>
            <button class="btn btn-success" id="imagen">Subir foto</button>
        </div>
        <script src="JQuery/JQuery.js"></script>
        <script src="JQuery/Alertas.js"></script>
        <script src="CSS/bootstrap/js/bootstrap.min.js"></script>
        <script src="CSS/bootstrap/js/bootstrap.js"></script>
        <script type="text/javascript">
            $("#imagen").click(function(){
                var formData = new FormData(document.getElementById('formuploadajax'));
                $.ajax({
                    url: "Imagen",
                    type: "post",
                    data: formData,
                    contentType: false,
                    cache: false,
                    processData: false,
                    success: function (resultado) {
                        if (resultado === "ok") {
                            swal({
                                type: 'success',
                                title: 'ACCION COMPLETADA!',
                                showConfirmButton: false,
                                background: 'linear-gradient(#1c7430 , #ffffff)',
                                timer: 2150
                            });
                        }else if (resultado === "error2") {
                           swal({
                                type: 'error',
                                title: 'No permitido',
                                text: 'No tiene permiso de subir imagen',
                                background: 'linear-gradient(#1c7430 , #ffffff)'
                            });
                        }else if (resultado === "error3") {
                           swal({
                                type: 'error',
                                title: 'Imagen ya ha subida',
                                text: 'La imagen ya ha sido subida',
                                background: 'linear-gradient(#1c7430 , #ffffff)'
                            });                           
                            
                        } else {
                            swal({
                                type: 'error',
                                title: 'Oops...',
                                text: 'Algo salio mal comuniquese con el administrador',
                                background: 'linear-gradient(#1c7430 , #ffffff)'
                            });
                        }
                    }
                });
            });
        </script>
        <% }
            }%>
    </body>
</html>
