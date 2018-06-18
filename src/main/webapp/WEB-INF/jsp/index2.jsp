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
<h1>Select famous person</h1>

<form action="/">
    <h4>Find person</h4>
    <input name="personToFind" type="text" value="">
    <input name="findPerson" type="submit" value="Find person">
</form>

<h3>Sample</h3>
<form action="/findObjects">
    <p>${person.personLabel}</p>
    <p>${person.description}</p>
    <img src="${person.imageUrl}" height="300"/>
    <input name="personName" type="hidden" value="${person.personLabel}">
    <input name="personBirthDate" type="hidden" value="${person.dateOfBirth}">
    <input name="personDeathDate" type="hidden" value="${person.dateOfDeath}">
    <input name="personCountry" type="hidden" value="${person.country}"><br>
    <input name="findExponates" type="submit" value="Find suitable showpieces">
</form>


<%--<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>

</body>

</html>