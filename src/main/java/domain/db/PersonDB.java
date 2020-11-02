package domain.db;

import domain.model.Person;

import java.util.List;

public interface PersonDB {

    void add(Person person);


    List<Person> getAll();


    boolean personAlreadyInDb(String userid);



    void remove(String userid);


    Person get(String userid);
}
