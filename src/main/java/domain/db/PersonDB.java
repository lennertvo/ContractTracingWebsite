package domain.db;

import domain.model.Person;

import java.sql.Date;
import java.util.List;

public interface PersonDB {

    void add(Person person);


    List<Person> getAll();

    List<Person> getAllPersonsWhoAlsoInPositiveTest();

    List<Person> getAllPositiveUserOnSpecificDate(Date date);


    boolean personAlreadyInDb(String userid);


    void remove(String userid);


    Person get(String userid);
}
