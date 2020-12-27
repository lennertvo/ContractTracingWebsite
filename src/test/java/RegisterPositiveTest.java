import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterPositiveTest {

    private WebDriver driver;
    //private String path = "http://localhost:8080/opdracht_web3_war_exploded/Controller";



    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenne\\2TI\\Web3\\chromedriver.exe");

        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        //System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        driver = new ChromeDriver();
        //driver.get(path);
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
    public void succesfull_test_add_brings_you_to_contact_page() {
        TestRegisterPage testRegisterPage = PageFactory.initElements(driver, TestRegisterPage.class);
        testRegisterPage.setDateField(LocalDate.now());
        testRegisterPage.submitSuccesful();
        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        assertEquals("Add Visitor", contactsPage.getTitle());

    }

    @Test
    public void unsuccesfull_test_add_gives_an_error_message() {
        TestRegisterPage testRegisterPage = PageFactory.initElements(driver, TestRegisterPage.class);
        testRegisterPage.submitUnsuccesful();
        assertEquals("Add Positive Test", testRegisterPage.getTitle());
        assertTrue(testRegisterPage.hasErrorMessage("Please fill a date in"));
    }

    @Test
    public void not_logged_in_user_cant_see_Add_Test_in_navigation() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.submitLogOutButton();
        boolean b = homePage.hasNavToTestPage();
        assertFalse(b);
    }

    @Test
    public void logged_in_user_can_see_Add_Test_in_navigation() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        boolean b = homePage.hasNavToTestPage();
        assertTrue(b);
    }
}


