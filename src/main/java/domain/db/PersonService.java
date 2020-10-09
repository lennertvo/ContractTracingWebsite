package domain.db;

import domain.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonService {
	private Map<String, Person> persons;
	
	public PersonService () {
		this.persons = new HashMap<>();
		Person administrator = new Person("admin", "admin@ucll.be", "t", "Ad", "Ministrator");
		add(administrator);
	}
	//
	public Person get(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		return persons.get(personId);
	}
	
	public List<Person> getAll(){
		return new ArrayList<Person>(persons.values());	
	}

	public void add(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if (persons.containsKey(person.getUserid())) {
			throw new DbException("User already exists");
		}
		persons.put(person.getUserid(), person);
	}
	
	public void update(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if(!persons.containsKey(person.getUserid())){
			throw new DbException("No person found");
		}
		persons.put(person.getUserid(), person);
	}
	
	public void delete(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		this.persons.remove(personId);
	}
	public Person findPerson(String userId){
		if(userId == null) {
			throw new IllegalArgumentException("No person to find with that user ID");
		}
		return this.persons.get(userId);
	}
	public Person getPersonIfAuthenticated(String userId, String password) {
		Person person = findPerson(userId);
		if(person != null && person.isCorrectPassword(password)){
			return person;
		}
		return null;
	}

	public int getNumberOfPersons() {
		return persons.size();
	}

}
