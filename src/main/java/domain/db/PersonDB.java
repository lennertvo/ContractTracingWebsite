package domain.db;

import domain.model.Person;

import java.util.List;

public interface PersonDB {

    void add(Person person);


    List<Person> getAll();



    void remove(String userid);


    Person get(String userid);
}
