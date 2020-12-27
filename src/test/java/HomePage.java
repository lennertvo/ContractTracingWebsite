import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends Page {

    public HomePage(WebDriver driver){
        super(driver);
        this.driver.get(getPath());
    }

    @FindBy(id="useridLogIn")
    private WebElement useridLoginField;



    @FindBy(id="passwordLogIn")
    private WebElement passwordLogInField;

    @FindBy(id="login")
    private WebElement logInButton;




    public void setUserid(String userid){
        useridLoginField.clear();
        useridLoginField.sendKeys(userid);
    }


    public void setPassword(String password) {
        passwordLogInField.clear();
        passwordLogInField.sendKeys(password);
    }


    public boolean loginButtonIsPresent(){

        try {
            WebElement webElement = driver.findElement(By.id("login"));
            return true;
        }catch (NoSuchElementException e) {
            return false;
        }
    }

    public void submitLoginButton() {
        WebElement buttonLogin = driver.findElement(By.id("login"));
        buttonLogin.submit();

    }
    public boolean logOutButtonIsPresent(){

        try {
            WebElement webElement = driver.findElement(By.id("logout"));
            return true;
        }catch (NoSuchElementException e) {
            return false;
        }
    }

    public void submitLogOutButton() {
        WebElement buttonLogin = driver.findElement(By.id("logout"));
        buttonLogin.submit();

    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("#errorMsgLogin"));
        return errorMsg.getText().equals(message);

    }

    public boolean hasWelcomeMessage(String message) {
        WebElement welcomeMsg = driver.findElement(By.cssSelector("#welcomeMsg"));
        return welcomeMsg.getText().equals(message);
    }
    public boolean hasNavToTestPage() {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("nav ul li a"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains("Add Test")) {
                found=true;
            }
        }
        return found;
    }

}
