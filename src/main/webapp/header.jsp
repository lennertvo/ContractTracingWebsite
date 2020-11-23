<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <figure class="hero-image"></figure>
    <h1><Span>Contact tracing App</Span></h1>
    <nav>
        <ul>
            <li ${param.page eq 'Home'?'id="actual"':""}><a href="Controller">Home</a></li>
            
            <c:if test="${user.role eq 'ADMIN'}">
                <li ${param.page eq 'Overview'?'id="actual"':""}><a href="Controller?command=Overview">Users</a></li>
            </c:if>

            <c:if test="${empty user}">
                <li ${param.page eq 'Register'?'id="actual"':""}><a href="Controller?command=Register">Sign Up</a></li>
            </c:if>
            <c:if test="${not empty user}">
                <li ${param.page eq 'Add Visitor'?'id="actual"':""}><a href="Controller?command=VisitorOverview">Contacts</a></li>
                <li ${param.page eq 'Add Positive Test'?'id="actual"':""}><a href="Controller?command=ShowAddTest">Add Test</a></li>
                <li ${param.page eq 'Search'?'id="actual"':""}><a href="Controller?command=SearchPositiveTests">Search</a></li>


            </c:if>




        </ul>
    </nav>

</header>