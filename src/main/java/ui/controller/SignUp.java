package ui.controller;


import domain.db.DbException;
import domain.model.Person;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SignUp extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = new Person();

        List<String> errors = new ArrayList<String>();
        getUserId(person, request, errors);
        getFirstName(person, request, errors);
        getLastName(person, request, errors);
        getEmail(person, request, errors);
        getPassword(person, request, errors);
        getRole(person, request, errors);

        if(errors.size() == 0){
            try {
                contactTracingService.addPerson(person);
                request.getSession().setAttribute("user", person);
                //return "index.jsp";
                request.getRequestDispatcher("index.jsp").forward(request, response);

            }
            catch (DbException e) {
                errors.add(e.getMessage());

            }

            }else{
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }


            //return "register.jsp";



        //request.setAttribute("errors", errors);

        }



        private void getRole(Person person, HttpServletRequest request, List<String> errors) {
            String userId = request.getParameter("userid");
            if (userId.equals("admin")) {
                try {
                    person.setRole(Role.ADMIN);
                }
                catch (Exception e) {
                    errors.add(e.getMessage());
                }
            }
            else{

                try {
                    person.setRole(Role.USER);
                }
                catch (Exception e) {
                    errors.add(e.getMessage());
                }

            }

        }

    private void getPassword(Person person, HttpServletRequest request, List<String> errors) {
        String password = request.getParameter("password").trim();
        try {
            person.setPasswordHashed(password);
            request.setAttribute("passwordClass", "has-succes");
            System.out.println("okeeeee");
        }
        catch (Exception e) {
            request.setAttribute("passwordClass", "has-error");
            errors.add(e.getMessage());
        }
    }

    private void getEmail(Person person, HttpServletRequest request, List<String> errors) {
        String email = request.getParameter("email");
        request.setAttribute("emailPreviousValue", email);
        try {
            person.setEmail(email);
            request.setAttribute("emailClass", "has-succes");
        }
        catch (Exception e) {
            request.setAttribute("emailClass", "has-error");
            errors.add(e.getMessage());
        }
    }

    private void getLastName(Person person, HttpServletRequest request, List<String> errors) {
        String lastName = request.getParameter("lastName");
        request.setAttribute("lastNamePreviousValue", lastName);
        try {
            person.setLastName(lastName);
            request.setAttribute("lastNameClass", "has-succes");
        }
        catch (Exception e) {
            request.setAttribute("lastNameClass", "has-error");
            errors.add(e.getMessage());
        }
    }


    private void getFirstName (Person person, HttpServletRequest request, List<String> errors){
        String firstName = request.getParameter("firstName");
        request.setAttribute("firstNamePreviousValue", firstName);
        try {
            person.setFirstName(firstName);
            request.setAttribute("firstNameClass", "has-succes");
        } catch (Exception e) {
            request.setAttribute("firstNameClass", "has-error");
            errors.add(e.getMessage());
        }
    }

    private void getUserId (Person person, HttpServletRequest request, List<String> errors){
        String userId = request.getParameter("userid");
        if(contactTracingService.personAlreadyInDb(userId)) {
            errors.add("Person is already in database");
        }
        try {
            person.setUserid(userId);
            request.setAttribute("userIdClass", "has-success");
        } catch (Exception e) {
            request.setAttribute("userIdClass", "has-error");
            errors.add(e.getMessage());
        }
    }
}
