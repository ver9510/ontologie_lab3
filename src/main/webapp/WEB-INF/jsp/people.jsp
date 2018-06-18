<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html lang="en">
<head>

</head>
<body>
<a href="/">Back to index</a>
<h1>View search result</h1>
<h1>There are list of people who lived in ${year}</h1>
<table>
    <tr>
        <c:forEach items="${foundPeople}" var="person">
            <td>
                <table>
                    <tr>
                        <td> ${person.personLabel}</td>
                    </tr>
                    <tr>
                        <td>${person.dateOfBirth} - ${person.dateOfDeath}</td>
                    </tr>
                    <tr>
                        <td><img src="${person.imageUrl}" height="300"/></td>
                    </tr>
                    <tr>
                        <td>Description: ${person.description}</td>
                    </tr>
                    <tr>
                        <td>
                            <form action="/findObjects">
                                <input name="personName" type="hidden" value="${person.personLabel}">
                                <input name="personBirthDate" type="hidden" value="${person.dateOfBirth}">
                                <input name="personDeathDate" type="hidden" value="${person.dateOfDeath}">
                                <input name="personCountry" type="hidden" value="${person.country}">
                                <input name="findExponates" type="submit" value="Find suitable showpieces">
                            </form>
                        </td>
                    </tr>
                </table>
            </td>

        </c:forEach>
    </tr>
</table>
</body>

</html>