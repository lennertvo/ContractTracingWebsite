<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Change Password</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="scripts/formValidation.js" defer></script>
    <script src="scripts/messages.js" defer></script>
    <head>
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

        </h2>

    </header>
    <main>
        <c:if test="${notAuthorized != null}">
            <p class="alert-danger">${notAuthorized}</p>
        </c:if>

        <article>
            <h2>Change your password</h2>
            <%-- <c:if test="${not empty errors1}">
                 <div class="alert-danger" id="alert-danger">
                     <ul>
                         <c:forEach items="${errors1}" var="error">
                             <li>${error}</li>
                         </c:forEach>

                     </ul>
                 </div>

             </c:if>--%>
            <c:if test="${not empty error}">
                <div class="alert-danger">

                    <c:forEach var="error" items="${error}">
                        <ul>
                            <li><c:out value="${error}"/></li>
                        </ul>
                    </c:forEach>


                </div>

            </c:if>


            <form action="Controller?command=ChangePassword&userid=${userid}" method="post" novalidate>
                <p><label for="oldPassword">Old Password</label><input type="password" id="oldPassword"
                                                                       name="oldPassword" required>
                    <span id="errorFor-oldPassword" class="hidden error"></span></p>
                <p><label for="newPassword">New Password</label><input type="password" id="newPassword"
                                                                       name="newPassword" required
                                                                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
                </p>
                <p><span id="errorFor-newPassword" class="hidden error"></span></p>
                <p><input type="submit" id="changePassword" value="Change Password"></p>
            </form>

        </article>


    </main>
    <footer>
        &copy;Lennert Van Oosterwyck
    </footer>
</div>
</body>
</html>