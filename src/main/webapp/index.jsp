<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
	<script>function myFunction() {
		var x = document.getElementById("passwordLogIn");
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
					<li id="actual"><a href="Controller">Home</a></li>
					<li><a href="Controller?command=Overview">Users</a></li>
					<li><a href="Controller?command=Register">Sign Up</a></li>
				</ul>
			</nav>
			<h2>Home</h2>

		</header>
		<main>

			<c:choose>

				<c:when test="${person != null}">
					<h3>Welcome, ${person.firstName}. you are now logged in, you can log out again or you can change your password.</h3>
					<form action="Controller?command=LogOut" method="post">
						<input type="submit" value="Log Out" id="logout">
						<td><button type="submit" formaction="Controller?command=ChangePasswordForm&userid=${person.userid}">Change password</button></td>

					</form>
				</c:when>
				<c:otherwise>
					<h3>This is a contact tracing app for the cafeteria "in't hofke" located in Putte. Staff members can log in here. If you are not registered yet, please go to the sign up page first.</h3>
					<c:if test="${not empty error1}">
						<p class="alert-danger">
								${error1}</p>
					</c:if>
					<form novalidate method="post" action="Controller?command=LogIn">
						<p><label for="useridLogIn">User id</label><input type="text" id="useridLogIn" name="useridLogIn"
																	 required > </p>
						<p><label for="passwordLogIn">Password</label><input type="password" id="passwordLogIn"  name="passwordLogIn"
																		required>
							<input type="checkbox" onclick="myFunction()" >Show Password</p>

						<p><input type="submit" id="login" value="Log in"></p>

					</form>

				</c:otherwise>

			</c:choose>



		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg, Lennert Van Oosterwyck </footer>
	</div>
</body>
</html>