package domain.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Person {
	private String userid;
	private String email;
	private String password;
	private String firstName;
	private String lastName;



	public Person(String userid, String email, String firstName, String lastName,  String password) {
		setUserid(userid);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);

	}

	public Person() {
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		if(userid.isEmpty()){
			throw new IllegalArgumentException("No userid given");
		}
		this.userid = userid;
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
	
	
	public String getEmail() {
		return email;
	}


	
	public String getPassword() {
		return password;
	}
	
	public boolean isCorrectPassword(String password){
		if(password.isEmpty() || password.trim().isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		return this.password.equals(hashPassword(password));
	}

	public void setPassword(String password) {
		if(password.isEmpty() || password.trim().isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}
	public void setPasswordHashed(String password) {
		if(password == null || password.trim().isEmpty()){
			throw new DomainException("No password given");
		}
		this.password = hashPassword(password);
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}


	private static String hashPassword(String password) {

		try {
			//create MessageDigest
			MessageDigest crypt = MessageDigest.getInstance("SHA-512");
			//reset
			crypt.reset();
			//update
			byte[] passwordBytes = password.getBytes("UTF-8");
			crypt.update(passwordBytes);
			//digest
			byte[] digest = crypt.digest();
			//convert to String
			BigInteger digestAsBigInteger = new BigInteger(1, digest);
			//return hashed password
			return digestAsBigInteger.toString(16);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			throw new DomainException(e.getMessage());
		}
	}








	@Override
	public String toString(){
		return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail() + ", " ;
	}	
}
