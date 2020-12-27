<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add Positive Test</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>

<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Add Positive Test"/>
    </jsp:include>


    <main>

        <c:if test="${notAuthorized != null}">
            <div class="alert-danger">
                <ul>
                    <li><c:out value="notAuthorized"/></li>
                </ul>
            </div>
        </c:if>


        <c:if test="${not empty errors}">
            <div class="alert-danger" id="alert-danger">

                <c:forEach var="error" items="${errors}">
                    <ul>
                        <li><c:out value="${error}"/></li>
                    </ul>
                </c:forEach>


            </div>

        </c:if>
        <c:if test="${not empty success}">
            <div class="alert-success">

                <ul>
                    <li><c:out value="${success}"/></li>
                </ul>

            </div>

        </c:if>

        <form method="post" action="Controller?command=AddPositiveTest" novalidate="novalidate">
            <p><label for="date">Date</label><input type="date" id="date" name="date"
            <c:out value="${param.date}"/> required></p>

            <p><input type="submit" id="addPositiveTest" value="Covid-19 positive"></p>

        </form>


    </main>
    <footer>
        &copy;Lennert Van Oosterwyck
    </footer>


</div>

</body>
</html>