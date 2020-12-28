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
    <script src="scripts/messages.js" defer></script>
    <script src="scripts/formValidation.js" defer></script>
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
            <p class="alert-danger"><c:out value="${notAuthorized}"/></p>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert-danger" id="alert-danger">

                <c:forEach var="error" items="${error}">
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

        <form method="post" action="Controller?command=AddVisitor" novalidate="novalidate">
            <p><label for="firstName">Fistname</label><input type="text" id="firstName" name="firstName"
                                                             value="${fn:escapeXml(param.firstName)}" required>
                <span id="errorFor-firstName" class="hidden error"></span></p>
            <p><label for="lastName">Lastname</label><input type="text" id="lastName" name="lastName"
                                                            value="${fn:escapeXml(param.lastName)}" required>
                <span id="errorFor-lastName" class="hidden error"></span></p>
            <p><label for="email">E-mail</label><input type="email" id="email" name="email"
                                                       value="${fn:escapeXml(param.email)}" required>
                <span id="errorFor-email" class="hidden error"></span></p>
            <p><label for="phoneNumber">phonenumber</label><input type="tel" id="phoneNumber" name="phoneNumber"
                                                                  value="${fn:escapeXml(param.phoneNumber)}"

                                                                  required
                                                                  pattern="^(?:(?:\\+|00)32[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$">
                <span id="errorFor-phoneNumber" class="hidden error"></span></p>
            <p><input type="submit" id="addVisitor" value="Add visitor"></p>

        </form>

        <form id="filterForm" action="Controller?command=VisitorOverview" method="post">
            <p><label for="from">From:</label><input type="date" id="from" name="from"
                                                     <c:out value="${fromPreviousValue}"/>required></p>
            <p><label for="until">Until:</label><input type="date" id="until" name="until"
                                                       <c:out value="${untilPreviousValue}"/>required></p>

            <c:if test="${user.role == 'ADMIN'}">
                <p><label for="useridSelect">Userid</label><input type="text" list="users" id="useridSelect"
                                                                  name="useridSelect">

                    <datalist id="users">
                        <c:forEach var="users" items="${users}">
                            <option value="${users.userid}"></option>
                        </c:forEach>
                    </datalist>
                </p>
            </c:if>
            <p><input type="submit" id="filter" value="Filter"></p>
            <a href="Controller?command=VisitorOverview">Clear filter</a>
        </form>

        <c:if test="${not empty fromPreviousValue}">
            <c:if test="${not empty untilPreviousValue}">
                <p> All contacts between ${fromPreviousValue} and ${untilPreviousValue}</p>
                <c:if test="${person1 != null}">
                    <p>From ${person1.firstName} ${person1.lastName}</p>
                </c:if>

            </c:if>
        </c:if>


        <c:if test="${not empty user}">

            <h2>Visitor Overview</h2>
            <input type="text" id="search" name="search" onkeyup="mySearchFunction()" placeholder="Search for names..."
                   title="Type in a name">

            <table id="myTable" role="table">
                <thead role="rowgroup">

                <tr role="row">
                    <th role="columnheader">Firstname</th>
                    <th role="columnheader">Lastname</th>
                    <th role="columnheader">ArrivalTime</th>
                    <th role="columnheader">E-mail</th>
                    <th role="columnheader">Phonenumber</th>
                    <th role="columnheader">userid</th>


                </tr>
                </thead>


                <c:forEach var="visitor" items="${visitors}">
                    <tbody role="rowgroup">
                    <tr id="myTr" role="row">
                        <td role="cell"><c:out value="${visitor.firstName}"/></td>
                        <td role="cell"><c:out value="${visitor.lastName}"/></td>
                        <td role="cell"><fmt:formatDate pattern="dd/MM/YYYY HH:mm" value="${visitor.arrivalTime}"/></td>
                        <td role="cell"><c:out value="${visitor.email}"/></td>
                        <td role="cell"><c:out value="${visitor.phoneNumber}"/></td>
                        <td role="cell"><c:out value="${visitor.userid}"/></td>


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