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
    <script src="scripts/script.js" defer></script>

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
                <h3>This is a contact tracing app for the cafeteria "in't hofke" located in Putte. Staff members can log
                    in here. If you are not registered yet, please go to the sign up page first.</h3>
                <c:if test="${not empty success}">
                    <div class="alert-success">

                        <ul>
                            <li><c:out value="${success}"/></li>
                        </ul>

                    </div>

                </c:if>
                <c:if test="${not empty error1}">
                    <div class="alert-danger">

                        <ul>
                            <li><c:out value="${error1}"/></li>
                        </ul>

                    </div>

                </c:if>

                <c:if test="${not empty noPerson}">
                    <div class="alert-danger">

                        <ul>
                            <li><c:out value="${noPerson}"/></li>
                        </ul>

                    </div>

                </c:if>


                <%--<c:if test="${not empty error1}">

                    <p class="alert-danger" id="alert-danger1">
                            ${error1}</p>
                </c:if>--%>
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

<script>

    const togglePassword1 = document.querySelector('#togglePassword');
    const password2 = document.querySelector('#passwordLogIn');

    togglePassword1.addEventListener('click', function (e) {
        // toggle the type attribute
        const type = password2.getAttribute('type') === 'password' ? 'text' : 'password';
        password2.setAttribute('type', type);
        // toggle the eye slash icon
        this.classList.toggle('fa-eye-slash');
    });
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