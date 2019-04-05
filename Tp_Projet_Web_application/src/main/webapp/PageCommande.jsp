<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Commandes</title>
        <link type="text/css" rel="stylesheet" href="CSS\tableformat.css" />
    </head>
	<STYLE>A {text-decoration: none; color: black;} </STYLE>
    <body>
	 <nav class="barAffiche">
            <ol>
                <li class="a2"><a href=""> Accueil </a></li>
				<li class="a2"><a href=""> Votre compte </a></li>
				<li class="a2"><a href=""> Deconnection </a></li>
	<h1> Mes commandes </h1>
	<input type="submit" value="Ajouter une commande" class="firstbouton" />
		<table> 
			<tr> 
					
				<td> Numero de commande </td>
				<td> Produit </td>
				<td> Quantité </td>
				<td> Prix </td>
				<td> Modifier </td>
				<td> Suprimmer </td>
				
			</tr> 
                        <% for (OrderEntity v :Commande> {>
                            out.print("<tr>");
                                out.print("<td> commande.getNum() </td>");
                                out.print("<td> v.getNumero() </td>");
                                out.print("<td> v.getProduit() </td>");
                                out.print("<td> v.getQuantite()  </td>");
                                out.print("<td> <input type='submit' value='Modifier' class='secondbouton' /> </td>");
                                out.print("<td> <input type='submit' value='Supprimer' class='thirdbouton' /> </td>");
                            out.print("</tr>");
                        <% } %>
<!--			<tr> 
			<td> 2 </td>
			<td>  </td>
			<td>  </td>
			<td>  </td>
			<td> <input type="submit" value="Modifier" class="secondbouton" /> </td>
			<td> <input type="submit" value="Supprimer" class="thirdbouton" /> </td>
			</tr>		
			-->
		</table> 
		

	</body>
</html>