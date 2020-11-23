package ui.controller;

import domain.model.Person;
import domain.model.Visitor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class VisitorOverview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person  = (Person) request.getSession().getAttribute("user");

        String userid = person.getUserid();
        if(userid.equals("ADMIN")) {
            List<Visitor> visitors = contactTracingService.getVisitors();
            request.setAttribute("visitors", visitors);
        }
        else{
            List<Visitor> visitorsWithUserid = contactTracingService.getVisitorsWithUserid(userid);
            request.setAttribute("visitors", visitorsWithUserid);

        }





        return "addVisitor.jsp";


    }
}
