<%@page import="Entidades.BCNIIFLUJ"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Alta de Contrato</title>
    
    <link rel="stylesheet" href="estilos/style.css">
    
    <link rel="stylesheet" href="fontawesome/css/all.css">
    <!-- ANIMATE CSS Es el saltito que pega cuando carga la 1er pantallas-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
    
</head>

<body>


    <div class="content">

        <h1 class="logo">Alta de <span>Contrato</span></h1>

        <div class="contact-wrapper animated bounceInUp  ">
            <div class="contact-form">
                <h3>Transaccion Alta</h3>
                <form action="Alta_Datos_TXT_Servlet" method="POST" enctype="multipart/form-data" >
               
                <!--<form autocomplete="off" action="Alta_Datos_TXT_Servlet" method="POST" enctype="multipart/form-data" >-->
                    <p>
                        <label for="idAgrupador">Id Agrupador: </label>
                        <input type="text" id="idAgrupador" name="idAgrupador">
                    </p>

                    <p>
                        <label for="tipoContr">Tipo de Contrato: </label>
                        <input type="text" id="tipoContr" name="tipoContr">
                    </p>

                    <p>
                        <label for="descrip">Descripcion: </label>
                        <input type="text" id="descrip" name="descrip">
                    </p>
                    <p>
                        <label for="proveed">Proveedor: </label>
                        <input type="text" id="proveed" name="proveed">
                    </p>


                    <p>
                        <label for="moned">Moneda: </label>
                        <input type="text" id="moned" name="moned">
                    </p>
                    <p>
                        <label for="tipCamb">Tipo de Cambio: </label>
                        <input type="text" id="tipCamb" name="tipCamb">
                    </p>
                    <p>
                        <label for="tasaDesc">Tasa de Descuento $: </label>
                        <input type="text" id="tasaDesc" name="tasaDesc">
                    </p>
                    <p>
                        <label for="tasaDescDol">Tasa de Descuento U$S: </label>
                        <input type="text" id="tasaDescDol" name="tasaDescDol">
                    </p>
                    <p>
                        <label for="fechAlta">Fecha de Alta: </label>
                        <input type="date" id="fechAlta" name="fechAlta">
                    </p>
                    <p>
                        <label for="fechCort">Fecha de Corte: </label>
                        <input type="date" id="fechCort" name="fechCort">
                    </p>
                    
                    <p>
                        <input type="file" name="file" id="file" hidden />
                        <label for="file" id="selector">Select File</label>
                        <script src="./JS/file.js" ></script>
                    </p>

                    <p class="active" type="submit">
                        <button>
                            Cargar Flujo
                        </button>
                    </p>
                </form>
               

            </div>
            <div class="contact-info">
                <h4>More Info</h4>
                <ul>
                    <li><i class="fas fa-map-marker-alt"></i> Reconquista 823 - Piso 3 - CABA</li>
                    <li><i class="fas fa-phone"></i> Tel. 54-11-5559-8300</li>
                    <li><i class="fas fa-envelope-open-text"></i> contact@website.com</li>
                </ul>
                <p>Arrendamiento NIIF - 16</p>
                <p>Banco Comafi SA</p>
            </div>
        </div>

    </div>


</body>

</html>
