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
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        //Login_LogoutPage login_logoutPage = PageFactory.initElements(driver, Login_LogoutPage.class);
        System.out.println("okeeeeeeeeeeeeee");
        // eerst inloggen
        homePage.setUserid("admin");
        homePage.setPassword("t");
        homePage.submitLoginButton();

        // kijk of je de logout button kan zien
        assertTrue(homePage.logOutButtonIsPresent());
        // uitloggen
        homePage.submitLogOutButton();
        // kijk of je bent uitgelogd en ook dus terug kan inloggen
        assertTrue(homePage.loginButtonIsPresent());

    }

    @Test
    public void test_can_not_logout_when_not_logged_in() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
       boolean gelukt = false;
        // kijken of dat login button zichtbaar is
        assertTrue(homePage.loginButtonIsPresent());
        // kijken of dat logout button niet zichtbaar is
        assertFalse(homePage.logOutButtonIsPresent());

      try {
            homePage.submitLogOutButton();
            gelukt = false;
        }
        catch (NoSuchElementException e) {
            gelukt = true;
        }
       assertTrue(gelukt);
    }

}
