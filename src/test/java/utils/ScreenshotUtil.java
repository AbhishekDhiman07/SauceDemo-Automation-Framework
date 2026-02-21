package utils;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import java.io.File;
public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String name) {
        String path = System.getProperty("user.dir") + "/screenshots/" + name + ".png";
        try { FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File(path)); }
        catch (Exception e) { e.printStackTrace(); }
        return path;
    }
}
