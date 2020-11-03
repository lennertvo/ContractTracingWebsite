package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            request.getSession().setAttribute("user", person);

        }
        return "index.jsp";
    }
}
