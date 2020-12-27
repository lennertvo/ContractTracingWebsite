import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;

public class TestRegisterPage extends Page{

    @FindBy(id="date")
    private WebElement dateField;

    @FindBy(id = "addPositiveTest")
    private WebElement submit;

    public TestRegisterPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=ShowAddTest");
    }

    public void setDateField(LocalDate date) {
        dateField.clear();
        dateField.sendKeys(date.toString());
    }

    public ContactsPage submitSuccesful() {
        submit.click();
        return PageFactory.initElements(driver, ContactsPage.class);
    }

    public void submitUnsuccesful() {
        submit.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }
}
