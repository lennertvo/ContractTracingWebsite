package ui.controller;

import domain.db.DbException;
import domain.model.Person;
import domain.model.PositiveTest;

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
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errors = new ArrayList<String>();

        PositiveTest positiveTest = new PositiveTest();
        getUserid(positiveTest, request, errors);
        getDate(positiveTest, request, errors);

        if (errors.size() == 0) {
            try {

                contactTracingService.addPositiveTest(positiveTest);
                System.out.println("het is gelukt !!!");

                //return "Controller?command=VisitorOverview";
                //request.getRequestDispatcher("Controller?command=VisitorOverview").forward(request, response);
                response.sendRedirect( "Controller?command=VisitorOverview");
            } catch (DbException e) {
                errors.add(e.getMessage());
            }
        } else{
            request.setAttribute("errors", errors);
            //return "Controller?command=ShowAddTest";
            request.getRequestDispatcher("Controller?command=ShowAddTest").forward(request, response);
        }




    }

    private void getDate(PositiveTest positiveTest, HttpServletRequest request, List<String> errors) {
        String dateAsString = request.getParameter("date").trim();

        try {
            Date date = Date.valueOf(LocalDate.parse(dateAsString));
            positiveTest.setDate(date);
        } catch (Exception e) {
            errors.add(e.getMessage());
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
