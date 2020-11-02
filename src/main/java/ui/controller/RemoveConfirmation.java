package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveConfirmation extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        String userid = request.getParameter("userId");

        Person person = getService().getPerson(userid);
        String firstName = person.getFirstName();
        String lastName = person.getLastName();

        request.setAttribute("fName", firstName);
        request.setAttribute("lName", lastName);
        request.setAttribute("userid", userid);

        System.out.println("Firstname:" + firstName);
        System.out.println("Lastname:" + lastName);
        System.out.println("userid:" + userid);


        return "removeConfirmation.jsp";

    }
    //
}
