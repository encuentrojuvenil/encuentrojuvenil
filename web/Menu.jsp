<%-- 
    Document   : Menu
    Created on : 16/10/2019, 06:26:31 PM
    Author     : Lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
        <link href="CSS/bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="CSS/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="CSS/Menu.css" rel="stylesheet">
    </head>
    <body>
        <%
            String usuario = null;
            if ((String) session.getAttribute("Usuario") != null) {
                usuario = (String) session.getAttribute("Usuario");
            }
            String nombre = null;
            if ((String) session.getAttribute("Nombre") != null) {
                nombre = (String) session.getAttribute("Nombre");
            }
            String rol = null;
            if ((String) session.getAttribute("Nrol") != null) {
                rol = (String) session.getAttribute("Nrol");
            }
            if (rol != null) {
                if (rol.equals("2") || rol.equals("1")) {
        %>            
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="Inicio.jsp"<h3>Inicio</h3></a>
            <button  class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>            
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="Inicio.jsp"><h5>Inicio</h5></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Img.jsp"><h5>Imagen</h5></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="index.html" id="salir"><h5>Salir</h5></a>
                    </li>
                </ul>
                <h1 class="nombreu" ><%=nombre%></h1                
                <form class="form-inline my-2 my-lg-0">
                    <img id="logo" src="Imagen/logo.jpeg">
                </form>
            </div>



        </nav>
        <% }
        } else {

        %>
        <div class="container n">
            <label><h4>Porfavor dale click aqui ya que la cuenta se ha cerrado <a href="index.html">Inicio de la pagina</a></h4></label>
        </div>
        <% }%>
        <script src="JQuery/JQuery.js"></script>
        <script src="JQuery/Alertas.js"></script>
        <script src="CSS/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            $("#salir").click(function () {
                $.ajax({
                    type: 'POST',
                    url: "Salir",
                    success: function (resultado) {
                        window.location.replace('index.html');
                    }
                });
            });


        </script>
    </body>
</html>
