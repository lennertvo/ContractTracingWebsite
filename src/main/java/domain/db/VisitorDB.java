package domain.db;



import domain.model.Visitor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface VisitorDB {

    void add(Visitor visitor);


    List<Visitor> getAll();


    boolean visitorAlreadyInDb(String firstname, String lastname, Timestamp arrivaltime);


    void remove(String firstname, String lastname, Timestamp arrivaltime);


    Visitor get(String firstname, String lastname, Timestamp arrivaltime);


    List<Visitor> getWithFnameAndLname(String firstname, String lastname);
}
