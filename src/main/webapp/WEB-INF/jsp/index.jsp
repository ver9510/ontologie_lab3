<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html lang="en">
<head>

    <%--<!-- Access the bootstrap Css like this,--%>
    <%--Spring boot will handle the resource mapping automcatically -->--%>
    <%--<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>--%>

    <%--<!----%>
    <%--<spring:url value="/css/main.css" var="springCss" />--%>
    <%--<link href="${springCss}" rel="stylesheet" />--%>
    <%---->--%>
    <%--<c:url value="/css/main.css" var="jstlCss"/>--%>
    <%--<link href="${jstlCss}" rel="stylesheet"/>--%>

</head>
<body>
<h1>Flashcards jeu</h1>

<p>${addResult}</p>
<div class="form">
    <p>${randomWord}</p>
    <form action="/sampleTranslate">
        <input id="word" name="word" type="text" value="${translatedWord}"/>
        <input id="button" type="submit" value="Répondre"/>
    </form>
    <p>${result}</p>
    <p>Résultat: ${count}</p>
</div>

<h4>Remettre un mot</h4>
<form action="/addWord">
    <p>Mot en francais <input name="frenchWord" type="text"/></p>
    <p>Mot en russe <input name="russianWord" type="text"/></p>
    <p>Genre</p>
    <input name="gender" type="radio" value="male">Masculin</input>
    <input name="gender" type="radio" value="female">Féminin</input><br>
    <p>Types de mots <select name="wordClass">
        <option value ="noun">Nom</option>
        <option value ="verb">Verbe</option>
        <option value ="adjective">Adjectif</option>
        <option value ="pronoun">Pronom</option>
        <option value ="adverb">Adverbe</option>
    </select></p>
    <input name="add" type="submit" value="Remettre">
</form>
<%--<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>

</body>

</html>