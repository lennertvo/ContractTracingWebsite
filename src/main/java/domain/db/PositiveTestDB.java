package domain.db;

import domain.model.PositiveTest;

import java.util.List;

public interface PositiveTestDB {

    void add(PositiveTest positiveTest);

    List<PositiveTest> getAll();


    PositiveTest getPositiveTestWithUserid(String userid);

    void deleteTestWithUserid(String userid);
}
