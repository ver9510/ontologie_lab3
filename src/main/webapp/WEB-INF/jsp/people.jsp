<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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
<a href="/">Back to index</a>
<h1>View search result</h1>
<h1>People who lived in ${year}</h1>
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
                                <input name="personCountry" type="text" value="${person.country}">
                                <input name="findExponates" type="submit" value="Find suitable showpieces">
                            </form>
                        </td>
                    </tr>
                </table>
            </td>

        </c:forEach>
    </tr>
</table>
<%--<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>

</body>

</html>