package domain.service;

import domain.db.DbException;
import domain.db.PositiveTestDB;
import domain.db.PositiveTestDBSQL;
import domain.model.DomainException;
import domain.model.PositiveTest;

import java.util.List;


public class PositiveTestService {
    private PositiveTestDB db = new PositiveTestDBSQL();

    public PositiveTestService() {
    }

    ;


    public void addPositiveTest(PositiveTest positiveTest) {
        if (positiveTest == null) {
            throw new DomainException("No userid given");
        }
        db.add(positiveTest);
    }

    public List<PositiveTest> getAllPositiveTests() {
        return db.getAll();
    }

    public void deletePositiveTestWithUserid(String userid) {
        if (userid == null || userid.trim().isEmpty()) {
            throw new DomainException("No userid given");
        }
        db.deleteTestWithUserid(userid);
    }

    public PositiveTest getPositiveTestWithUserid(String userid) {
        if (userid == null || userid.trim().isEmpty()) {
            throw new DomainException("No userid given");
        }
        return db.getPositiveTestWithUserid(userid);
    }


}
