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
<table>
    <tr>
    <c:forEach items="${foundDresses}" var="dress">

            <td>
                <table>
                    <tr>
                        <td>Title: ${dress.name}</td>
                    </tr>
                    <tr>
                        <td>Period: ${dress.year}</td>
                    </tr>
                    <tr>
                        <td><img src="${dress.imageUrl}" height="300"/></td>
                    </tr>
                    <tr>
                        <td>Description: ${dress.summary}</td>
                    </tr>
                    <tr>
                        <td>
                            <form>
                                <input name="dressYear" type="hidden" value="${dress.year}">
                                <input name="dressCountry" type="hidden" value="${dress.country}">
                                <input name="dressObjectNumber" type="hidden" value="${dress.objectNumber}">
                                <input type="submit" value="find more"/>
                            </form>
                        </td>
                        <td>
                            <form action="/findPeopleOfThatPeriod">
                                <input name="dressYear" type="hidden" value="${dress.year}">
                                <input name="dressCountry" type="hidden" value="${dress.country}">
                                <input name="dressObjectNumber" type="hidden" value="${dress.objectNumber}">
                                <input type="submit" value="find people"/>
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