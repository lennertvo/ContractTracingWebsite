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
    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Error"/>
    </jsp:include>
        <h2>Error!</h2>

    <main>
    <article>
        <p>${error}</p>
    </article>
    </main>

    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg, Lennert Van Oosterwyck </footer>

</div>
</body>
</html>