package domain.db;



import domain.model.Visitor;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitorDB {

    void add(Visitor visitor);


    List<Visitor> getAll();


    boolean visitorAlreadyInDb(String firstname, String lastname, LocalDateTime arrivaltime);



    void remove(String firstname, String lastname, LocalDateTime arrivaltime);


    Visitor get(String firstname, String lastname, LocalDateTime arrivaltime);
}
