import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.*;

public class ContactsOverviewTest {
    private WebDriver driver;
//    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenne\\2TI\\Web3\\chromedriver.exe");
        driver = new ChromeDriver();
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid("admin");
        homePage.setPassword("t");
        homePage.submitLoginButton();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Contact_Joske() {
        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        assertTrue(contactsPage.containsUserWithFirstName("Joske"));
        assertTrue(contactsPage.containsUserWithLastName("Vermeulen"));
        assertTrue(contactsPage.containsUserWithDateTime("21/11/2020 15:10"));

    }
}
