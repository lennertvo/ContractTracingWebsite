package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ChangePassword extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        Person person = service.getPerson(userid);
        String newPassword = request.getParameter("newPassword");
        String newPassword2 = request.getParameter("newPassword2");
        String oldPassword = request.getParameter("oldPassword");
        List<String> errors1 = new ArrayList<String>();



        checkOldPassword(errors1, person, oldPassword);
        checkNewPasswords(errors1, newPassword, newPassword2);

        String destination;
        if(errors1.size() > 0) {
            request.setAttribute("errors1", errors1);
            destination = "changePassword.jsp";
        }
        else{
            person.setPassword(newPassword);
            destination = "index.jsp";

        }
        return destination;


    }

    private void checkNewPasswords(List<String> errors1, String newPassword, String newPassword2) {
        if(!newPassword.equals(newPassword2)){
            String newPassword1NotNewPassword2 = "The new passwords do not match";
            errors1.add(newPassword1NotNewPassword2);
        }
    }

    private void checkOldPassword( List<String> errors1, Person person, String oldPassword)  {
        if(!person.isCorrectPassword(oldPassword)){
            String faultOldPassword = "The old password is not correct";
            errors1.add(faultOldPassword);
        }
    }
    //test2


}
