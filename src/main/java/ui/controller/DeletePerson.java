package ui.controller;

import domain.model.Role;
import ui.authorization.NotAuthorizedException;
import ui.authorization.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletePerson extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);
        String userid = request.getParameter("userId");
        contactTracingService.delete(userid);
        return "Controller?command=Overview";
        //request.getRequestDispatcher("Controller?command=Overview").forward(request, response);

        //response.sendRedirect("Controller?command=Overview");

    }
}
