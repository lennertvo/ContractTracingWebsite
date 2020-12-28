package domain.db;

import domain.model.Person;

import java.sql.Date;
import java.util.List;

public interface PersonDB {

    void add(Person person);


    List<Person> getAll();


    boolean personAlreadyInDb(String userid);

    List<Person> getAllPersonsWhoAlsoInPositiveTest();

    List<Person> getAllPositiveUserOnSpecificDate(Date date);

    void remove(String userid);
    void removePositiveTestFromUser(String userid);
    void removeVisitorsFromUser(String userid);


    Person get(String userid);
}
