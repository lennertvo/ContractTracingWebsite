package domain.db;



import domain.model.Person;
import domain.model.PositiveTest;
import domain.model.Visitor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VisitorDB {

    void add(Visitor visitor);


    List<Visitor> getAll();


    List<Visitor> getAllWithUserid(String userid);


    boolean visitorAlreadyInDb(String firstname, String lastname, Timestamp arrivaltime);


    void remove(String firstname, String lastname, Timestamp arrivaltime);


    Visitor get(String firstname, String lastname, Timestamp arrivaltime);


    List<Visitor> getWithFnameAndLname(String firstname, String lastname);

    List<Visitor> getAllContactsFromPersonWhenPositiveTest(PositiveTest positiveTest);

    List<Visitor> getAllContactsBetween2SpecificDates(LocalDate from, LocalDate until);

    List<Visitor> getAllContactsFromUserBetween2SpecificDates(Person person, LocalDate from, LocalDate until);
}
