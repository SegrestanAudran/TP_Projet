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
		<link rel="stylesheet" href="css/styles.css">
    </head>
	<STYLE>A {text-decoration: none; color: black;} </STYLE>
    <body>
	 <div id='cssmenu'>
<ul>
   <li class='active'><a href='#'>Accueil</a></li>
   <li><a href='#'>	Equipe</a></li>
   <li><a href='#'>Ressources</a></li>
   <li><a href='#'>Contact</a></li>
   <li><a href="Index2.html" class="sansLabel">Deconnexion</a> </li>
</ul>
	<h1> Mes commandes </h1>
	<p>${sessionscope.userName}</p>
	<form action="<c:url value="CommandController" />" method="POST">
	<!--<input type="submit" value="Ajouter une commande" class="firstbouton" /><br/>-->
	<button type="button" class="btn btn-primary" id="addCommand">Ajouter une commande</button><br/><br/>
		<table class="table table-sm table-bordered" style="width:70%;margin-left:auto;margin-right:auto"> 
			<thead class="thead-dark">
			<tr> 
					
				<td> Numero de commande </td>
				<td> Produit </td>
				<td> Quantité </td>
				<td> Prix </td>
                                <td> Date d'achat</td>
                                <td> Date d'envoie</td>
				<td> Modifier </td>
				<td> Suprimmer </td>
				
			</tr> 
                       
                        <c:forEach var="Cmd" items="${Commande}" >
                            <tr>
                                <td> ${Cmd.getOrder_num()}</td>
                                <td> ${Cmd.getId_produit()}</td>
                                <td> ${Cmd.getQuantite()}</td>
                                <td> ${Cmd.getFrais()}</td>
                                <td> ${Cmd.getDate_achat()}</td>
                                <td> ${Cmd.getDate_envoi()}</td>
                                <td><button type="button" class="btn btn-dark" id="secondBouton">Modifier</button></td>
                                <td><button type="button" class="btn btn-dark" id="thirdBouton">Supprimer</button></td>
                            </tr>
                        </c:forEach>
			
		</table> 
		

	</body>
</html>