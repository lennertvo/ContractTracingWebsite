<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
    <title> User Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
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
<li id="actual"><a href="Controller?command=Overview">Users</a></li>
<li><a href="Controller?command=Register">Sign Up</a></li>
</ul>
</nav>
<h2>
User Overview
</h2>

</header><main>
    <table role="table">
        <thead role="rowgroup">

        <tr role="row">
            <th role="columnheader">E-mail</th>
            <th role="columnheader">First Name</th>
            <th role="columnheader">Last Name</th>
            <th role="columnheader">Delete Person</th>

        </tr>
        </thead>

            <c:forEach var="person" items="${persons}">
            <form action="Controller?command=RemoveConfirmation&userId=${person.userid}" method="post">


                <tbody role="rowgroup">
                <tr role="row">
                    <td role="cell">${person.email}</td>
                    <td role="cell">${person.firstName}</td>
                    <td role="cell">${person.lastName}</td>
                    <td role="cell"><button type="submit">Delete person</button></td>

                </tr>


            </tbody>


            </form>


        </c:forEach>



<caption>Users Overview</caption>
</table>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg, Lennert Van Oosterwyck
</footer>
</div>
</body>
</html>