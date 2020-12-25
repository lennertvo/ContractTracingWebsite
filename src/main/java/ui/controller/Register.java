package ui.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Register extends RequestHandler {


    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        return "register.jsp";
        //request.getRequestDispatcher("register.jsp").forward(request, response);
    }

}

