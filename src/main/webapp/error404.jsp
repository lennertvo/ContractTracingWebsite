<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Something Wrong</title>
    <link href="css/sample.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="header.jsp"/>
<main id="container">

   <h1>Something is wrong</h1>
    <p>Sorry, we couldn't find the requested page (404 error).</p>
    <p>Go <a href="index.jsp">home</a>.</p>

</main>
</body>
</html>