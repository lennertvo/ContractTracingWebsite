package domain.service;


import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;
import domain.model.PositiveTest;
import domain.model.Visitor;
import domain.service.PersonService;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class ContactTracingService {
    private PersonService personService = new PersonService();
    private VisitorService visitorService = new VisitorService();
    private PositiveTestService positiveTestService = new PositiveTestService();

    public ContactTracingService() {
    }

    // personService
    public void addPerson(Person person) {
        personService.addPerson(person);
    }
    public List<Person> getAllPersonsWhoAlsoInPositiveTest(){
        return personService.getAllPersonsWhoAlsoInPositiveTest();
    }
    public List<Person> getAllPositiveUserOnSpecificDate(Date date) {
        return personService.getAllPositiveUserOnSpecificDate(date);
    }
    public boolean personAlreadyInDb(String userid) {
        return personService.personAlreadyInDb(userid);
    }


    public List<Person> getPersons() {
        return personService.getPersons();
    }

    public void update(Person person) {
        personService.update(person);
    }

    public void delete(String userid) {
        personService.delete(userid);
    }

    public Person getPerson(String userId) {
        return personService.getPerson(userId);
    }

    public Person findPerson(String userId) {
        return personService.findPerson(userId);
    }

    public Person getPersonIfAuthenticated(String userId, String password) {
        return personService.getPersonIfAuthenticated(userId, password);
    }


    public int getNumberOfPersons() {
        return personService.getNumberOfPersons();
    }




    //visitorService
    public void addVisitor(Visitor visitor) {
        visitorService.addVisitor(visitor);
    }

    public List<Visitor> getVisitors(){
        return visitorService.getVisitors();
    }

    public List<Visitor> getVisitorsWithUserid(String userid) {
        return visitorService.getVisitorsWithUserid(userid);
    }

    public void update(Visitor visitor) {
        visitorService.update(visitor);
    }

    public void delete(String firstname, String lastname, Timestamp arrivaltime) {
        visitorService.delete(firstname, lastname, arrivaltime);
    }

    public Visitor getVisitor(String firstname, String lastname, Timestamp arrivaltime){
       return visitorService.getVisitor(firstname, lastname, arrivaltime);
    }

    public Visitor findVisitor(String firstname, String lastname, Timestamp arrivaltime) {
        return visitorService.findVisitor(firstname, lastname, arrivaltime);
    }
    public List<Visitor> getAllContactsFromPersonWhenPositiveTest(PositiveTest positiveTest) {
        return visitorService.getAllContactsFromPersonWhenPositiveTest(positiveTest);
    }

    public int getNumberOfVisitors() {
        return visitorService.getNumberOfVisitors();
    }

    public List<Visitor> getAllContactsBetween2SpecificDates(LocalDate from, LocalDate until) {
        return visitorService.getAllContactsBetween2SpecificDates(from, until);
    }

    public List<Visitor> getAllContactsFromUserBetween2SpecificDates(Person person, LocalDate from, LocalDate until){
        return visitorService.getAllContactsFromUserBetween2SpecificDates(person, from, until);
    }




    //positiveTestService

    public void addPositiveTest(PositiveTest positiveTest) {
        positiveTestService.addPositiveTest(positiveTest);
    }

    public List<PositiveTest> getAllPositiveTests() {
        return positiveTestService.getAllPositiveTests();
    }

    public void deletePositiveTestWithUserid(String userid) {
        positiveTestService.deletePositiveTestWithUserid(userid);
    }

    public PositiveTest getPositiveTestWithUserid(String userid) {
        return positiveTestService.getPositiveTestWithUserid(userid);
    }




}
