package domain.service;

import domain.db.DbException;
import domain.db.VisitorDB;
import domain.db.VisitorDBSQL;
import domain.model.DomainException;
import domain.model.Person;
import domain.model.PositiveTest;
import domain.model.Visitor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class VisitorService {

    private VisitorDB  db = new VisitorDBSQL();


    public VisitorService(){

    }

    public void addVisitor(Visitor visitor) {
        if(visitor == null) throw new DbException("No visitor given");
        if(db.visitorAlreadyInDb(visitor.getFirstName(), visitor.getLastName(), visitor.getArrivalTime())) throw new DbException("Visitor already in DB");
        db.add(visitor);
    }

    public List<Visitor> getVisitors(){
        return db.getAll();
    }

    public List<Visitor> getVisitorsWithUserid(String userid) {
        return db.getAllWithUserid(userid);
    }

    public void update(Visitor visitor) {
        if(visitor == null){
            throw new DbException("No visitor given");
        }
        delete(visitor.getFirstName(), visitor.getLastName(), visitor.getArrivalTime());
        addVisitor(visitor);
    }

    public void delete(String firstname, String lastname, Timestamp arrivaltime) {
        if(firstname == null || firstname.trim().isEmpty() || lastname == null || lastname.trim().isEmpty()) {
            throw new DbException("No visitorid given");
        }
        db.remove(firstname, lastname, arrivaltime);
    }

    public Visitor getVisitor(String firstname, String lastname, Timestamp arrivaltime){
        if(firstname == null || firstname.trim().isEmpty() || lastname == null || lastname.trim().isEmpty()) throw new DbException("No visitorid given");
        if(!db.visitorAlreadyInDb(firstname, lastname, arrivaltime)) throw new DbException("visitor not in database");
        return db.get(firstname, lastname, arrivaltime);
    }

    public Visitor findVisitor(String firstname, String lastname, Timestamp arrivaltime) {
        if(firstname == null || firstname.trim().isEmpty() || lastname == null || lastname.trim().isEmpty()) throw new DbException("No visitor to find with that visitor id");
        return db.get(firstname, lastname, arrivaltime);
    }
    public List<Visitor> getAllContactsFromPersonWhenPositiveTest(PositiveTest positiveTest) {
        if(positiveTest == null){
            throw new DomainException("No positive test given");
        }
        return db.getAllContactsFromPersonWhenPositiveTest(positiveTest);
    }

    public List<Visitor> getAllContactsBetween2SpecificDates(LocalDate from, LocalDate until) {
        return db.getAllContactsBetween2SpecificDates(from, until);
    }

    public List<Visitor> getAllContactsFromUserBetween2SpecificDates(Person person, LocalDate from, LocalDate until){
        return db.getAllContactsFromUserBetween2SpecificDates(person, from, until);
    }

    public int getNumberOfVisitors() {
        return db.getAll().size();
    }




}
