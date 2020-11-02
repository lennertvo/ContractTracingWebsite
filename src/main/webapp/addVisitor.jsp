<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add Visitor</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Visitors overview"/>
    </jsp:include>


        <h2>
            Leave your personal data here
        </h2>



    <main>

        <form method="post" action="Controller?command=addVisitor" novalidate="novalidate">
            <p><label for="firstName">Fistname</label><input type="text" id="firstName" name="firstName" required></p>
            <p><label for="lastName">Lastname</label><input type="text" id="lastName" name="lastName" required></p>
            <p><label for="email">E-mail</label><input type="email" id="email" name="email" required></p>
            <p><label for="phoneNumber">phonenumber</label><input type="tel" id="phoneNumber" name="phoneNumber" required></p>
            <p><label for="arrivalTime">Arrivaltime</label><input type="datetime-local" id="arrivalTime" name="arrivalTime" required></p>
            <p><input type="submit" id="addVisitor" value="Add visitor"></p>



        </form>
    </main>
</div>

</body>
</html>