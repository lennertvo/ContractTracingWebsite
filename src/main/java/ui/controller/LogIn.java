package ui.controller;

import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogIn extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userId = request.getParameter("useridLogIn");
            String password = request.getParameter("passwordLogIn");

            Person person = contactTracingService.getPerson(userId);

            if (person != null) {
                if (person.isCorrectPassword(password)) {
                    request.getSession().setAttribute("user", person);
                    request.getSession().setAttribute("success", "You have been logged in successfully");


                } else {
                    request.setAttribute("error1", "Wrong password");
                }
            } else {
                request.setAttribute("error1", "There is no person with this userid");
            }

        } catch (Exception e) {
            request.setAttribute("error1", "There is no person with this combination");

        }

        
        request.getRequestDispatcher("index.jsp").forward(request, response);
        request.getSession().removeAttribute("success");


    }


}
