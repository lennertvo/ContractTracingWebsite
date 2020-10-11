package ui.controller;

import domain.db.PersonService;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class LogIn extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        String userId = request.getParameter("useridLogIn");
        String password = request.getParameter("passwordLogIn");
        Person person = getService().getPersonIfAuthenticated(userId, password);
        if(person == null) {
            request.setAttribute("error1", "No matching user Id and password");
            return "index.jsp";
        }
        else{
            request.getSession().setAttribute("person", person);

        }
        return "index.jsp";
    }
}
