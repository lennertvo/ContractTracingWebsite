package ui.controller;

import domain.db.DbException;
import domain.model.Person;
import domain.model.PositiveTest;
import domain.model.Role;
import ui.authorization.NotAuthorizedException;
import ui.authorization.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddPositiveTest extends RequestHandler {


    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.ADMIN, Role.USER};
        Utility.checkRole(request, roles);
        List<String> errors = new ArrayList<String>();

        PositiveTest positiveTest = new PositiveTest();
        getUserid(positiveTest, request, errors);
        getDate(positiveTest, request, errors);

        if (errors.size() == 0) {
            try {

                contactTracingService.addPositiveTest(positiveTest);
                request.setAttribute("success", "Successfully added a positive test");
                System.out.println("het is gelukt !!!");

                return "Controller?command=VisitorOverview";
                //request.getRequestDispatcher("Controller?command=VisitorOverview").forward(request, response);
                //response.sendRedirect("Controller?command=VisitorOverview");
            } catch (DbException e) {
                errors.add(e.getMessage());
                request.getSession().setAttribute("error", errors);
                return "Controller?command=ShowAddTest";
            }
        }

        request.getSession().setAttribute("error", errors);
        //request.setAttribute("errors", errors);
        return "Controller?command=ShowAddTest";
        //request.getRequestDispatcher("Controller?command=ShowAddTest").forward(request, response);


    }

    private void getDate(PositiveTest positiveTest, HttpServletRequest request, List<String> errors) {
        String dateAsString = request.getParameter("date").trim();

        try {
            Date date = Date.valueOf(LocalDate.parse(dateAsString));
            positiveTest.setDate(date);
            request.setAttribute("date", date);
        } catch (Exception e) {
            errors.add("Please fill a date in");

        }
    }

    private void getUserid(PositiveTest positiveTest, HttpServletRequest request, List<String> errors) {
        Person p = (Person) request.getSession().getAttribute("user");
        String userid = p.getUserid();

        try {
            positiveTest.setUserid(userid);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }


    }
}
