package ui.controller;

import domain.model.Person;
import domain.model.Role;
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
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";
        try {
            String dateAsString = request.getParameter("date");
            request.setAttribute("date", dateAsString);
            Date date = Date.valueOf(LocalDate.parse(dateAsString));
            request.setAttribute("date1", date );
            List<Person> persons = contactTracingService.getAllPositiveUserOnSpecificDate(date);
            request.setAttribute("positivePersonsOnSpecificDate", persons);
        }
        catch (Exception e) {
            error = "Date value is empty";
        }






        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);
        request.setAttribute("error", error);
        request.getRequestDispatcher("Controller?command=AllPositiveUsers").forward(request, response);
        //response.sendRedirect("Controller?command=AllPositiveUsers");


    }


}
