package ui.controller;
import domain.db.DbException;
import domain.model.Person;
import domain.model.Visitor;
import domain.service.VisitorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AddVisitor extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {


        Visitor visitor = new Visitor();


        List<String> errors = new ArrayList<String>();
        getFirstName(visitor, request, errors);
        getLastName(visitor, request, errors);
        getEmail(visitor, request, errors);
        getPhoneNumber(visitor, request, errors);
        getArrivalTime(visitor, request, errors);





        if(errors.size() == 0){
            try {
                visitorService.addVisitor(visitor);
                return "Controller?command=VisitorOverview";

            }
            catch (DbException e){
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=AddVisitorForm";

    }



    private void getArrivalTime(Visitor visitor, HttpServletRequest request, List<String> errors) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.toString());
        try {
            visitor.setArrivalTime(timestamp);
        }
        catch (Exception e) {
            errors.add(e.getMessage());
        }

    }


    private void getPhoneNumber(Visitor visitor, HttpServletRequest request, List<String> errors) {
        String phoneNumber = request.getParameter("phoneNumber");
        request.setAttribute("phoneNumberPreviousValue", phoneNumber);

        try {
            visitor.setPhoneNumber(phoneNumber);
            request.setAttribute("phoneNumberClass", "has-success");
        }
        catch (Exception e) {
            request.setAttribute("phoneNumberClass", "has-error");
            errors.add(e.getMessage());
        }
    }

    private void getEmail(Visitor visitor, HttpServletRequest request, List<String> errors) {
        String email = request.getParameter("email");
        request.setAttribute("emailPreviousValue", email);
        try {
            visitor.setEmail(email);
            request.setAttribute("emailClass", "has-succes");
        }
        catch (Exception e) {
            request.setAttribute("emailClass", "has-error");
            errors.add(e.getMessage());
        }

    }

    private void getLastName(Visitor visitor, HttpServletRequest request, List<String> errors) {
        String lastName = request.getParameter("lastName");
        request.setAttribute("lastNamePreviousValue", lastName);
        try {
            visitor.setLastName(lastName);
            request.setAttribute("lastNameClass", "has-succes");
        }
        catch (Exception e){
            request.setAttribute("lastNameClass", "has-error");
            errors.add(e.getMessage());
        }
    }

    private void getFirstName(Visitor visitor, HttpServletRequest request, List<String> errors) {
        String firstName = request.getParameter("firstName");
        request.setAttribute("firstNamePreviousValue", firstName);
        try {
            visitor.setFirstName(firstName);
            request.setAttribute("firstNamePreviousValue", firstName);
        }
        catch (Exception e) {
            request.setAttribute("firstNameClass", "has-succes");
            errors.add(e.getMessage());
        }
    }



}


