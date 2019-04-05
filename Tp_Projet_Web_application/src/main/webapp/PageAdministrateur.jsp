<%-- 
    Document   : PageAdministrateur
    Created on : 5 avr. 2019, 15:24:17
    Author     : Yasmina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="javascript/chart.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Les graphiques</title>
    </head>
    <body>
        <h1>Les graphiques de Chiffre d'Affaires</h1>
        <form method="post" action="adminAction">
            <label for="Choix">Chiffre d'affaire par :</label>
            <select  name="choixSelect" id="choixSelect">
                
                <option id='1'>Catégorie</option>
                <option id='2'>Zone Géographique</option>
                <option id='3'>Client</option>
            </select><br>
            <label for="Date_debut">Date de début :</label>
            <input type="date" name="Date_debut" id="Date_debut">
            <label for="Date_fin">Date de fin :</label>
             <input type="date" name="Date_fin" id="Date_fin">
            
            
                <input type="submit" value="Voir mon graphique">
                
                 <div id="piechart" style="width: 900px; height: 500px;"></div>
        </form>
        
    </body>
</html>
