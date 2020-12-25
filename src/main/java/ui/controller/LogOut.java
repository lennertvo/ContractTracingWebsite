package ui.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOut extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        request.getSession().setAttribute("success", "You have successfully logged out");
        return "index.jsp";
        //request.getRequestDispatcher("index.jsp").forward(request, response);
        //request.getSession().removeAttribute("success");
    }

}
