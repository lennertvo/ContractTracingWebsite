package ui.authorization;

import domain.model.Person;
import domain.model.Role;
import ui.authorization.NotAuthorizedException;

import javax.servlet.http.HttpServletRequest;

public class Utility {

    public static void checkRole(HttpServletRequest request, Role[] roles) {
        boolean found = false;
        Person person = (Person) request.getSession().getAttribute("user");
        if(person != null){
            for(Role role : roles) {
                if (person.getRole().equals(role)) {
                    found = true;
                    break;
                }
            }
        }
        if(!found) {
            throw new NotAuthorizedException();
        }

    }


}