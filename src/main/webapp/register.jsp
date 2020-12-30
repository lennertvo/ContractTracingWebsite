<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">--%>
    <script src="scripts/messages.js" defer></script>
    <script src="scripts/togglePassword.js" defer></script>
    <script src="scripts/formValidation.js" defer></script>
</head>

<script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.2.0/zxcvbn.js"></script>


<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Register"/>
    </jsp:include>
    <h2>
        You can create an account here and after that you can add your contacts.
    </h2>

    <main>
        <c:if test="${not empty error}">
            <div class="alert-danger" id="alert-danger">

                <c:forEach var="error" items="${error}">
                    <ul>
                        <li><c:out value="${error}"/></li>
                    </ul>
                </c:forEach>


            </div>

        </c:if>


        <form novalidate="novalidate" method="post" action="Controller?command=SignUp">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="userid">User id</label><input type="text" id="userid" name="userid"
                                                         value="${fn:escapeXml(param.userid)}" required
                                                         pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{3,20}$">
                <span id="errorFor-userid" class="hidden error"></span></p>
            <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                                                               value="${fn:escapeXml(param.firstName)}" required>
                <span id="errorFor-firstName" class="hidden error"></span></p>
            <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
                                                             value="${fn:escapeXml(param.lastName)}" required>
                <span id="errorFor-lastName" class="hidden error"></span></p>
            <p><label for="email">Email</label><input type="email" id="email" name="email"
                                                      value="${fn:escapeXml(param.email)}" required
                                                      pattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$">
                <span id="errorFor-email" class="hidden error"></span></p>
            <p><label for="password">Password</label><input type="password" id="password" name="password"
                                                            required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
                <i class="far fa-eye" id="togglePassword"></i>

                <%-- <span id="errorFor-password" class="hidden error"></span></p>--%>

                <%--<meter max="4" min="0" id="password-strength-meter" optimum="2"></meter>--%>
            <p id="password-strength-text"><span id="errorFor-password" class="hidden error"></span></p></p>
            <p><input type="submit" id="signUp" value="Sign Up"></p>

        </form>
    </main>
    <footer>
        &copy;Lennert Van Oosterwyck
    </footer>


</body>
</html>
