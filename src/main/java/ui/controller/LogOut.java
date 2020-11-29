package ui.controller;

import domain.model.Role;
import ui.authorization.NotAuthorizedException;
import ui.authorization.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOut extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException, ServletException, IOException {
        request.getSession().invalidate();
        //return "index.jsp";

        request.getRequestDispatcher("Controller?command=Home").forward(request, response);
    }

}
