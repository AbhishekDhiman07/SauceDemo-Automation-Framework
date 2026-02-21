package testcases;

import base.BaseTest;
import pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class AddToCartTest extends BaseTest {
    @Test
    public void validateCart() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1. Login
        login.login("standard_user", "secret_sauce");
        Thread.sleep(5000); 

        // 2. Click Add to Cart
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();
        
        // 3. Wait for the badge to update to '1'
        // This ensures the page state is stable before we move on
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("shopping_cart_badge"), "1"));
        Thread.sleep(5000);

        // 4. Click Cart Icon (Re-finding it to avoid StaleElementReferenceException)
        WebElement cartLink = wait.until(ExpectedConditions.refreshed(
            ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link"))
        ));
        cartLink.click();

        // 5. Verify Item in Cart
        wait.until(ExpectedConditions.urlContains("cart.html"));
        Thread.sleep(5000);
        
        String itemName = driver.findElement(By.className("inventory_item_name")).getText();
        Assert.assertEquals(itemName, "Sauce Labs Backpack", "Item not found in cart!");
    }
}