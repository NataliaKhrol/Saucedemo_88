package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {
    @Test
    public void loginUserLogin() throws InterruptedException {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void invalidUserLogin() throws InterruptedException {
        driver.findElement(By.cssSelector("#user-name")).sendKeys("Standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.name("login-button")).click();
        Thread.sleep(2000);

        boolean errorIsVisible = driver.findElement(By.cssSelector("[data-test='error-button']")).isDisplayed();
        String errorText = driver.findElement(By.tagName("h3")).getText();

        assertTrue(errorIsVisible);
        assertEquals(errorText, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void noPasswordLogin() throws InterruptedException {
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("");
        Thread.sleep(2000);
        driver.findElement(By.name("login-button")).click();
        Thread.sleep(2000);

        boolean errorIsVisible = driver.findElement(By.cssSelector("[data-test='error-button']")).isDisplayed();
        String errorText = driver.findElement(By.tagName("h3")).getText();

        assertTrue(errorIsVisible);
        assertEquals(errorText, "Epic sadface: Password is required");
    }

    @Test
    public void noLogin() throws InterruptedException {
        driver.findElement(By.cssSelector("#user-name")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.name("login-button")).click();
        Thread.sleep(2000);

        boolean errorIsVisible = driver.findElement(By.cssSelector("[data-test='error-button']")).isDisplayed();
        String errorText = driver.findElement(By.tagName("h3")).getText();

        assertTrue(errorIsVisible);
        assertEquals(errorText, "Epic sadface: Username is required");
    }

    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(productsPage.pageTitleVisible());
        assertEquals(productsPage.getTitleText(), "Products");
    }
}
