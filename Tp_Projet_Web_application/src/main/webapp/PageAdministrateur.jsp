<%-- 
    Document   : PageAdministrateur
    Created on : 5 avr. 2019, 15:24:17
    Author     : Yasmina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <!--<script type="text/javascript" src="javascript/chart.js"></script>-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Les graphiques</title>
    </head>
    <body>
        <h1>Les graphiques de Chiffre d'Affaires</h1>
        <form method="POST" action="<c:url value="AdminController" />">
            <label for="Choix">Chiffre d'affaire par :</label>
            <select  name="choixSelect" id="choixSelect">
                <option id='1'>Catégorie</option>
                <option id='2'>Zone Géographique</option>
                <option id='3'>Client</option>
            </select><br>
            <label for="Date_debut">Date de début :</label>
            <input type="date" name="adminAction" id="Date_debut">
            <label for="Date_fin">Date de fin :</label>
            <input type="date" name="adminAction" id="Date_fin">
            <input type="submit" name="adminAction" value="Voir mon graphique" id="affgraph">
            <div id="piechart" style="width: 900px; height: 500px;"></div>
        </form>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<!-- On charge l'API Google -->
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
        
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
						for(var client in result.records) {
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
