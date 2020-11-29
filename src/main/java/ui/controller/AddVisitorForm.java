package ui.controller;

import domain.model.Person;
import domain.model.Role;
import domain.model.Visitor;
import ui.authorization.NotAuthorizedException;
import ui.authorization.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddVisitorForm extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException, ServletException, IOException {
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
        Role[] roles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, roles);
        request.getRequestDispatcher("addVisitor.jsp").forward(request, response);

    }
}
