<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
    <title>User Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">

    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Overview"/>
    </jsp:include>
    <h2>User Overview</h2>

<%--</header>--%>
<main>

    <c:if test="${notAuthorized != null}">
        <p class="alert-danger" id="alert-danger"><c:out value="${notAuthorized}"/></p>
    </c:if>

    <table role="table">
        <thead role="rowgroup">

        <tr role="row">
            <th role="columnheader">E-mail</th>
            <th role="columnheader">First Name</th>
            <th role="columnheader">Last Name</th>
            <c:if test="${user.userid eq 'admin'}">
                <th role="columnheader">Delete Person</th>
            </c:if>



        </tr>
        </thead>


            <c:forEach var="person" items="${persons}">



                <tbody role="rowgroup">
                <tr role="row">
                    <td id="emailPerson" role="cell"><c:out value="${person.email}"/></td>
                    <td id="firstnamePerson" role="cell"><c:out value="${person.firstName}"/></td>
                    <td id="lastnamePerson" role="cell"><c:out value="${person.lastName}"/></td>
                    <c:if test="${user.userid eq 'admin'}">
                    <form action="Controller?command=RemoveConfirmation&userId=<c:out value="${person.userid}"/> "method="post">
                        <td role="cell"><button type="submit">Delete person</button></td>
                    </form>
                    </c:if>

                </tr>

            </tbody>


        </c:forEach>
    </table>



<%--</table>--%>
</main>
<footer>
&copy;Lennert Van Oosterwyck
</footer>
</div>
</body>
</html>