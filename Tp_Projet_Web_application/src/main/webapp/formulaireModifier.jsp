<%@page import="Tp_Projet.ProductEntity"%>
<%@page import="Tp_Projet.OrderEntity"%>
<%-- 
    Document   : formulaireModifier
    Created on : 12 avr. 2019, 16:15:56
    Author     : Yasmina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/StyleModifierCommande.css">
        <link rel="stylesheet" href="css/NouveauStyleBarreNavigation.css">
        <title>Modifier une commande</title>
    </head>
    <body>
        <ul>
            <li><a href="#home">Home</a></li>
            <li><a href="#news">News</a></li>
            <li><a href="#contact">Contact</a></li>
            <li style="float:right"><a class="active" href="#about">Deconnexion</a></li>
        </ul>

        <h1 id="MC">Modifier une commande</h1>

        <form action="<c:url value="CommandController" />" method="POST">
            <fieldset style="margin-left: auto;margin-right: auto;">
                <label for="name_product" ><span style="font-weight: bold;">Le Numéro de commande :</span> ${Order.getOrder_num()}</label><br>
                <input type="hidden" name="Order_num" value=${Order.getOrder_num()}>
                <label for="name_product"><span style="font-weight: bold;">Le produit :</span> ${name_product} </label><br>

                <label for="quantity" style="font-weight: bold;">Nombre de produit :</label>
                <!--<input type="text" name="quantity" value=${Order.getQuantite()} ><br><br>-->
                <SELECT name="quantity" size="1">
                    <% for (int i = 1; i <= 150; i++) {%>
                    <option name="quantity"
                            <c:if test = "${Order.getQuantite() == i}">
                                selected="selected"
                            </c:if>
                            ><%out.print(i); %></option>
                    <% }%>
                </SELECT><br>
                <label for="name_company" style="font-weight: bold;">Choisir la compagnie :</label>
                <select  name="name_company" id="name_company">
                    <c:forEach var="Pr" items="${compagnie}" >
                        <option name="produit"
                                <c:if test = "${Order.getCompagnie() == Pr}">
                                    selected="selected"
                                </c:if>
                                > ${Pr} </option>
                    </c:forEach>
                </select><br>
                <label for="frais"><span style="font-weight: bold;">Le frais :</span> ${Order.getFrais()}</label><br>
                <label for="achat"><span style="font-weight: bold;">La date d'achat :</span> ${Order.getDate_envoi()} </label><br>
                <label for="envoi"><span style="font-weight: bold;">La date d'envoi :</span> ${Order.getDate_achat()} </label><br>

                <input type="submit" name="action" value="Modifier ma commande" class="btn btn-dark">
            </fieldset>
        </form>
    </body>
</html>

