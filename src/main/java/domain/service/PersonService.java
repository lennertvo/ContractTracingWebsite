package domain.service;

import domain.db.DbException;
import domain.db.PersonDB;
import domain.db.PersonDBSQL;
import domain.model.Person;



import java.sql.Date;
import java.util.*;

public class PersonService {
	private PersonDB db = new PersonDBSQL();

	
	public PersonService () {

	}
//


	public void addPerson(Person person){
		if(person == null) throw new DbException("No person given");
		if(db.personAlreadyInDb(person.getUserid())) throw new DbException("User already in DB");
		db.add(person);
	}


	public List<Person> getPersons() {
		return db.getAll();
	}

	public List<Person> getAllPersonsWhoAlsoInPositiveTest(){
		return db.getAllPersonsWhoAlsoInPositiveTest();
	}
	public List<Person> getAllPositiveUserOnSpecificDate(Date date) {
		return db.getAllPositiveUserOnSpecificDate(date);
	}

	public void update(Person person){
		if(person == null){
			throw new DbException("No person given");
		}

		delete(person.getUserid());
		addPerson(person);
	}

	public void delete(String userid){
		if(userid == null || userid.trim().isEmpty()){
			throw new DbException("No userid given");
		}
		db.remove(userid);
	}
	public Person getPerson(String userId) {
		if(userId == null || userId.trim().isEmpty()) throw new DbException("No userid given");
		if(!db.personAlreadyInDb(userId)) throw new DbException("Person not in database");

		return db.get(userId);
	}
	public Person findPerson(String userId){
		if(userId == null) {
			throw new IllegalArgumentException("No person to find with that user ID");
		}
		return db.get(userId);
	}
	public Person getPersonIfAuthenticated(String userId, String password){
		Person person = findPerson(userId);
		if(person != null && person.isCorrectPassword(password)){
			return person;
		}
		return null;
	}




	public int getNumberOfPersons() {
		return db.getAll().size();
	}

}
