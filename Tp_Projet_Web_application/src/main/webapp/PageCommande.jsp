<%@page import="Tp_Projet.OrderEntity"%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="utf-8" />
        <title>Commandes</title>
        <!-- <link type="text/css" rel="stylesheet" href="css/form.css" />-->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/StylePageCommande.css">
        <link rel="stylesheet" href="css/NouveauStyleBarreNavigation.css">
    </head>
    <STYLE>A {text-decoration: none; color: black;} </STYLE>
    <body>
        <ul>
            <li><a href="#home">Home</a></li>
            <li><a href="#news">News</a></li>
            <li><a href="#contact">Contact</a></li>
            <li style="float:right"><a name="action" value="logout" class="active" href="PageConnection.jsp">Deconnexion</a></li>
        </ul>
        <h1> Mes commandes </h1>
        <p>${sessionscope.userName}</p>
        <form action="<c:url value="CommandController" />" method="POST">
            <!--<input type="submit" value="Ajouter une commande" class="firstbouton" /><br/>-->
            


<div style="text-align:center;">
            <input type="submit" class="btn btn-primary" id="addCommand" name="action" value ="Ajouter une commande" ><br/><br/>
</div>
        </form>	
        <table class="table table-sm table-bordered" style="width:70%;margin-left:auto;margin-right:auto"> 
            <thead class="thead-dark">
                <tr> 

                    <td> Numero de commande </td>
                    <td> Produit </td>
                    <td> Quantité </td>
                    <td> Prix </td>
                    <td> Date d'achat</td>
                    <td> Date d'envoi</td>
                    <td></td>
                    <td></td>

                </tr> 

                <c:forEach var="Cmd" items="${Commande}" >
                <form action="<c:url value="CommandController" />" method="POST">
                    <tr>
                        <td> ${Cmd.getOrder_num()}</td>
                        <td> ${Cmd.getId_produit()}</td>
                        <td> ${Cmd.getQuantite()}</td>
                        <td> ${Cmd.getFrais()}</td>
                        <td> ${Cmd.getDate_achat()}</td>
                        <td> ${Cmd.getDate_envoi()}</td>
                    <input type="hidden" name="Order_num" value="${Cmd.getOrder_num()}">
                    <input type="hidden" name="quantite" value="${Cmd.getQuantite()}">
                    <td><input type="submit" class="btn btn-dark" id="secondBouton" name="action" value="Modifier"></td>
                    <td><input type="submit" class="btn btn-dark" id="thirdBouton" name="action" value="Supprimer"></td>
                    </tr>
                </form>
            </c:forEach>

        </table> 


    </body>
</html>