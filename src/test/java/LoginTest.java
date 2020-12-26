import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private String path = "http://localhost:8080/opdracht_web3_war_exploded/Controller";

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenne\\2TI\\Web3\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path);




    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_login_works(){
        System.out.println("oke");
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        //Login_LogoutPage login_logoutPage = PageFactory.initElements(driver, Login_LogoutPage.class);
        System.out.println("okeeeeeeeeeeeeee");
        // eerst inloggen
        homePage.setUserid("admin");
        homePage.setPassword("t");
        homePage.submitLoginButton();

        assertTrue(homePage.hasWelcomeMessage("Welcome Ad, you are now logged in, you can log out again or you can change your password."));

        // kijk of je de logout button kan zien
        assertTrue(homePage.logOutButtonIsPresent());

    }

    @Test
    public void test_can_not_login_when_already_logged_in() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        boolean gelukt = false;

        // eerst inloggen
        homePage.setUserid("admin");
        homePage.setPassword("t");
        homePage.submitLoginButton();
        // kijken of dat login button zichtbaar is
        assertFalse(homePage.loginButtonIsPresent());
        // kijken of dat logout button niet zichtbaar is
        assertTrue(homePage.logOutButtonIsPresent());

        try {
            homePage.submitLoginButton();
            gelukt = false;
        }
        catch (NoSuchElementException e) {
            gelukt = true;
        }
        assertTrue(gelukt);
    }

    @Test
    public void test_gifs_error_message_when_fault_password() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        // eerst inloggen
        homePage.setUserid("admin");
        // fout password meegevene
        homePage.setPassword("a");
        homePage.submitLoginButton();
        // kijken of dat login button zichtbaar is
        assertTrue(homePage.loginButtonIsPresent());
        // kijken of dat logout button niet zichtbaar is
        assertFalse(homePage.logOutButtonIsPresent());
        assertTrue(homePage.hasErrorMessage("No matching user Id and password"));

    }

}



