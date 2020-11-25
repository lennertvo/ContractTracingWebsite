package ui.controller;


import domain.model.Visitor;
import domain.service.ContactTracingService;
import domain.service.PersonService;
import domain.service.PositiveTestService;
import domain.service.VisitorService;
import org.checkerframework.checker.units.qual.A;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ContactTracingService contactTracingService = new ContactTracingService();
    private final HandlerFactory handlerFactory = new HandlerFactory();

    public Controller() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String command = request.getParameter("command");
        //String destination = "index.jsp";
        if (command != null) {
            try {
                RequestHandler handler = handlerFactory.getHandler(command, contactTracingService);
                handler.handleRequest(request, response);
                handler.setModel(contactTracingService);
                //request.getRequestDispatcher(command).forward(request, response);

            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
                //destination = "error.jsp";
                request.getRequestDispatcher("error.jsp").forward(request, response);


            }
        }
        else{
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }



    }
}
