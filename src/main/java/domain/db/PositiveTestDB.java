package domain.db;

import domain.model.PositiveTest;

import java.sql.Date;
import java.util.List;

public interface PositiveTestDB {

    void add(PositiveTest positiveTest);

    List<PositiveTest> getAll();


    PositiveTest getPositiveTestWithUserid(String userid);

    List<PositiveTest> getAllPositiveTests();

    List<PositiveTest> getAllPositiveTestsOnSpecificDate(Date date);


    void deleteTestWithUserid(String userid);
}
