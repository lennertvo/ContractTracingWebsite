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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
    <script src="scripts/script.js" defer></script>
</head>

<script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.2.0/zxcvbn.js"></script>


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
            <div class="alert-danger" id="alert-danger">

                <c:forEach var="error" items="${errors}">
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
                                                            required>
                <i class="far fa-eye" id="togglePassword"></i>

                <span id="errorFor-password" class="hidden error"></span></p>

            <meter max="4" id="password-strength-meter"></meter>
            <p id="password-strength-text"></p>
            <p><input type="submit" id="signUp" value="Sign Up"></p>

        </form>
    </main>
    <footer>
        &copy;Lennert Van Oosterwyck
    </footer>


    <script>
        const togglePassword = document.querySelector('#togglePassword');
        const password2 = document.querySelector('#password');

        togglePassword.addEventListener('click', function (e) {
            // toggle the type attribute
            const type = password2.getAttribute('type') === 'password' ? 'text' : 'password';
            password2.setAttribute('type', type);
            // toggle the eye slash icon
            this.classList.toggle('fa-eye-slash');
        })
    </script>


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
