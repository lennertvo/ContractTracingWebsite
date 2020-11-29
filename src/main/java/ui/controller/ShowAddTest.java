package ui.controller;

import domain.model.Role;
import ui.authorization.NotAuthorizedException;
import ui.authorization.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowAddTest extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException, ServletException, IOException {
        //return "addPositiveTest.jsp";
        Role[] roles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, roles);
        request.getRequestDispatcher("addPositiveTest.jsp").forward(request, response);
    }
}
