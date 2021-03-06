package ui.controller;


import domain.model.Visitor;
import domain.service.ContactTracingService;
import domain.service.PersonService;
import domain.service.PositiveTestService;
import domain.service.VisitorService;

import java.util.ArrayList;

public class HandlerFactory{
    public RequestHandler getHandler(String handlerName, ContactTracingService model) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("ui.controller."+ handlerName);

            // Java 10
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(model);


        } catch (Exception e) {
            throw new RuntimeException("Deze pagina bestaat niet!!!!");
        }


        return handler;
    }


}
