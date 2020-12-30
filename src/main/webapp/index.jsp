<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
    <script src="scripts/messages.js" defer></script>
    <script src="scripts/togglePassword.js" defer></script>
    <script src="scripts/formValidation.js" defer></script>

</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Home"/>
    </jsp:include>

    <main>
        <c:if test="${notAuthorized != null}">
            <li class="alert-danger" id="alert-danger">${notAuthorized}</li>
        </c:if>


        <c:choose>

            <c:when test="${not empty user}">
                <h3 id="welcomeMsg">Welcome <c:out value="${user.firstName}"/>, you are now logged in, you can log out
                    again or you can change your password.</h3>
                <h4>To add your contacts go to the "Contacts" Page</h4>


                <c:if test="${not empty success}">
                    <div class="alert-success">

                        <ul>
                            <li><c:out value="${success}"/></li>
                        </ul>

                    </div>

                </c:if>


                <form action="Controller?command=LogOut" method="post">
                    <input type="submit" value="Log Out" id="logout">
                    <td>
                        <button type="submit"
                                formaction="Controller?command=ChangePasswordForm&userid=<c:out value="${user.userid}"/> ">
                            Change password
                        </button>
                    </td>

                </form>
            </c:when>
            <c:otherwise>
                <h3>This is a contact tracing app for the cafeteria "in't hofke" located in Putte. Log in here. If you
                    are not registered yet, please go to the sign up page first.</h3>
                <c:if test="${not empty success}">
                    <div class="alert-success">

                        <ul>
                            <li><c:out value="${success}"/></li>
                        </ul>

                    </div>

                </c:if>

                <c:if test="${not empty error}">
                    <div class="alert-danger">

                        <c:forEach var="error" items="${error}">
                            <ul>
                                <li><c:out value="${error}"/></li>
                            </ul>
                        </c:forEach>


                    </div>

                </c:if>

                <c:if test="${not empty noPerson}">
                    <div class="alert-danger">

                        <ul>
                            <li><c:out value="${noPerson}"/></li>
                        </ul>

                    </div>

                </c:if>


                <form novalidate method="post" action="Controller?command=LogIn">
                    <p><label for="useridLogIn">User id</label><input type="text" id="useridLogIn" name="useridLogIn"
                                                                      value="${fn:escapeXml(param.useridLogIn)}"
                                                                      required
                                                                      pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{3,20}$">
                        <span id="errorFor-useridLogIn" class="hidden error"></span></p>
                    <p><label for="passwordLogIn">Password</label><input type="password" id="passwordLogIn"
                                                                         name="passwordLogIn"
                        <c:out value="${param.passwordLogIn}"/> required>
                        <i class="far fa-eye" id="togglePassword"></i>
                        <span id="errorFor-passwordLogIn" class="hidden error"></span></p>


                    <p><input type="submit" id="login" value="Log in"></p>

                </form>

            </c:otherwise>

        </c:choose>


    </main>
    <footer>
        &copy;Lennert Van Oosterwyck
    </footer>
</div>


</body>
</html>