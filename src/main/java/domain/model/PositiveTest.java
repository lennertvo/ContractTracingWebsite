package domain.model;


import java.sql.Date;
import java.time.LocalDate;

public class PositiveTest {
    private String userid;
    private Date date;

    public PositiveTest(){};

    public PositiveTest(String userid, Date date) {
        setUserid(userid);
        setDate(date);
    }

    public void setUserid(String userid) {
        if(userid == null || userid.trim().isEmpty()) {
            throw new DomainException("Userid can't be empty or null");
        }
        this.userid = userid;
    }

    public void setDate(Date date) {
        if(date == null) {
            throw new DomainException("Date can't be null");
        }
        if(date.after(Date.valueOf(LocalDate.now()))){
            throw new DomainException("Date must be before today");
        }
        this.date = date;
    }

    public String getUserid() {
        return userid;
    }

    public Date getDate() {
        return date;
    }
}
