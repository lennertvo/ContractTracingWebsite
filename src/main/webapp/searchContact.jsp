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
    <title>Add Visitor</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Add Visitor"/>
    </jsp:include>


    <h2>
        Leave your personal data here
    </h2>
    <main>
        <c:if test="${not empty errors}">
            <div class="alert-danger">

                <c:forEach var="error" items="${errors}" >
                    <ul>
                        <li>${error}</li>
                    </ul>
                </c:forEach>


            </div>

        </c:if>

        <form method="post" action="Controller?command=SearchVisitor" novalidate="novalidate">
            <p><label for="firstName">Fistname</label><input type="text" id="firstName" name="firstName" required></p>
            <p><label for="lastName">Lastname</label><input type="text" id="lastName" name="lastName" required></p>
        </form>
        <c:if test="${not empty user}">

            <h2>Visitor Overview</h2>

            <table role="table">
                <thead role="rowgroup">

                <tr role="row">
                    <th role="columnheader">Firstname</th>
                    <th role="columnheader">Lastname</th>
                    <th role="columnheader">Email</th>
                    <th role="columnheader">Phonenumber</th>
                    <th role="columnheader">ArrivalTime</th>

                </tr>
                </thead>


                <c:forEach var="visitor" items="${visitors}">
                    <tbody role="rowgroup">
                    <tr role="row">

                        <td role="cell">${visitor.firstName}</td>
                        <td role="cell">${visitor.lastName}</td>
                        <td role="cell">${visitor.email}</td>
                        <td role="cell">${visitor.phoneNumber}</td>
                        <td role="cell"><fmt:formatDate pattern="dd/MM/YYYY HH:mm" value="${visitor.arrivalTime}"/></td>

                    </tr>
                    </tbody>
                </c:forEach>



            </table>



        </c:if>





    </main>
</div>

</body>
</html>