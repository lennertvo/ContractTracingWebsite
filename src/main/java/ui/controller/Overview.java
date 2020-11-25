package ui.controller;

import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Overview extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Person> persons = contactTracingService.getPersons();
        request.setAttribute("persons", persons);
        //return "personoverview.jsp";
        request.getRequestDispatcher("personoverview.jsp").forward(request, response);
    }

}
