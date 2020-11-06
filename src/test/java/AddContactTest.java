import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.servlet.annotation.WebListener;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddContactTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/opdracht_web3_war_exploded/Controller";

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenne\\2TI\\Web3\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?command=AddVisitorForm");
    }


    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Register_AllFieldsFilledInCorrectly_VisitorIsRegistered(){
        submitForm("Jan", "Janssens", "jan.janssens@hotmail.com" , "0412345678");

        String title = driver.getTitle();
        assertEquals("Add Visitor",title);

        driver.get("http://localhost:8080/opdracht_web3_war_exploded/index.jsp");
        fillOutField("useridLogIn", "admin");
        fillOutField("passwordLogIn", "t");
        WebElement button=driver.findElement(By.id("login"));
        button.click();

        driver.get(path +"?command=VisitorOverview");

        ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains("Jan") &&  listItem.getText().contains("Janssens")) {
                found=true;
            }
        }
        assertTrue(found);
    }

    @Test
    public void test_Register_AllFieldsFilledInCorrectly_VisitorIsRegisteredAndFielsAreBackEmpty(){
        submitForm("Jan", "Janssens", "jan.janssens@hotmail.com" , "0412345678");

        String title = driver.getTitle();
        assertEquals("Add Visitor",title);

        driver.get("http://localhost:8080/opdracht_web3_war_exploded/index.jsp");
        fillOutField("useridLogIn", "admin");
        fillOutField("passwordLogIn", "t");
        WebElement button=driver.findElement(By.id("login"));
        button.click();

        driver.get(path +"?command=VisitorOverview");

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("",fieldLastName.getAttribute("value"));


        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("",fieldEmail.getAttribute("value"));


        WebElement fieldPhoneNumber=driver.findElement(By.id("phoneNumber"));
        assertEquals("", fieldPhoneNumber.getAttribute("value"));


        ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains("Jan") &&  listItem.getText().contains("Janssens")) {
                found=true;
            }
        }
        assertTrue(found);
    }

    @Test
    public void test_Register_FirstNameFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept() {
        submitForm("", "Janssens", "jan.janssens@hotmail.com", "0412345678");

        String title = driver.getTitle();
        assertEquals("Add Visitor",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No firstname given", errorMsg.getText());

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));

        WebElement fieldPhoneNumber=driver.findElement(By.id("phoneNumber"));
        assertEquals("0412345678", fieldPhoneNumber.getAttribute("value"));

    }

    @Test
    public void test_Register_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){
        submitForm("Jan", "", "jan.janssens@hotmail.com", "0412345678");

        String title = driver.getTitle();
        assertEquals("Add Visitor",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No lastname given", errorMsg.getText());

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));

        WebElement fieldPhoneNumber=driver.findElement(By.id("phoneNumber"));
        assertEquals("0412345678", fieldPhoneNumber.getAttribute("value"));
    }

    @Test
    public void test_Register_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        submitForm("Jan", "Janssens", "", "0412345678");

        String title = driver.getTitle();
        assertEquals("Add Visitor",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No email given", errorMsg.getText());


        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("",fieldEmail.getAttribute("value"));

        WebElement fieldPhoneNumber=driver.findElement(By.id("phoneNumber"));
        assertEquals("0412345678", fieldPhoneNumber.getAttribute("value"));
    }

    @Test
    public void test_Register_EmailNotFilledInCorrectly_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        submitForm("Jan", "Janssens", "Janssens.gmail.com", "0412345678");

        String title = driver.getTitle();
        assertEquals("Add Visitor",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("Email not valid", errorMsg.getText());


        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("Janssens.gmail.com",fieldEmail.getAttribute("value"));

        WebElement fieldPhoneNumber=driver.findElement(By.id("phoneNumber"));
        assertEquals("0412345678", fieldPhoneNumber.getAttribute("value"));
    }


    @Test
    public void test_Register_PhonenumberNotFilledIn_ErrorMessageGivenForPhonenumberAndOtherFieldsValueKept(){
        submitForm("Jan", "Janssens", "jan.janssens@hotmail.com", "");

        String title = driver.getTitle();
        assertEquals("Add Visitor",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No phonenumber given", errorMsg.getText());

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));

        WebElement fieldPhoneNumber=driver.findElement(By.id("phoneNumber"));
        assertEquals("", fieldPhoneNumber.getAttribute("value"));
    }

    @Test
    public void test_Register_PhonenumberNotFilledInCorrectly_ErrorMessageGivenForphonenumberherFieldsValueKept(){
        submitForm("Jan", "Janssens", "jan.janssens@hotmail.com", "123");

        String title = driver.getTitle();
        assertEquals("Add Visitor",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("Phonenumber not valid", errorMsg.getText());

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));

        WebElement fieldPhoneNumber=driver.findElement(By.id("phoneNumber"));
        assertEquals("123", fieldPhoneNumber.getAttribute("value"));
    }

    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }


    private void submitForm(String firstname, String lastname,String email, String phonenumber) {
        fillOutField("firstName", firstname);
        fillOutField("lastName", lastname);
        fillOutField("email",email);
        fillOutField("phoneNumber", phonenumber);

        WebElement button=driver.findElement(By.id("addVisitor"));
        button.click();

    }

}
