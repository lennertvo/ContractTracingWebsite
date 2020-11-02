package ui.controller;

import domain.service.PersonService;
import domain.service.VisitorService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public abstract class RequestHandler {

    protected PersonService service;
    protected VisitorService visitorService;


    public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response);
    public void setModel (PersonService personService, VisitorService visitorService) {
        this.service = personService;
        this.visitorService = visitorService;
    }



    public PersonService getService() {
        System.out.println("dit is ook oke");
        return service;
    }
}
