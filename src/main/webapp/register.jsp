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
<header>
    <figure class="hero-image">
    </figure>

    <h1>
        <span>Contact Tracing App</span>
    </h1>
<nav>
<ul>
<li><a href="Controller">Home</a></li>
<li><a href="Controller?command=Overview">Users</a></li>
<li id="actual"><a href="Controller?command=Register">Sign Up</a></li>
</ul>
</nav>
<h2>
Register
</h2>

</header><main>
    <c:if test="${not empty errors}">
        <div class="alert-danger">
            <ul>
                <li>Fill in all the fields</li>
            </ul>
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
&copy; Webontwikkeling 3, UC Leuven-Limburg, Lennert Van Oosterwyck
</footer>
</div>
</body>
</html>
