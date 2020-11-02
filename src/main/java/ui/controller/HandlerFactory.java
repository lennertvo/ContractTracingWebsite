package ui.controller;


import domain.service.PersonService;
import domain.service.VisitorService;

public class HandlerFactory{
    public RequestHandler getHandler(String handlerName, PersonService model, VisitorService visitorModel) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("ui.controller."+ handlerName);

            // Java 10
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(model, visitorModel);


        } catch (Exception e) {
            throw new RuntimeException("Deze pagina bestaat niet!!!!");
        }

        return handler;
    }


}
