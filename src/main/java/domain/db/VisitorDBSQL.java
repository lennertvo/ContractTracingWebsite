package domain.db;

import domain.model.Person;
import domain.model.PositiveTest;
import domain.model.Visitor;

import util.DbConnectionService;

import java.sql.*;
import java.time.LocalDate;
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
        String sql = String.format("Insert into %s.bezoeker( firstname, lastname, email, phonenumber, arrivaltime, userid) values( ?, ?, ?, ?, ?, ?)",  this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, visitor.getFirstName());
            statementSql.setString(2, visitor.getLastName());
            statementSql.setString(3, visitor.getEmail());
            statementSql.setString(4, visitor.getPhoneNumber());
            statementSql.setObject(5, visitor.getArrivalTime());
            statementSql.setString(6, visitor.getUserid());
            statementSql.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Visitor> getAll() {
        List<Visitor> visitors = new ArrayList<Visitor>();
        String sql = String.format("SELECT * FROM %s.bezoeker order by arrivaltime", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
               Visitor visitor = createVisitor(result);
               visitors.add(visitor);
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return visitors;
    }

    @Override
    public List<Visitor> getAllWithUserid(String userid) {
        List<Visitor> visitors = new ArrayList<Visitor>();
        String sql = String.format("SELECT * FROM %s.bezoeker where userid = ? order by arrivaltime", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                Visitor visitor = createVisitor(result);
                visitors.add(visitor);
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return visitors;
    }

    @Override
    public List<Visitor> getWithFnameAndLname(String firstname, String lastname) {
        List<Visitor> visitorss = new ArrayList<Visitor>();
        String sql = String.format("Select * from %s.bezoeker where firstname = ? and lastname = ? order by arrivaltime", this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, firstname);
            statementSql.setString(2, lastname);
            ResultSet result = statementSql.executeQuery();
            while(result.next()){
                Visitor visitor = createVisitor(result);
                visitorss.add(visitor);
            }
        }
        catch (SQLException e) {
            throw new DbException(e);
        }
        return visitorss;
    }

    @Override
    public List<Visitor> getAllContactsFromPersonWhenPositiveTest(PositiveTest positiveTest) {
       List<Visitor> visitors = new ArrayList<Visitor>();
        String sql = String.format("SELECT * FROM %s.bezoeker where userid = ? and arrivaltime >= ? and arrivaltime <= ? order by lastname, firstname, arrivaltime", this.schema);
       try {
           PreparedStatement statementSql = connection.prepareStatement(sql);
            LocalDate s = positiveTest.getDate().toLocalDate();
           System.out.println(s);
           LocalDate dateMin3Days = s.minusDays(3);
           Date dateMin3 = java.sql.Date.valueOf(dateMin3Days);
           System.out.println(dateMin3);
           System.out.println(positiveTest.getDate());
           System.out.println(positiveTest.getDate());
           statementSql.setString(1, positiveTest.getUserid());
           statementSql.setDate(2, dateMin3);
           statementSql.setDate(3, positiveTest.getDate());
           ResultSet result = statementSql.executeQuery();

           while (result.next()) {
               Visitor visitor = createVisitor(result);
               visitors.add(visitor);
           }
       }catch (SQLException e) {
           throw new DbException(e.getMessage(), e);
       }
       return visitors;
    }

    @Override
    public List<Visitor> getAllContactsBetween2SpecificDates(LocalDate from, LocalDate until) {
        List<Visitor> visitors = new ArrayList<Visitor>();
        String sql = String.format("SELECT * FROM %s.bezoeker where arrivaltime >= ? and arrivaltime <= ? order by arrivaltime", schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setDate(1, Date.valueOf(from));
            statementSql.setDate(2, Date.valueOf(until));
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {
                Visitor visitor = createVisitor(result);
                visitors.add(visitor);
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return visitors;
    }

    @Override
    public List<Visitor> getAllContactsFromUserBetween2SpecificDates(Person person, LocalDate from, LocalDate until) {
        List<Visitor> visitors = new ArrayList<Visitor>();
        String sql = String.format("SELECT * FROM %s.bezoeker where userid = ? and arrivaltime >= ? and arrivaltime <= ? order by arrivaltime", schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, person.getUserid());
            statementSql.setDate(2, Date.valueOf(from));
            statementSql.setDate(3, Date.valueOf(until));
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {
                Visitor visitor = createVisitor(result);
                visitors.add(visitor);
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return visitors;
    }


    @Override
    public boolean visitorAlreadyInDb(String firstname, String lastname, Timestamp arrivaltime) {
        String sql = String.format("SELECT * FROM %s.bezoeker where firstname = ? and lastname = ? and arrivaltime = ?", this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, firstname);
            statementSql.setString(2, lastname);

            statementSql.setObject(3, arrivaltime);
            ResultSet result = statementSql.executeQuery();
            return result.next();
        }
        catch (SQLException e) {
            throw new DbException(e);
        }
    }


    @Override
    public void remove(String firstname, String lastname, Timestamp arrivaltime) {
        String sql = String.format("SELECT * FROM %s.bezoeker where firstname = ? and lastname = ? and arrivaltime = ?)", this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, firstname);
            statementSql.setString(2, lastname);
            statementSql.setObject(3, arrivaltime);
            statementSql.execute();
        }
        catch (SQLException  e) {
            throw new DbException(e);
        }

    }

    @Override
    public Visitor get(String firstname, String lastname, Timestamp arrivaltime) {
        String sql = String.format("SELECT * FROM %s.bezoeker where firstname = ? and lastname = ? and arrivaltime = ?)", this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, firstname);
            statementSql.setString(2, lastname);
            statementSql.setObject(3, arrivaltime);
            ResultSet result = statementSql.executeQuery();
            result.next();
            String email = result.getString("email");
            String phonenumber = result.getString("phonenumber");
            String userid = result.getString("userid");
            Visitor visitor = new Visitor(firstname, lastname, email, phonenumber, arrivaltime, userid);
            return visitor;

        }
        catch (SQLException e) {
            throw new DbException(e);
        }
    }




    private Visitor createVisitor(ResultSet result) throws SQLException{
        String firstname = result.getString("firstname");
        String lastname = result.getString("lastname");
        String email = result.getString("email");
        String phonenumber = result.getString("phonenumber");
        String userid = result.getString("userid");
        Timestamp arrivaltime = result.getObject("arrivaltime", Timestamp.class);

        return new Visitor(firstname, lastname, email, phonenumber, arrivaltime, userid);
    }
}
