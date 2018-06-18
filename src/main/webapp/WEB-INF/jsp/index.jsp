<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
</head>
<body>
<div align="center">
    <h1>Costume finder</h1>
    <p>
    <h2>Find out what costumes from collection of British Museum of Victoria and Albert
        famous people could wear? </h2>
    </p>
</div>
<div align="center">
    <h3>Start with selecting famous person</h3>
    <form action="/">
        <h4>Find person
            <input name="personToFind" type="text" value="">
            <input name="findPerson" type="submit" value="Find person"></h4>
    </form>

    <h3>.. or use this sample</h3>

    <form action="/findObjects">
        <c:if test="${error!=true}">
            <p>${person.personLabel}</p>
            <p>${person.description}</p>
            <img src="${person.imageUrl}" height="300"/>
            <input name="personName" type="hidden" value="${person.personLabel}">
            <input name="personBirthDate" type="hidden" value="${person.dateOfBirth}">
            <input name="personDeathDate" type="hidden" value="${person.dateOfDeath}">
            <input name="personCountry" type="hidden" value="${person.country}"><br>
            <input name="findExponates" type="submit" value="Find suitable showpieces">
        </c:if>
        <c:if test="${error==true}">
            <h3>Nobody found!</h3>
        </c:if>

    </form>
</div>
</body>

</html>