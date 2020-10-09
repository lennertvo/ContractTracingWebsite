<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Home</title>
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
                <li id="actual"><a href="Controller">Home</a></li>
                <li><a href="Controller?command=Overview">Users</a></li>
                <li><a href="Controller?command=Register">Sign Up</a></li>
            </ul>
        </nav>
        <h2>Error!</h2>

    </header>
    <main>
    <article>
        <p>${error}</p>
    </article>
    </main>

    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg, Lennert Van Oosterwyck </footer>

</div>
</body>
</html>