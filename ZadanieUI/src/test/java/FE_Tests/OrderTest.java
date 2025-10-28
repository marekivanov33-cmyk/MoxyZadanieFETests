package FE_Tests;

import helpers.CommonSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

//E2E test celého procesu objednavky až po zaplatenie.

@Tag(("UITest"))
public class OrderTest {
    private  final String BASE_URL = "https://www.saucedemo.com/";
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = CommonSetup.setUp(BASE_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        CommonSetup.login(driver);

        WebElement productsTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='title']"))
        );

        // Overenie ze som na spravnej stranke
        assertTrue(productsTitle.isDisplayed(), "'Products' title by mal byť viditeľný");
        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl(), "Nie si na stránke produktov");

}
    @Test
    void OrderAndPay(){
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.cssSelector("#checkout")).click();
        driver.findElement(By.cssSelector("#first-name")).sendKeys("Marek");
        driver.findElement(By.cssSelector("#last-name")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("03401");
        driver.findElement(By.cssSelector("#continue")).click();
        driver.findElement(By.xpath("//button[@id='finish']")).click();

        WebElement OrderConfirmed = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space()='Thank you for your order!']")));
                assertTrue(OrderConfirmed.isDisplayed(),"Objednavka nebola uspesna");

    }

}
