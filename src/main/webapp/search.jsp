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
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="scripts/messages.js" defer></script>
</head>

<body>

<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Search"/>
    </jsp:include>


    <main>
        <c:if test="${notAuthorized != null}">
            <p class="alert-danger" id="alert-danger"><c:out value="${notAuthorized}"/></p>
        </c:if>

        <%--<c:if test="${not empty errorr}">
            <p class="alert-danger" id="alert-danger"><c:out value="${errorr}"/></p>
        </c:if>--%>
        <c:if test="${not empty error}">
            <div class="alert-danger">

                <c:forEach var="error" items="${error}">
                    <ul>
                        <li><c:out value="${error}"/></li>
                    </ul>
                </c:forEach>


            </div>

        </c:if>


        <c:if test="${empty errorr}">
            <h3>Your last positve test was on <c:out value="${positiveTest.date}"/></h3>


        </c:if>

        <c:if test="${not empty error2}">
            <div class="alert-danger">
                <ul>
                    <li><c:out value="${error2}"/></li>
                </ul>
            </div>
        </c:if>


        <c:if test="${empty error2}">
            <h2>here are all your contacts since your last positive test</h2>
            <table id="MyTable2" role="table">
                <thead role="rowgroup">
                <tr role="row">
                    <th role="columnheader">Name</th>
                    <th role="columnheader">Gsm</th>
                    <th role="columnheader">E-mail</th>
                </tr>
                </thead>


                <c:forEach var="visitor" items="${visitors}">
                    <tbody role="rowgroup">
                    <tr id="mySecondTr" role="row">
                        <td role="cell"><c:out value="${visitor.firstName}"/> <c:out value="${visitor.lastName}"/></td>
                        <td role="cell"><c:out value="${visitor.phoneNumber}"/></td>
                        <td role="cell"><c:out value="${visitor.email}"/></td>

                    </tr>
                    </tbody>
                </c:forEach>


            </table>

        </c:if>


    </main>
    <footer>
        &copy;Lennert Van Oosterwyck
    </footer>


</div>

</body>
</html>