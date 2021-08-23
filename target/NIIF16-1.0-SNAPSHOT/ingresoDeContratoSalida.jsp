<%@page import="Entidades.BCNIIFALT"%>
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
    <!-- GOOGLE FONTs -->
    <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
    <link rel="stylesheet" href="estilos/styleSalida.css">
    
    <!-- FONT AWESOME -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
        integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
     <!-- ANIMATE CSS Se saca esta animacion para evitar el salto-->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
 
</head>

<body>


    <div class="content">

        <h1 class="logo">Alta de <span>Contrato</span></h1>
<!--Le sacamos el bounceInUp para evitar el saltito-->
        <div class="contact-wrapper animated ">
            <div class="contact-form">
                <div class="titulo">
                <h4 >Transaccion Alta </h3>
                <button class="btn btn-primary" type="button" data-toggle="modal">APROBAR FLUJO</button>
               </div>
      <%  
          
//          if(request.getAttribute("flujo")!=null){
           HttpSession sess = request.getSession();
           if(sess==null){
          System.out.println("NO HAY SESION");
      } else{
           
           out.print("<table border=5>"+
                    "<thead>"+
                        "<tr>"+
                            "<th>ID</th>"+
                            "<th>IdAgrupador</th>"+
                            "<th>Descripcion</th>"+
                            "<th>Proveedor</th>"+
                            "<th>FechaDesde</th>"+
                            "<th>FechaHasta</th>"+
                            "<th>Moneda</th>"+
                            "<th>ImpMonOrig</th>"+
                            "<th>FechaCorte</th>"+
                            "<th>DiasFuturos</th>"+
                            "<th>ImpDesc</th>"+
                            "<th>Saldo</th>"+
                            "<th>Intereses</th>"+
                            "<th>Amortizacion</th>"+
                            "<th>TIR</th>"+
                            
                        "</tr>"+
                    "</thead>"+
                    "<tbody>");
       
    ArrayList<BCNIIFLUJ> flujo = (ArrayList<BCNIIFLUJ>)sess.getAttribute("flujo");
        if(flujo==null)
        {flujo=new ArrayList<BCNIIFLUJ>(); 
        }else{
        out.print("<tr>");  
            out.print("<td>" + flujo.get(0).getId() +"</td>");
    
            
            out.print("<td>" + flujo.get(0).getIdAgrupador() +"</td>");
            out.print("<td>" + flujo.get(0).getDescriptn()+"</td>");
            out.print("<td>" + flujo.get(0).getProveedor() +"</td>");
            out.print("<td>" +"</td>");
            out.print("<td>" + flujo.get(0).getFechCorte() +"</td>");
            out.print("<td>" + flujo.get(0).getMoneda()+"</td>");
            out.print("<td>" +"</td>");
            out.print("<td>" +"</td>");
            out.print("<td>" + "</td>");
            out.print("<td>" + "</td>");
            out.print("<td>" + "</td>");
            out.print("<td>" + "</td>");
            out.print("<td>" + "</td>");
            out.print("<td>" + String.format("%.3f", flujo.get(0).SALDO) +"</td>");
            out.print("</tr>");
  
    for (int i = 0; i < flujo.size(); i++) {
            BCNIIFLUJ flujoAux = flujo.get(i);
            out.print("<tr>");  
            out.print("<td>" + flujoAux.getId() +"</td>");
            out.print("<td>" + flujoAux.getIdAgrupador() +"</td>");
            out.print("<td>" + flujoAux.getDescriptn()+"</td>");
            out.print("<td>" + flujoAux.getProveedor() +"</td>");
            out.print("<td>" + flujoAux.getFechDesde()+"</td>");
            out.print("<td>" + flujoAux.getFechHasta() +"</td>");
            out.print("<td>" + flujoAux.getMoneda() +"</td>");
            out.print("<td>" + String.format("%.3f", flujoAux.getImpMonOrig())+"</td>");
            out.print("<td>" + flujoAux.getFechCorte() +"</td>");
            out.print("<td>" + flujoAux.getDiasFuturos() +"</td>");
            out.print("<td>" + String.format("%.3f", flujoAux.getImporDesc()) +"</td>");
            out.print("<td>" + String.format("%.3f", flujoAux.getSaldo()) +"</td>");
            out.print("<td>" + String.format("%.3f", flujoAux.getIntereses())+"</td>");
            out.print("<td>" + String.format("%.2f", flujoAux.getAmortizac())+"</td>");
            out.print("<td>" + String.format("%.2f", flujoAux.getTirCuota())  +"</td>");
            out.print("</tr>");


                            
          }  
           }
           }
      %>
                      
                           
                    </tbody>
                </table>

                

            </div>
            <div class="contact-info">
                <h4>Datos Alta</h4>
                <ul>
                     <li><i class="fas">Fecha de Alta:  </i>
                            <% 
                          BCNIIFALT b1 = (BCNIIFALT)sess.getAttribute("flujoAlta");        
                          if(b1==null){
                              b1 = new BCNIIFALT();
                          }else{
                          out.print(b1.getFechAlta());
                              
                          }
                            %>
                            </li> 
                    <li><i class="fas ">Tipo de Cambio:   </i> 
                        <% out.print(b1.getTipCamb()); 
                        %>
                    </li>
                    <li><i class="fas">Tasa de Desc Pesos: </i> 
                        <% 
                        out.print(b1.getTasDescPes());
                    %>
                    </li>
                    <li><i class="fas">Tasa de Desc Dolares:</i>
                        <% 
                            if(b1==null){
                                System.out.println("Es nulo");
                            }else{
                        out.print(b1.getTasDescDol()); 
                                
                            }
                        %>
                    </li>
  
                </ul>
                    <br>
                <p>Arrendamiento NIIF-16</p>
            </div>
        </div>

    </div>

  


</body>

</html>
