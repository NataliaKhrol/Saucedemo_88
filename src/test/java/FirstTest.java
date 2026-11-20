import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

public class FirstTest extends BaseTest {
    @Test
    public void zipCode4Digits() throws InterruptedException {
        driver.findElement(By.cssSelector("#user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.name("login-button")).click();
        Thread.sleep(2000);

        boolean errorIsVisible = driver.findElement(By.cssSelector("[data-test='error-button']")).isDisplayed();
        String errorText = driver.findElement(By.tagName("h3")).getText();

        assertTrue(errorIsVisible);
        assertEquals(errorText, "Epic sadface: Sorry, this user has been locked ouhhht.");
    }

    @Test
    public void correctLogin() throws InterruptedException {
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.name("login-button")).click();
        Thread.sleep(2000);

        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
        assertTrue(currentUrl.contains("inventory.html"));

        boolean pageTitleIsVisible = driver.findElement(By.cssSelector("[data-test='title']")).isDisplayed();
        String errorText = driver.findElement(By.cssSelector("[data-test='title']")).getText();

        assertTrue(pageTitleIsVisible);
        assertEquals(errorText, "Products");
    }
}
