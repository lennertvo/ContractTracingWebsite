package ui.controller;

import domain.model.Person;
import domain.model.Role;
import ui.authorization.NotAuthorizedException;
import ui.authorization.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChangePassword extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] roles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, roles);
        String userid = request.getParameter("userid");
        Person person = contactTracingService.getPerson(userid);
        String newPassword = request.getParameter("newPassword");
        String oldPassword = request.getParameter("oldPassword");
        List<String> errors1 = new ArrayList<String>();


        checkOldPassword(errors1, person, oldPassword);
        getAndSetNewPassword(person, request, errors1);


        if (errors1.size() == 0) {

            return "index.jsp";
            //request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        request.setAttribute("errors1", errors1);

        return "changePassword.jsp";
        //request.getRequestDispatcher("changePassword.jsp").forward(request, response);


    }


    private void checkOldPassword(List<String> errors1, Person person, String oldPassword) {
        if (!person.isCorrectPassword(oldPassword)) {
            String faultOldPassword = "The old password is not correct";
            errors1.add(faultOldPassword);
        }
    }

    private void getAndSetNewPassword(Person person, HttpServletRequest request, List<String> errors1) {
        String newPassword = request.getParameter("newPassword");
        try {
            person.setPasswordHashed(newPassword);
            contactTracingService.update(person);
            request.getSession().invalidate();
        } catch (Exception e) {
            errors1.add(e.getMessage());
        }
    }


}
