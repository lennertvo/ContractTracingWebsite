package domain.db;

import domain.model.PositiveTest;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositiveTestDBSQL implements PositiveTestDB {
    private Connection connection;
    private String schema;


    public PositiveTestDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println("Schema: " + schema);
    }

    @Override
    public void add(PositiveTest positiveTest) {
        String sql = String.format("INSERT INTO %s.positieve_test(userid, date) values(?,?) on conflict (userid) do update set date = excluded.date;", this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, positiveTest.getUserid());
            statementSql.setDate(2, positiveTest.getDate());

            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public List<PositiveTest> getAll() {
        List<PositiveTest> positiveTests = new ArrayList<PositiveTest>();
        String sql = String.format("Select * from %s.positieve_test order by date, userid", this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {

                PositiveTest positiveTest = createPositiveTest(result);
                positiveTests.add(positiveTest);
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return positiveTests;
    }


    @Override
    public PositiveTest getPositiveTestWithUserid(String userid) {
        String sql = String.format("SELECT * FROM %s.positieve_test where userid = ?", this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);
            ResultSet result = statementSql.executeQuery();

            if(result.next()) {
                return createPositiveTest(result);
            }
            else{
                return null;
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteTestWithUserid(String userid) {
        String sql = String.format("SELECT * FROM %s.positieve_test where userid = ?", this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

    }

    private PositiveTest createPositiveTest(ResultSet result) throws SQLException {
        String userid = result.getString("userid");
        Date date = result.getDate("date");
        return new PositiveTest(userid, date);
    }


}
