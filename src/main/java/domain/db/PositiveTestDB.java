package domain.db;

import domain.model.PositiveTest;
import java.sql.Date;

import java.util.List;

public interface PositiveTestDB {

    void add(PositiveTest positiveTest);

    List<PositiveTest> getAll();



    List<PositiveTest> getAllPositiveTests();

    List<PositiveTest> getAllPositiveTestsOnSpecificDate(Date date);



    PositiveTest getPositiveTestWithUserid(String userid);

    void deleteTestWithUserid(String userid);
}
