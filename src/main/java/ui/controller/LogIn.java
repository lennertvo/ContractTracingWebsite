package ui.controller;

import domain.db.DbException;
import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogIn extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String userId = request.getParameter("useridLogIn");
            String password = request.getParameter("passwordLogIn");

            Person person = getService().getPersonIfAuthenticated(userId, password);

            if (person == null) {
                request.setAttribute("error1", "No matching user Id and password");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("user", person);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }


    }


}
