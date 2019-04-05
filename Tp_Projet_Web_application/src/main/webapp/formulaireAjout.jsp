<%-- 
    Document   : formulaireAjout
    Created on : 5 avr. 2019, 09:20:39
    Author     : Yasmina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ajouter une commande</h1>
        
        <form method="post" action="traitement">
            <label for="name_product">Choisir le produit :</label>
            <select  name="name_product" id="name_product">
                <c:forEach var="Pr" items="${Produit}" >
                     out.print("<option id=${Pr.get()} > ${Cmd.getOrder_num()} </option>");
                    
                <option></option>
                
                </c:forEach>
            </select><br>
                <label for="quantity">Nombre de produit :</label>
                <input type="text" name="quantity" id="quantity"><br><br>
        
                <input type="submit" value="Enregistrer ma commande">
        </form>
    </body>
</html>
