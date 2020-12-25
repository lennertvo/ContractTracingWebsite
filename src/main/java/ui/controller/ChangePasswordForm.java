package ui.controller;

import domain.model.Role;
import ui.authorization.NotAuthorizedException;
import ui.authorization.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePasswordForm extends RequestHandler {
    @Override
    public String  handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, roles);
        String userid = request.getParameter("userid");
        request.setAttribute("userid", userid);
        return "changePassword.jsp";
        //request.getRequestDispatcher("changePassword.jsp").forward(request, response);

    }
}
