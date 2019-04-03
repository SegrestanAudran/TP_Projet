<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
        <%--<link type="text/css" rel="stylesheet" href="form.css" /> --%>
    </head>
    <body>
        <div>
            <fieldset>
                <legend>Connexion</legend>
                <form action="<c:url value="inscription" />" method="POST"> <!-- l'action par défaut est l'URL courant, qui va rappeler la servlet -->

                    <%-- <span class="title">Vous pouvez vous connecter via ce formulaire.</span> --%>

                    <label for="email">Adresse email <span class="requis">*</span></label>
                    <input type='text' id='email' name='email' value="" size='20'  maxlength="60" />
                    <br>

                    <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                    <input type='password' id='motdepasse' name='motdepasse' value="" size="20" maxlength="20" />
                    <br>

                    <input type="submit" value="Connexion" class="sansLabel" />
                    <br>
                </form>
            </fieldset>
        </div>
    </body>
</html>