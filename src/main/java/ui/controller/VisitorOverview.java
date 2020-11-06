package ui.controller;

import domain.model.Visitor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class VisitorOverview extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Visitor> visitors = visitorService.getVisitors();
        request.setAttribute("visitors", visitors);
        return "addVisitor.jsp";
        
    }
}
