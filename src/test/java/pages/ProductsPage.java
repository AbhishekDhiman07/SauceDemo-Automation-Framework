package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    WebDriver driver;
    public ProductsPage(WebDriver driver) { this.driver = driver; }

    By backpackBtn = By.id("add-to-cart-sauce-labs-backpack");
    By cartLink = By.className("shopping_cart_link");

    public void addBackpack() {
        driver.findElement(backpackBtn).click();
    }

    public void clickCartIcon() {
        driver.findElement(cartLink).click();
    }
}