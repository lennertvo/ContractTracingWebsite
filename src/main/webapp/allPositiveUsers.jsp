+<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Positive users</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">

    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Positive users"/>
    </jsp:include>
    <h2>All positive users</h2>

    </header><main>
    <c:if test="${notAuthorized != null}">
        <p class="alert-danger" id="alert-danger"><c:out value="${notAuthorized}"/></p>
    </c:if>


    <c:if test="${empty positivePersons}">
        <h3>There are no users with a positve test</h3>
    </c:if>

    <c:if test="${not empty positivePersons}">


        <table role="table">
            <thead role="rowgroup">

            <tr role="row">
                <th role="columnheader">First Name</th>
                <th role="columnheader">Last Name</th>




            </tr>
            </thead>





            <c:forEach var="person" items="${positivePersons}">



                <tbody role="rowgroup">
                <tr id="myThirdTr" role="row">
                    <td role="cell"><c:out value="${person.firstName}"/></td>
                    <td role="cell"><c:out value="${person.lastName}"/></td>


                </tr>

                </tbody>


            </c:forEach>

        </table>
        <h3>Search here for positve users on specific date</h3>


        <c:if test="${not empty error}">
            <p class="alert-danger" id="alert-danger"><c:out value="${error}"/></p>
        </c:if>




        <form method="post" action="Controller?command=AllPositiveUsersOnSpecificDate" novalidate="novalidate">
            <p><label for="date">Date</label><input type="date" id="date" name="date"
                <c:out value="${param.date}"/> required></p>

            <p><input type="submit" id="searchPositiveUsersOnDate" value="Search"></p>

        </form>
        <c:if test="${not empty date}">
            <c:if test="${empty positivePersonsOnSpecificDate}">
                <p class="alert-danger" id="alert-danger">No users tested positive on this date</p>
            </c:if>
        </c:if>

        <c:if test="${not empty date1}">
            <h3>All users who had a positive test on ${date1}</h3>


        </c:if>




        <c:if test="${not empty positivePersonsOnSpecificDate}">


            <table role="table">
                <thead role="rowgroup">

                <tr role="row">
                    <th role="columnheader">First Name</th>
                    <th role="columnheader">Last Name</th>




                </tr>
                </thead>





                <c:forEach var="person" items="${positivePersonsOnSpecificDate}">



                    <tbody role="rowgroup">
                    <tr id="my4Tr" role= row">
                        <td role="cell"><c:out value="${person.firstName}"/></td>
                        <td role="cell"><c:out value="${person.lastName}"/></td>


                    </tr>

                    </tbody>


                </c:forEach>

            </table>

        </c:if>



    </c:if>


</main>
    <footer>
        &copy;Lennert Van Oosterwyck
    </footer>
</div>
</body>
</html>