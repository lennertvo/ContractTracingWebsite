<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <figure class="hero-image"></figure>
    <h1><Span>Contact tracing App</Span></h1>
    <nav>
        <ul>
            <li ${param.page eq 'Home'?'id="actual"':""}><a href="Controller">Home</a></li>
            <li ${param.page eq 'Overview'?'id="actual"':""}><a href="Controller?command=Overview">Users</a></li>
            <li ${param.page eq 'Register'?'id="actual"':""}><a href="Controller?command=Register">Sign Up</a></li>


                <li ${param.page eq 'Add Visitor'?'id="actual"':""}><a href="Controller?command=VisitorOverview">Contacts</a></li>


        </ul>
    </nav>

</header>