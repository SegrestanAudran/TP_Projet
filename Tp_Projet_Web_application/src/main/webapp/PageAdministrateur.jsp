<%-- 
    Document   : PageAdministrateur
    Created on : 5 avr. 2019, 15:24:17
    Author     : Yasmina
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <!--<script type="text/javascript" src="javascript/chart.js"></script>-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />
        <link type="text/css" rel="stylesheet" href="css/StylePageAdministrateur.css" />
        <link rel="stylesheet" href="css/NouveauStyleBarreNavigation.css">
        <title>Les graphiques</title>
    </head>
    <body>
      <STYLE>A {text-decoration: none; color: black;} </STYLE>  
       <form method="POST" action="<c:url value="AdminController" />">  
      <ul>
  <li><a href="#home">Home</a></li>
  <li><a href="#news">News</a></li>
  <li><a href="#contact">Contact</a></li>
  <li style="float:right"><a name="action" value="logout" class="active" href="PageConnection.jsp">Deconnexion</a></li>
</ul>
        <h1 id="CA">Les graphiques de Chiffre d'Affaires</h1>
       
            <fieldset>
            <label for="Choix">Chiffre d'affaire par :</label>
            <select  name="choixSelect" id="choixSelect">
                <option id='1'>Categorie</option>
                <option id='2'>Zone Geographique</option>
                <option id='3'>Client</option>
            </select><br>
            <label for="Date_debut">Date de début :</label>
            <input type="date" name="Date_debut" id="Date_debut" value="2011-01-01">
            <label for="Date_fin">Date de fin :</label>
            <input type="date" name="Date_fin" id="Date_fin" value=${today} >
            <input type="submit" name="action" value="Voir mon graphique" id="affgraph">
            </fieldset>
            <div id="piechart" style="width: 900px; height: 500px;"></div>
        </form>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <!-- On charge l'API Google -->
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
            document.getElementById('Date_fin').value = new Date().toISOString().slice(0, 10);
            google.load("visualization", "1", {packages: ["corechart"]});

            // Après le chargement de la page, on fait l'appel AJAX
            google.setOnLoadCallback(doAjax);

            function drawChart(dataArray) {
                var data = google.visualization.arrayToDataTable(dataArray);
                var options = {
                    title: 'Ventes par client',
                    is3D: true
                };
                var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                chart.draw(data, options);
            }

            // Afficher les ventes par client
            function doAjax() {
                $.ajax({
                    url: "salesByCustomer",
                    dataType: "json",
                    success: // La fonction qui traite les résultats
                            function (result) {
                                // On reformate le résultat comme un tableau
                                var chartData = [];
                                // On met le descriptif des données
                                chartData.push(["Client", "Ventes"]);
                                for (var client in result.records) {
                                    chartData.push([client, result.records[client]]);
                                }
                                // On dessine le graphique
                                drawChart(chartData);
                            },
                    error: showError
                });
            }

            // Fonction qui traite les erreurs de la requête
            function showError(xhr, status, message) {
                alert("Erreur: " + status + " : " + message);
            }

        </script>
    </body>
</html>
