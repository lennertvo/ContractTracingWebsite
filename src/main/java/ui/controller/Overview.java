package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Overview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Person> persons = service.getPersons();
        request.setAttribute("persons", persons);
        System.out.println("werkt dit?");
        return "personoverview.jsp";
    }
}
