package pages;
import org.openqa.selenium.*;
public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver) { this.driver = driver; }
    public void login(String u, String p) {
        driver.findElement(By.id("user-name")).sendKeys(u);
        driver.findElement(By.id("password")).sendKeys(p);
        driver.findElement(By.id("login-button")).click();
    }
}
