package ui.controller;

import domain.model.Person;
import domain.model.Role;
import org.checkerframework.checker.units.qual.A;
import ui.authorization.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AllPositiveUsersOnSpecificDate extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        /*Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);
        ArrayList<String> errors = new ArrayList<String>();

       *//* try {
            String dateAsString = request.getParameter("date");
            request.setAttribute("date", dateAsString);
            Date date = Date.valueOf(LocalDate.parse(dateAsString));
            request.setAttribute("date1", date);
            List<Person> persons = contactTracingService.getAllPositiveUserOnSpecificDate(date);
            request.setAttribute("positivePersonsOnSpecificDate", persons);
        }
        catch (Exception e) {
            error = "Date value is empty";
        }
*//*

        String dateAsString = request.getParameter("date");
        if(dateAsString == null) {
            errors.add("Date value is empty");
            request.setAttribute("error1", errors);
            //request.getSession().setAttribute("error", "Date value is empty");
            return "Controller?command=AllPositiveUsers";
        }
        else{
            request.setAttribute("date", dateAsString);
            Date date = Date.valueOf(LocalDate.parse(dateAsString));
            request.setAttribute("date1", date);
            List<Person> persons = contactTracingService.getAllPositiveUserOnSpecificDate(date);
            request.setAttribute("positivePersonsOnSpecificDate", persons);
            return "Controller?command=AllPositiveUsers";
        }
*/

        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);
        String error = "";
        try {
            String dateAsString = request.getParameter("date");
            request.setAttribute("date", dateAsString);
            Date date = Date.valueOf(LocalDate.parse(dateAsString));
            request.setAttribute("date1", date);
            List<Person> persons = contactTracingService.getAllPositiveUserOnSpecificDate(date);
            request.setAttribute("positivePersonsOnSpecificDate", persons);
            return "Controller?command=AllPositiveUsers";
        } catch (Exception e) {
            error = "Date value is empty";
            request.setAttribute("error", error);
            return "Controller?command=AllPositiveUsers";
        }


       //request.setAttribute("error", error);
        //request.getRequestDispatcher("Controller?command=AllPositiveUsers").forward(request, response);
        //response.sendRedirect("Controller?command=AllPositiveUsers");
        //return "Controller?command=AllPositiveUsers";


    }


}