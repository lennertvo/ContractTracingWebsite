package domain.model;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Visitor {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss");
    String visitorid;
    String firstName;
    String LastName;
    String email;
    int phoneNumber;
    LocalDateTime arrivalTime;


    public Visitor(String visitorid, String firstName, String lastName, String email, int phoneNumber){
        setVisitorid(visitorid);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setArrivalTime();

    }

    public Visitor(){

    }

    private String formatPhoneNumberToString(int phoneNumber){
        if(phoneNumber == 0) return null;
        return Integer.toString(phoneNumber);
    }
    private String formatDateTimeToString(LocalDate date){
        if(date == null) return null;
        return date.format(formatter);
    }

    public void setVisitorid(String visitorid) {
        if(visitorid == null || visitorid.trim().isEmpty()) {
            throw new DomainException("No visitorid given");
        }
        this.visitorid = visitorid;
    }

    public void setFirstName(String firstName) {
        if(firstName == null || firstName.trim().isEmpty()){
            throw new DomainException("No firstname given");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if(lastName == null || lastName.trim().isEmpty()) {
            throw new DomainException("No lastname given");
        }
        LastName = lastName;
    }

    public void setEmail(String email) {
        if(email.isEmpty()){
            throw new IllegalArgumentException("No email given");
        }
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new IllegalArgumentException("Email not valid");
        }
        this.email = email;
    }

    public void setPhoneNumber(int phoneNumber) {
        if(phoneNumber == 0){
            throw new DomainException("No phonenumber given");
        }
        this.phoneNumber = phoneNumber;
    }



    public void setArrivalTime() {
        this.arrivalTime = LocalDateTime.now();
    }

    public String getVisitorid() {
        return visitorid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }



    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "formatter=" + formatter +
                ", visitorid='" + visitorid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
