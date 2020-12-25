package ui.controller;

import domain.model.Person;
import domain.model.PositiveTest;
import domain.model.Role;
import ui.authorization.NotAuthorizedException;
import ui.authorization.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AllPositiveUsers extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException, ServletException, IOException {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);
        List<Person> persons = contactTracingService.getAllPersonsWhoAlsoInPositiveTest();
        request.setAttribute("positivePersons", persons);
        String date = request.getParameter("date");
        request.setAttribute("date", date);
        String error = (String) request.getAttribute("error");
        request.setAttribute("error", error);
        System.out.println(error);


        request.getRequestDispatcher("allPositiveUsers.jsp").forward(request, response);

    }
}