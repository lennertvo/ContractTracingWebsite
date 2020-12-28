import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import reactor.core.publisher.Flux;

import javax.swing.text.Element;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SearchPositiveUsersOnSpecificDateTest {

    private WebDriver driver;
    private String path = "http://localhost:8080/opdracht_web3_war_exploded/Controller";

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
        driver.get(path);
        fillOutField("useridLogIn", "admin");
        fillOutField("passwordLogIn", "t");
        WebElement button = driver.findElement(By.id("login"));
        button.click();
    }


    @After
    public void clean() {
        driver.quit();
    }


    // er zijn 3 users met een positieve test: Admin op 24/11/2020, Lennert op 27/11/2020 en Jan op 28/11/2020
    @Test
    public void test_date_is_filled_in_and_gives_all_positive_users_on_given_date() {
        //eerst inloggen als admin, userid = admin, password = t

        driver.get(path+"?command=AllPositiveUsers");

        // gaan testen op datum 28/11/2020 dus alleen Jan mag nog weergegeven worden

        WebElement date = driver.findElement(By.id("date"));
        date.sendKeys("28-11-2020");

        WebElement search = driver.findElement(By.id("searchPositiveUsersOnDate"));
        search.click();

        ArrayList<WebElement> listItems = (ArrayList<WebElement>) driver.findElements(By.cssSelector("#my4Tr"));
        boolean onlyJan = false;
        for(WebElement listItem: listItems) {
            if(listItem.getText().contains("Ad") && listItem.getText().contains("Ministrator") || listItem.getText().contains("Lennnert") &&
                    listItem.getText().contains("Van Oosterwyck")){
                onlyJan = false;
            }else if(listItem.getText().contains("Jan") && listItem.getText().contains("Janssens"))
            {
                onlyJan = true;
            }

        }
        assertTrue(onlyJan);

    }

    @Test
    public void test_date_is_not_filled_in_gives_error_message(){
        //eerst inloggen als admin, userid = admin, password = t

        driver.get(path+"?command=AllPositiveUsers");

        WebElement search = driver.findElement(By.id("searchPositiveUsersOnDate"));
        search.click();
        WebElement error = driver.findElement(By.className("alert-danger"));
        String errorMessage = error.getText();
        assertEquals("Date value is empty", errorMessage);
    }

    @Test
    public void test_date_is_filled_in_with_date_when_nobody_is_tested_positive(){
        //eerst inloggen als admin, userid = admin, password = t


        driver.get(path+"?command=AllPositiveUsers");

        WebElement date = driver.findElement(By.id("date"));
        date.sendKeys("25-11-2020");

        WebElement search = driver.findElement(By.id("searchPositiveUsersOnDate"));
        search.click();
        WebElement error = driver.findElement(By.className("alert-danger"));
        String errorMessage = error.getText();
        assertEquals("No users tested positive on this date", errorMessage);
    }






    private void fillOutField (String name, String value){
        WebElement field = driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }






}

