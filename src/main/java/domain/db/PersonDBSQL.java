package domain.db;

import domain.model.Person;
import util.DbConnectionService;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDBSQL implements PersonDB {
    private Connection connection;
    private String schema;


    public PersonDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println( "Schema: " + schema);
    }


    @Override
    public void add(Person person) {
        if(person == null){
            throw new DbException("Nothing to add.");
        }
        String sql = String.format("Insert into %s.gebruiker(userid, email, firstname, lastname, password) VALUES(?, ?, ?, ?, ?)", schema);


        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, person.getUserid());
            statementSQL.setString(2, person.getEmail());
            statementSQL.setString(3, person.getFirstName());
            statementSQL.setString(4, person.getLastName());
            statementSQL.setString(5, person.getPassword());
            statementSQL.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<Person>();
        String sql = String.format("SELECT * FROM %s.gebruiker", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()){
                String userid = result.getString("userid");
                String firstname = result.getString("email");
                String lastname = result.getString("firstname");
                String email = result.getString("lastname");
                String password = result.getString("password");
                Person person = new Person(userid, email, firstname, lastname, password);
                persons.add(person);
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return persons;
    }

    @Override
    public void remove(String userid) {
        String sql = String.format("Delete from %s.gebruiker where userid = ?", this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);
            statementSql.execute();
        }
        catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public Person get(String userid) {
        String sql = String.format("Select * from %s.gebruiker where userid = ?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);
            ResultSet result = statementSql.executeQuery();
            String userId = result.getString("userid");
            String firstname = result.getString("email");
            String lastname = result.getString("firstname");
            String email = result.getString("lastname");
            String password = result.getString("password");
            Person person = new Person(userId, email, firstname, lastname, password);
            return person;
        }
        catch (SQLException e) {
            throw new DbException(e);
        }
    }



}
