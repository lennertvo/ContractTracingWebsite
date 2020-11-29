package ui.controller;

import domain.model.Person;
import domain.model.Role;
import domain.model.Visitor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class VisitorOverview extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person  = (Person) request.getSession().getAttribute("user");

        String userid = person.getUserid();
        if(person.getRole() == Role.ADMIN) {
            List<Visitor> visitors = contactTracingService.getVisitors();
            request.setAttribute("visitors", visitors);
        }
        else{
            List<Visitor> visitorsWithUserid = contactTracingService.getVisitorsWithUserid(userid);
            request.setAttribute("visitors", visitorsWithUserid);

        }





        //return "addVisitor.jsp";
        request.getRequestDispatcher("addVisitor.jsp").forward(request, response);

    }
}
