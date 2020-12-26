import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddContactPage extends Page {

    @FindBy(id="firstName")
    private WebElement firstNameField;

    @FindBy(id="lastName")
    private WebElement lastNameField;

    @FindBy(id="email")
    private WebElement emailField;

    @FindBy(id="phoneNumber")
    private WebElement gsmField;

    @FindBy(id="addVisitor")
    private WebElement signUpButton;



    public AddContactPage (WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=AddVisitorForm");
    }

    public void setFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }



    public void setPhoneNumber(String phoneNumber) {
        gsmField.clear();
        gsmField.sendKeys(phoneNumber);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void pressButton() {
        signUpButton.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyFirstName (String firstname) {
        return firstname.equals(firstNameField.getAttribute("value"));
    }

    public boolean hasStickyLastName (String lastname) {
        return lastname.equals(lastNameField.getAttribute("value"));
    }

    public boolean hasStickyPhoneNumber(String phoneNumber) {
        return phoneNumber.equals(gsmField.getAttribute("value"));
    }



    public boolean hasStickyEmail (String email) {
        return email.equals(emailField.getAttribute("value"));
    }

    public boolean hasEmptyFirstName () {
        return firstNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyLastName () {
        return lastNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyPhoneNumber () {
        return gsmField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyEmail () {
        return emailField.getAttribute("value").isEmpty();
    }


}
