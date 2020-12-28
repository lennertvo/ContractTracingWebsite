package ui.controller;

import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogIn extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        try {
            String userId = request.getParameter("useridLogIn");
            String password = request.getParameter("passwordLogIn");

            Person person = contactTracingService.getPerson(userId);

            if (person != null) {
                if (person.isCorrectPassword(password)) {
                    request.getSession().setAttribute("user", person);
                    request.getSession().setAttribute("success", "You have been logged in successfully");


                } else {
                    request.getSession().setAttribute("error", "Wrong password");
                    //request.setAttribute("error1", "Wrong password");
                }
            } else {
                request.getSession().setAttribute("error", "There is no person with this userid");
                //request.setAttribute("error1", "There is no person with this userid");
            }

        } catch (Exception e) {
            request.getSession().setAttribute("error","There is no person with this combination" );
            //request.setAttribute("error1", "There is no person with this combination");

        }

        return "index.jsp";
        //request.getRequestDispatcher("index.jsp").forward(request, response);



    }


}
