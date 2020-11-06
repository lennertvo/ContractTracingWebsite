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

</header><main>
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
                    <td role="cell">${person.email}</td>
                    <td role="cell">${person.firstName}</td>
                    <td role="cell">${person.lastName}</td>
                    <c:if test="${user.userid eq 'admin'}">
                    <form action="Controller?command=RemoveConfirmation&userId=${person.userid}" method="post">
                        <td role="cell"><button type="submit">Delete person</button></td>
                    </form>
                    </c:if>

                </tr>

            </tbody>


        </c:forEach>
    </table>



</table>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg, Lennert Van Oosterwyck
</footer>
</div>
</body>
</html>