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
        <title>Modifier une commande</title>
    </head>
    <body>
        <h1>Modifier une commande</h1>

        <form action="<c:url value="CommandController" />" method="POST">
            <label for="name_product">Choisir le produit :</label>
            <select  name="name_product" id="name_product">
                
                    <option name="produit"> ${Pr.getName()} </option>
                
            </select><br>
            <label for="quantity">Nombre de produit :</label>
            <input type="text" name="quantity" id="quantity"><br><br>
            <label for="name_company">Choisir la compagnie :</label>
            <select  name="name_company" id="name_company">
               
                    <option name="company"> ${c} </option>
                
            </select><br>



            <input type="submit" name="action" value="Modifier ma commande">
        </form>
    </body>
</html>

