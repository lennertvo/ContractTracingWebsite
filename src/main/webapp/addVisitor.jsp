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
    <script src="scripts/script.js" defer></script>
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

        <c:if test="${not empty errors}">
            <div class="alert-danger" id="alert-danger">

                <c:forEach var="error" items="${errors}">
                    <ul>
                        <li><c:out value="${error}"/></li>
                    </ul>
                </c:forEach>


            </div>

        </c:if>

        <form method="post" action="Controller?command=AddVisitor" novalidate="novalidate">
            <p><label for="firstName">Fistname</label><input type="text" id="firstName" name="firstName"
                                                             value="${fn:escapeXml(param.firstname)}" required>
                <span id="errorFor-firstName" class="hidden error"></span></p>
            <p><label for="lastName">Lastname</label><input type="text" id="lastName" name="lastName"
                                                            value="${fn:escapeXml(param.lastName)}" required>
                <span id="errorFor-lastName" class="hidden error"></span></p>
            <p><label for="email">E-mail</label><input type="email" id="email" name="email"
                                                       value="${fn:escapeXml(param.email)}" required>
                <span id="errorFor-email" class="hidden error"></span></p>
            <p><label for="phoneNumber">phonenumber</label><input type="tel" id="phoneNumber" name="phoneNumber"
                                                                  value="${fn:escapeXml(param.phonenumber)}"

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
<script>
    document.addEventListener("blur", checkField, true);

    document.addEventListener("submit", finalValidation, false);


    function finalValidation(event) {
        let fields = event.target.elements;
        let error, hasErrors;
        for (let i = 0; i < fields.length; i++) {
            error = hasError(fields[i]);
            if (error) {
                showError(fields[i], error);
                if (!hasErrors) {
                    hasErrors = fields[i];
                }
            }
        }
        if (hasErrors) {
            event.preventDefault();
            hasErrors.focus();
        }
    }

    function checkField(event) {
        let error = hasError(event.target);
        if (error) {
            showError(event.target, error);
        } else {
            removeError(event.target);
        }
    }

    function hasError(field) {
        if (field.disabled || field.type === "file" || field.type === "submit") {
            return;
        }
        let validity = field.validity;
        if (validity === null || validity.valid) {
            return;
        }
        if (validity.valueMissing) {

            return "Please fill out a value"
        }
        if (validity.typeMismatch) {
            if (field.id === "email") {
                return "Give a valid e-mail"
            }
            if (field.id === "phoneNumber") {
                return "Give a valid phonenumber"
            }
            return "Please use the correct input type";
        }
        if (validity.patternMismatch) {
            if (field.type === "text") {
                if (field.id === "useridLogIn" || field.id === "userid") {
                    return "Your userid must contain at least 4 characters";
                }
                if (field.id === "email") {
                    return "Give a valid e-mail";
                }

            }

        }
        //return "Please complete the form correct";
    }

    function removeError(field) {
        let id = field.id;
        let message = document.getElementById("errorFor-" + id);
        if (message != null && message.classList != null) {

            message.innerText = "";
            message.classList.add("hidden");
        }

    }

    function showError(field, error) {
        let id = field.id;
        if (!id) {
            return;
        }
        let message = document.getElementById("errorFor-" + id);
        message.classList.remove("hidden");
        message.innerHTML = error;
    }

</script>

</body>
</html>