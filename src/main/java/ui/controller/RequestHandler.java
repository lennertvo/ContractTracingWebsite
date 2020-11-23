package ui.controller;

import domain.model.Visitor;
import domain.service.ContactTracingService;
import domain.service.PersonService;
import domain.service.PositiveTestService;
import domain.service.VisitorService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public abstract class RequestHandler {
    protected ContactTracingService contactTracingService;



    public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response);
    public void setModel (ContactTracingService contactTracingService) {
        this.contactTracingService = contactTracingService;

    }


    public ContactTracingService getService() {
        System.out.println("dit is ook oke");
        return contactTracingService;
    }
}
