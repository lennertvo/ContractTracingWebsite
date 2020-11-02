package domain.db;

import domain.model.Person;
import domain.model.Visitor;

import util.DbConnectionService;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VisitorDBSQL implements VisitorDB {
    private Connection connection;
    private String schema;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss");



    public VisitorDBSQL() {
        this.connection= DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println("Schema: " + schema);
    }


    @Override
    public void add(Visitor visitor) {
        if(visitor == null){
            throw new DbException("Nothing to add.");
        }
        String sql = String.format("Insert into %s.bezoeker( firstname, lastname, email, phonenumber, arrivaltime) values( ?, ?, ?, ?, ?");
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, visitor.getFirstName());
            statementSql.setString(2, visitor.getLastName());
            statementSql.setString(3, visitor.getEmail());
            statementSql.setString(4, visitor.getPhoneNumber());
            statementSql.setString(5, visitor.getArrivalTimeInString());
            statementSql.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Visitor> getAll() {
        List<Visitor> visitors = new ArrayList<Visitor>();
        String sql = String.format("SELECT * FROM %s.bezoeker", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String email = result.getString("email");
                String phonenumber = result.getString("phonenumber");
                String arrivaltime = result.getString("arrivaltime");
                LocalDateTime arrivalTime = LocalDateTime.parse(arrivaltime);
                Visitor visitor = new Visitor(firstname, lastname, email, phonenumber, arrivalTime);
                visitors.add(visitor);
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return visitors;
    }

    @Override
    public boolean visitorAlreadyInDb(String firstname, String lastname, LocalDateTime arrivaltime) {
        String sql = String.format("SELECT * FROM %s.bezoeker where firstname = ? and lastname = ? and arrivaltime = ?", this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, firstname);
            statementSql.setString(2, lastname);

            statementSql.setString(3, arrivaltime.format(formatter));
            ResultSet result = statementSql.executeQuery();
            return result.next();
        }
        catch (SQLException e) {
            throw new DbException(e);
        }
    }


    @Override
    public void remove(String firstname, String lastname, LocalDateTime arrivaltime) {
        String sql = String.format("SELECT * FROM %s.bezoeker where firstname = ? and lastname = ? and arrivaltime = ?)", this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, firstname);
            statementSql.setString(2, lastname);
            statementSql.setTimestamp(3, Timestamp.valueOf(arrivaltime));
            statementSql.setString(3, arrivaltime.format(formatter));
            statementSql.execute();
        }
        catch (SQLException  e) {
            throw new DbException(e);
        }

    }

    @Override
    public Visitor get(String firstname, String lastname, LocalDateTime arrivaltime) {
        String sql = String.format("SELECT * FROM %s.bezoeker where firstname = ? and lastname = ? and arrivaltime = ?)", this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, firstname);
            statementSql.setString(2, lastname);
            statementSql.setString(3, arrivaltime.format(formatter));
            ResultSet result = statementSql.executeQuery();
            result.next();
            String email = result.getString("email");
            String phonenumber = result.getString("phonenumber");
            Visitor visitor = new Visitor(firstname, lastname, email, phonenumber, arrivaltime);
            return visitor;

        }
        catch (SQLException e) {
            throw new DbException(e);
        }
    }
}
