<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
		<jsp:include page="header.jsp">
			<jsp:param name="page" value="Home"/>
		</jsp:include>

		<main>
			<c:if test="${notAuthorized != null}">
				<p class="alert-danger">${notAuthorized}</p>
			</c:if>



			<c:choose>

				<c:when test="${not empty user}">
					<h3>Welcome, ${user.firstName}. you are now logged in, you can log out again or you can change your password.</h3>
					<form action="Controller?command=LogOut" method="post">
						<input type="submit" value="Log Out" id="logout">
						<td><button type="submit" formaction="Controller?command=ChangePasswordForm&userid=${user.userid}">Change password</button></td>

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
																	value="${fn:escapeXml(param.useridLogIn)}" required > </p>
						<p><label for="passwordLogIn">Password</label><input type="password" id="passwordLogIn"  name="passwordLogIn"
																		required>
							<input type="checkbox" onclick="myFunction()" >Show Password</p>

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