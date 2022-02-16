package ui.controller;


import domain.service.ContactTracingService;
import ui.authorization.NotAuthorizedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String destination = "index.jsp";
        if (command != null) {
            try {
                RequestHandler handler = handlerFactory.getHandler(command, contactTracingService);

                try {
                    destination= handler.handleRequest(request, response);
                    handler.setModel(contactTracingService);
                }
                catch (NotAuthorizedException e) {
                    request.setAttribute("notAuthorized", "You have insufficient right to have a look a the requested page.");
                    destination = "index.jsp";
                }
                handler.setModel(contactTracingService);
                //request.getRequestDispatcher(command).forward(request, response);

            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
                destination = "error.jsp";
                /*request.getRequestDispatcher("error.jsp").forward(request, response);*/


            }
        }
       /* else{
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }*/

        if(response.isCommitted()) {
            response.sendRedirect(destination);
            request.getSession().removeAttribute("success");
            request.getSession().removeAttribute("error");


        }else{
            request.getRequestDispatcher(destination).forward(request, response);
            request.getSession().removeAttribute("success");
            request.getSession().removeAttribute("error");


        }





    }
}
