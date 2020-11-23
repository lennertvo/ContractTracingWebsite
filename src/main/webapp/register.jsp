<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
    <title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
    <script>function myFunction() {
        var x = document.getElementById("password");
        if (x.type === "password") {
            x.type = "text";
        } else {
            x.type = "password";
        }
    }</script>
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Register"/>
    </jsp:include>
    <h2>
        Staff can create an account here.
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





    <form novalidate="novalidate" method="post" action="Controller?command=SignUp">
    	<!-- novalidate in order to be able to run tests correctly -->
        <p><label for="userid">User id</label><input type="text" id="userid" name="userid"
          value="${userIdPreviousValue}" required > </p>
        <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
          value="${firstNamePreviousValue}" required> </p>
        <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
         value="${lastNamePreviousValue}" required> </p>
        <p><label for="email">Email</label><input type="email" id="email" name="email" value="${emailPreviousValue}" required></p>
        <p><label for="password">Password</label><input type="password" id="password"  name="password"
         required>
            <input type="checkbox" onclick="myFunction()" >Show Password</p>
        <p><input type="submit" id="signUp" value="Sign Up"></p>
        
    </form>
</main>
    <footer>
        &copy;Lennert Van Oosterwyck
    </footer>
</div>
</body>
</html>
