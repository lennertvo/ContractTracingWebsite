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
        Login_LogoutPage login_logoutPage = PageFactory.initElements(driver, Login_LogoutPage.class);
        //Login_LogoutPage login_logoutPage = PageFactory.initElements(driver, Login_LogoutPage.class);
        System.out.println("okeeeeeeeeeeeeee");
        // eerst inloggen
        login_logoutPage.setUserid("admin");
        login_logoutPage.setPassword("t");
        login_logoutPage.submitLoginButton();

        assertTrue(login_logoutPage.hasWelcomeMessage("Welcome Ad, you are now logged in, you can log out again or you can change your password."));

        // kijk of je de logout button kan zien
        assertTrue(login_logoutPage.logOutButtonIsPresent());

    }

    @Test
    public void test_can_not_login_when_already_logged_in() {
        Login_LogoutPage login_logoutPage = PageFactory.initElements(driver, Login_LogoutPage.class);
        boolean gelukt = false;

        // eerst inloggen
        login_logoutPage.setUserid("admin");
        login_logoutPage.setPassword("t");
        login_logoutPage.submitLoginButton();
        // kijken of dat login button zichtbaar is
        assertFalse(login_logoutPage.loginButtonIsPresent());
        // kijken of dat logout button niet zichtbaar is
        assertTrue(login_logoutPage.logOutButtonIsPresent());

        try {
            login_logoutPage.submitLoginButton();
            gelukt = false;
        }
        catch (NoSuchElementException e) {
            gelukt = true;
        }
        assertTrue(gelukt);
    }

    @Test
    public void test_gifs_error_message_when_fault_password() {
        Login_LogoutPage login_logoutPage = PageFactory.initElements(driver, Login_LogoutPage.class);

        // eerst inloggen
        login_logoutPage.setUserid("admin");
        // fout password meegevene
        login_logoutPage.setPassword("a");
        login_logoutPage.submitLoginButton();
        // kijken of dat login button zichtbaar is
        assertTrue(login_logoutPage.loginButtonIsPresent());
        // kijken of dat logout button niet zichtbaar is
        assertFalse(login_logoutPage.logOutButtonIsPresent());
        assertTrue(login_logoutPage.hasErrorMessage("No matching user Id and password"));

    }

}



