package ui.controller;

import domain.db.DbException;
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
        String oldPassword = request.getParameter("oldPassword");
        List<String> errors1 = new ArrayList<String>();


        checkOldPassword(errors1, person, oldPassword);
        getAndSetNewPassword(person, request, errors1);



        if(errors1.size() == 0) {

                return "index.jsp";
        }
        request.setAttribute("errors1", errors1);
        return "changePassword.jsp";



    }



    private void checkOldPassword( List<String> errors1, Person person, String oldPassword)  {
        if(!person.isCorrectPassword(oldPassword)){
            String faultOldPassword = "The old password is not correct";
            errors1.add(faultOldPassword);
        }
    }

    private void getAndSetNewPassword(Person person, HttpServletRequest request, List<String> errors1){
        String newPassword = request.getParameter("newPassword");
        try {
            person.setPasswordHashed(newPassword);
            service.update(person);
            request.getSession().invalidate();
        }
        catch (Exception e) {
            errors1.add(e.getMessage());
        }
    }



}
