package ui.controller;

import domain.model.Person;
import domain.model.PositiveTest;
import domain.model.Role;
import domain.model.Visitor;
import ui.authorization.NotAuthorizedException;
import ui.authorization.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchPositiveTests extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        Role[] roles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, roles);
        Person person = (Person) request.getSession().getAttribute("user");

        PositiveTest positiveTest = contactTracingService.getPositiveTestWithUserid(person.getUserid());
        System.out.println(positiveTest);

        if (positiveTest == null) {
            request.setAttribute("errorr","You are not positive to Covid-19" );
            //request.setAttribute("error1", "You are not positive to Covid-19");
            //request.getRequestDispatcher("search.jsp").forward(request, response);
            return "search.jsp";
        }
            List<Visitor> visitors = contactTracingService.getAllContactsFromPersonWhenPositiveTest(positiveTest);

        if(visitors.isEmpty()) {
            request.setAttribute("error2", "You haven't had any contacts since the positive test");
        }
            request.setAttribute("positiveTest", positiveTest);
            request.setAttribute("visitors", visitors);


        return "search.jsp";

        //request.getRequestDispatcher("search.jsp").forward(request, response);


    }
}
