package ui.controller;

import domain.model.Person;
import domain.model.Role;
import ui.authorization.NotAuthorizedException;
import ui.authorization.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Overview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);
        List<Person> persons = contactTracingService.getPersons();
        request.setAttribute("persons", persons);
        return "personoverview.jsp";
        //request.getRequestDispatcher("personoverview.jsp").forward(request, response);
    }

}
