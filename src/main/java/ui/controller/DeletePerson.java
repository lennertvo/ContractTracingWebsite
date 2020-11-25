package ui.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletePerson extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userId");
        contactTracingService.delete(userid);
        //return "Controller?command=Overview";
        //request.getRequestDispatcher("Controller?command=Overview").forward(request, response);
        response.sendRedirect("Controller?command=Overview");

    }
}
