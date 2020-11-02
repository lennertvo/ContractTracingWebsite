<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Remove</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
<html>
<head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Remove Confirmation"/>
    </jsp:include>
   <main>

    <article>
        <h2>Are you sure you want to delete ${fName} ${lName}?</h2>



        <form action="Controller?command=DeletePerson&userId=${userid}" method="post">
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