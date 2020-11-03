<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <jsp:param name="page" value="VisitorOverview"/>
    </jsp:include>
    <h2>Visitor Overview</h2>

    </header><main>
    <table role="table">
        <thead role="rowgroup">

        <tr role="row">
            <th role="columnheader">ArrivalTime</th>
            <th role="columnheader">Firstname</th>
            <th role="columnheader">Lastname</th>

        </tr>
        </thead>




    </table>
    <c:forEach var="visitor" items="${visitors}">
        <tbody role="rowgroup">
        <tr role="row">




            <td role="cell"><fmt:formatDate pattern="dd/MM/YYYY HH:mm:ss" value="${visitor.arrivalTime}"/></td>
            <td role="cell">${visitor.firstName}</td>
            <td role="cell">${visitor.lastName}</td>

        </tr>
        </tbody>
    </c:forEach>



    </table>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg, Lennert Van Oosterwyck
    </footer>
</div>
</body>
</html>