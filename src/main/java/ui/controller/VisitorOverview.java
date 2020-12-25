package ui.controller;

import domain.model.Person;
import domain.model.Role;
import domain.model.Visitor;
import domain.service.ContactTracingService;
import ui.authorization.NotAuthorizedException;
import ui.authorization.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class VisitorOverview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        Role[] roles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, roles);
        Person person  = (Person) request.getSession().getAttribute("user");
        List<Visitor> visitors;
        LocalDate from = null;
        LocalDate until = null;
        List<Person> users  =  contactTracingService.getPersons();
        Person person1 = null;

        try {
            String fromString = request.getParameter("from");
            String untilString = request.getParameter("until");
            from = LocalDate.parse(fromString);
            until = LocalDate.parse(untilString);
            request.setAttribute("fromPreviousValue", fromString);
            request.setAttribute("untilPreviousValue", untilString);
            if(person.getRole() == Role.ADMIN) {
                String userid = request.getParameter("useridSelect");
                person1 = contactTracingService.getPerson(userid);
            }


        }
        catch (Exception e) {}


        if (from != null && until != null && from.isBefore(until)) {
            if(person.getRole() == Role.ADMIN) {
                if(person1 != null) {
                    visitors = contactTracingService.getAllContactsFromUserBetween2SpecificDates(person1, from, until);
                    request.setAttribute("person1", person1);
                    System.out.println("yessss");
                }
                else{
                    visitors = contactTracingService.getAllContactsBetween2SpecificDates(from, until);
                }

                

            }
            else{
                visitors = contactTracingService.getAllContactsFromUserBetween2SpecificDates(person, from, until);
            }
        }
        else{
            if(person.getRole() == Role.ADMIN) {
                visitors = contactTracingService.getVisitors();
            }
            else{
                visitors = contactTracingService.getVisitorsWithUserid(person.getUserid());
            }
        }
        request.setAttribute("visitors", visitors);
        request.setAttribute("users", users);

        return "addVisitor.jsp";
        //request.getRequestDispatcher("addVisitor.jsp").forward(request, response);

    }
}
