<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


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
        <c:if test="${notAuthorized != null}">
            <p class="alert-danger">${notAuthorized}</p>
        </c:if>

        <c:if test="${not empty errors}">
            <div class="alert-danger">

                <c:forEach var="error" items="${errors}">
                    <ul>
                        <li>${error}</li>
                    </ul>
                </c:forEach>


            </div>

        </c:if>

        <form method="post" action="Controller?command=AddVisitor" novalidate="novalidate">
            <p><label for="firstName">Fistname</label><input type="text" id="firstName" name="firstName"
                                                             value="${fn:escapeXml(param.firstName)}" required></p>
            <p><label for="lastName">Lastname</label><input type="text" id="lastName" name="lastName"
                                                            value="${fn:escapeXml(param.lastName)}" required></p>
            <p><label for="email">E-mail</label><input type="email" id="email" name="email"
                                                       value="${fn:escapeXml(param.email)}" required></p>
            <p><label for="phoneNumber">phonenumber</label><input type="tel" id="phoneNumber" name="phoneNumber"
                                                                  value="${fn:escapeXml(param.phoneNumber)}"

                                                                  required></p>
            <p><input type="submit" id="addVisitor" value="Add visitor"></p>

        </form>
        <c:if test="${not empty user}">

            <h2>Visitor Overview</h2>
            <input type="text" id="search" name="search" onkeyup="mySearchFunction()" placeholder="Search for names..."
                   title="Type in a name">

            <table id="myTable" role="table">
                <thead role="rowgroup">

                <tr role="row">
                    <th role="columnheader">Firstname</th>
                    <th role="columnheader">Lastname</th>
                    <th role="columnheader">Arrivaltime</th>

                </tr>
                </thead>


                <c:forEach var="visitor" items="${visitors}">
                    <tbody role="rowgroup">
                    <tr id="myTr" role="row">

                        <td role="cell">${visitor.firstName}</td>
                        <td role="cell">${visitor.lastName}</td>
                        <td role="cell"><fmt:formatDate pattern="dd/MM/YYYY HH:mm" value="${visitor.arrivalTime}"/></td>

                    </tr>
                    </tbody>
                </c:forEach>


            </table>


        </c:if>


    </main>
    <footer>
        &copy;Lennert Van Oosterwyck
    </footer>

    <script>
        function mySearchFunction() {
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("search");
            filter = input.value.toUpperCase();
            table = document.getElementById("myTable");
            tr = table.getElementsByTagName("tr");
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[0];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>
</div>

</body>
</html>