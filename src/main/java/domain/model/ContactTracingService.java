package domain.model;


import domain.service.PersonService;

import java.util.List;

public class ContactTracingService {
    private PersonService personDb = new PersonService();

    public ContactTracingService() {
    }

    public Person getPerson(String personId) {
        return getPersonDb().getPerson(personId);
    }

    public List<Person> getPersons() {
        return getPersonDb().getPersons();
    }

    public void addPerson(Person person) {
        getPersonDb().addPerson(person);
    }

    public void updatePersons(Person person) {
        getPersonDb().update(person);
    }

    public void deletePerson(String id) {
        getPersonDb().delete(id);
    }

    private PersonService getPersonDb() {
        return personDb;
    }

}
