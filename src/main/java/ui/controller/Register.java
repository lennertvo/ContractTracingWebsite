package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Register extends RequestHandler {


    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return "register.jsp";
    }
}

