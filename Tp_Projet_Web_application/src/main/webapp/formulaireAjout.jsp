<%@page import="Tp_Projet.ProductEntity"%>
<%-- 
    Document   : formulaireAjout
    Created on : 5 avr. 2019, 09:20:39
    Author     : Yasmina
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/styleAjoutCommande.css">
        <link rel="stylesheet" href="css/NouveauStyleBarreNavigation.css">
        <title>Ajouter une commande</title>
    </head>
    <body>
        <ul>
            <li><a href="#home">Home</a></li>
            <li><a href="#news">News</a></li>
            <li><a href="#contact">Contact</a></li>
            <li style="float:right"><a class="active" href="#about">Deconnexion</a></li>
        </ul>
        <h1 id="AJ" style="margin-top: 50px;">Ajouter une commande</h1>

        <form action="<c:url value="CommandController" />" method="POST">
            <fieldset style=" margin-left: auto; margin-right: auto;">
                <label for="name_product">Choisir le produit :</label>
                <select  name="name_product" id="name_product">
                    <c:forEach var="Pr" items="${Prod}" >
                        <option name="produit"> ${Pr.getName()} </option>
                    </c:forEach>
                </select><br><br>
                <label for="quantity">Nombre de produit :</label>
                <SELECT name="quantity" size="1">
                    <% for (int i = 1; i <= 150; i++) {%>
                    <option name="quantity_choix"> 
                     <%out.print(i); %>
                    </option>
                       <% }%>
                </SELECT>
                <br><br>
                <!--<input type="text" name="quantity" id="quantity"><br><br>-->
                <label for="name_company">Choisir la compagnie :</label>
                <select  name="name_company" id="name_company">
                    <c:forEach var="c" items="${Company}" >
                        <option name="company"> ${c} </option>
                    </c:forEach>
                </select>
                <br><br>
                <input type="submit" name="action" class="btn btn-dark" id="graph" value="Enregistrer ma commande">
            </fieldset>
        </form>
    </body>
</html>
