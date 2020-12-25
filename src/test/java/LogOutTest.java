import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;


import static org.junit.Assert.*;

public class LogOutTest {

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


    // kan enkel werken als je een gebruiker hebt in dit geval de ADMIN met als userid= 'admin' en als password = 't'
    @Test
    public void test_logout_works_when_you_are_logged_in(){
        System.out.println("oke");
        Login_LogoutPage login_logoutPage = PageFactory.initElements(driver, Login_LogoutPage.class);
        //Login_LogoutPage login_logoutPage = PageFactory.initElements(driver, Login_LogoutPage.class);
        System.out.println("okeeeeeeeeeeeeee");
        // eerst inloggen
        login_logoutPage.setUserid("admin");
        login_logoutPage.setPassword("t");
        login_logoutPage.submitLoginButton();

        // kijk of je de logout button kan zien
        assertTrue(login_logoutPage.logOutButtonIsPresent());
        // uitloggen
        login_logoutPage.submitLogOutButton();
        // kijk of je bent uitgelogd en ook dus terug kan inloggen
        assertTrue(login_logoutPage.loginButtonIsPresent());

    }

    @Test
    public void test_can_not_logout_when_not_logged_in() {
        Login_LogoutPage login_logoutPage = PageFactory.initElements(driver, Login_LogoutPage.class);
       boolean gelukt = false;
        // kijken of dat login button zichtbaar is
        assertTrue(login_logoutPage.loginButtonIsPresent());
        // kijken of dat logout button niet zichtbaar is
        assertFalse(login_logoutPage.logOutButtonIsPresent());

      try {
            login_logoutPage.submitLogOutButton();
            gelukt = false;
        }
        catch (NoSuchElementException e) {
            gelukt = true;
        }
       assertTrue(gelukt);
    }

}
