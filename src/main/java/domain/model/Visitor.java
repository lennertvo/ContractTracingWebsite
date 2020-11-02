package domain.model;


import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Visitor {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss");
    String firstName;
    String LastName;
    String email;
    String  phoneNumber;
    LocalDateTime arrivalTime;


    public Visitor(String firstName, String lastName, String email, String phoneNumber, LocalDateTime arrivalTime){
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setArrivalTime(arrivalTime);

    }

    public Visitor(){

    }

    private String formatPhoneNumberToString(int phoneNumber){
        if(phoneNumber == 0) return null;
        return Integer.toString(phoneNumber);
    }
    private String formatDateTimeToString(LocalDateTime dateTime){
        if(dateTime == null) return null;
        return dateTime.format(formatter);
    }

    private LocalDateTime formatStringToDateTime(String localdateString) {
        if(localdateString.isEmpty() || localdateString.trim().isEmpty()) {
            throw new DomainException("Localdate String can not be empty");
        }
        return LocalDateTime.parse(localdateString, formatter);
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

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber == null || phoneNumber.trim().isEmpty()){
            throw new DomainException("No phonenumber given");
        }
        this.phoneNumber = phoneNumber;
    }



    public void setArrivalTime(LocalDateTime arrivaltime) {
        if(arrivaltime == null) {
            this.arrivalTime = LocalDateTime.now();
        }
        this.arrivalTime = arrivaltime;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }



    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public String getArrivalTimeInString() {
        return formatDateTimeToString(arrivalTime);
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
