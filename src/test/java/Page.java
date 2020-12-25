import org.openqa.selenium.WebDriver;



public class Page {
    WebDriver driver;
    String path = "http://localhost:8080/opdracht_web3_war_exploded/Controller";

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }




    public String getPath() {
        return path;
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
