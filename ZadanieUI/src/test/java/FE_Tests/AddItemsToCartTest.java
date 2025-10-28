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

//Tento test pokryva vkladanie jednotlivych polozek do kosika a validuje pocet objednavok v kosiku.

@Tag(("UITest"))
public class AddItemsToCartTest {
    private final String BASE_URL = "https://www.saucedemo.com/";
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
    void AddItemsToCart() {
        // Vsetky button pre pridane produktu do kosika
        String[] productButtons = {
                "add-to-cart-sauce-labs-backpack",
                "add-to-cart-sauce-labs-bike-light",
                "add-to-cart-sauce-labs-bolt-t-shirt",
                "add-to-cart-sauce-labs-fleece-jacket",
                "add-to-cart-sauce-labs-onesie",
                "add-to-cart-test.allthethings()-t-shirt-(red)"
        };

        int expectedCount = 0;

        for (String buttonId : productButtons) {
            driver.findElement(By.id(buttonId)).click();
            expectedCount++;

            // počká, kým sa badge zobrazí alebo zmení
            WebElement cartBadge = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".shopping_cart_badge"))
            );

            int actualCount = Integer.parseInt(cartBadge.getText());
            assertEquals(expectedCount, actualCount, "Pocet poloziek v kosiku nesedi!");
        }
    }
}
