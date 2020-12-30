import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AddContactTest {


    private WebDriver driver;

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenne\\2TI\\Web3\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.managed_default_content_settings.javascript", 2);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid("admin");
        homePage.setPassword("Webontwikkeling3");
        homePage.submitLoginButton();


    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_AddContact_AllFieldsFilledInCorrectly_ContactIsAdded() {


        //Create a contact
        int randomId = (int) (Math.random()*100);
        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.setPhoneNumber("0412345678");
        addContactPage.pressButton();

        ContactsPage overview = PageFactory.initElements(driver, ContactsPage.class);
        assertTrue(overview.containsUserWithFirstName("Jan"));
        assertTrue(overview.containsUserWithLastName("Janssens"));
    }

    @Test
    public void test_AddContact_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept(){

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("");
        addContactPage.setLastName("Janssens");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.setPhoneNumber("0468235671");
        addContactPage.pressButton();

        assertEquals("Add Visitor", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("No firstname given"));
        assertTrue(addContactPage.hasEmptyFirstName());
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        assertTrue(addContactPage.hasStickyPhoneNumber("0468235671"));
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.setPhoneNumber("0468235671");
        addContactPage.pressButton();

        assertEquals("Add Visitor", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("No lastname given"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasEmptyLastName());
        assertTrue(addContactPage.hasStickyPhoneNumber("0468235671"));
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_GSMNotFilledIn_ErrorMessageGivenForGSMAndOtherFieldsValueKept(){

        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setPhoneNumber("");
        addContactPage.setEmail("jan.janssens@hotmail.com");
        addContactPage.pressButton();

        assertEquals("Add Visitor", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("No phonenumber given"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        assertTrue(addContactPage.hasEmptyPhoneNumber());
        assertTrue(addContactPage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_AddContact_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        AddContactPage addContactPage = PageFactory.initElements(driver, AddContactPage.class);
        addContactPage.setFirstName("Jan");
        addContactPage.setLastName("Janssens");
        addContactPage.setPhoneNumber("0468235671");
        addContactPage.setEmail("");
        addContactPage.pressButton();

        assertEquals("Add Visitor", addContactPage.getTitle());
        assertTrue(addContactPage.hasErrorMessage("No email given"));
        assertTrue(addContactPage.hasStickyFirstName("Jan"));
        assertTrue(addContactPage.hasStickyLastName("Janssens"));
        assertTrue(addContactPage.hasStickyPhoneNumber("0468235671"));
        assertTrue(addContactPage.hasEmptyEmail());
    }
}
