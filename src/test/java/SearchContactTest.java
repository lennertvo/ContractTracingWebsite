import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchContactTest {

    private WebDriver driver;
    private String path = "http://localhost:8080/opdracht_web3_war_exploded/Controller";



    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenne\\2TI\\Web3\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?command=AddVisitorForm");
    }


    @After
    public void clean() {
        driver.quit();
    }


    @Test
    public void test_SearchContact_showsOlyContactsWithThisFirstnanme() {

        driver.get("http://localhost:8080/opdracht_web3_war_exploded/index.jsp");
        fillOutField("useridLogIn", "admin");
        fillOutField("passwordLogIn", "t");
        WebElement button=driver.findElement(By.id("login"));
        button.click();

        driver.get(path +"?command=VisitorOverview");


        fillOutField("search", "Jan");
        String title = driver.getTitle();
        assertEquals("Add Visitor",title);


        ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (!listItem.getText().contains("Jan")) {
                found=true;
            }
        }
        assertTrue(found);
    }





    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }









}
