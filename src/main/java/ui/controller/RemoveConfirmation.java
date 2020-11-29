package ui.controller;

import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveConfirmation extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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


        //return "removeConfirmation.jsp";
        request.getRequestDispatcher("removeConfirmation.jsp").forward(request, response);

    }
    //
}
