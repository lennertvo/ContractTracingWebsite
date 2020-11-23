package domain.db;

import domain.model.Person;
import domain.model.Role;
import util.DbConnectionService;


import java.sql.*;

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
        String sql = String.format("Insert into %s.gebruiker(userid, email, firstname, lastname, password, role) VALUES(?, ?, ?, ?, ?, ?)", schema);


        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, person.getUserid());
            statementSQL.setString(2, person.getEmail());
            statementSQL.setString(3, person.getFirstName());
            statementSQL.setString(4, person.getLastName());
            statementSQL.setString(5, person.getPassword());
            statementSQL.setString(6, person.getRole().toString());
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
                Person person = createPerson(result);
                persons.add(person);
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return persons;
    }

    @Override
    public boolean personAlreadyInDb(String userid) {
        String sql = String.format("SELECT * from %s.gebruiker where userid = ?", this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);
            ResultSet result = statementSql.executeQuery();
            return result.next();
        }
        catch (SQLException e) {
            throw new DbException(e);
        }
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
            result.next();

            return createPerson(result);



        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    private Person createPerson(ResultSet result) throws SQLException{
        String userid = result.getString("userid");
        String email = result.getString("email");
        String firstname = result.getString("firstname");
        String lastname = result.getString("lastname");
        String password = result.getString("password");
        String roleAsString = result.getString("role");
        Role role = Role.valueOf(roleAsString);

        return new Person(userid, email, firstname, lastname, password, role);
    }

}
