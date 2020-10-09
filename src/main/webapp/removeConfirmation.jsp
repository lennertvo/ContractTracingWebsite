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
<html>
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

    </header><main>

    <article>
        <h2>Are you sure you want to delete ${fName} ${lName}?</h2>



        <form action="Controller?command=DeletePerson&userid=${userid}" method="post">
            <button type="submit">Yes</button>
            <button type="submit" formaction="Controller?command=Overview">No</button>
        </form>

    </article>


</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg, Lennert Van Oosterwyck
    </footer>
</div>
</body>
</html>