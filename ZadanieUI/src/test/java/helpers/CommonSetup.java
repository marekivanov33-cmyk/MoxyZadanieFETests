package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonSetup {

    //Nastavenie browsera a otvorenie stranky
    public static WebDriver setUp(String baseUrl) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("credentials_enable_service", false);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(baseUrl);

        return driver;
    }

    // Overenie ze som na spravnej stranky
    public static void OverenieStranky(WebDriver driver) {
        String BASE_URL = "https://www.saucedemo.com/";
        assertEquals(BASE_URL, driver.getCurrentUrl(), "Nie si na spravnej URL");
        assertEquals("Swag Labs", driver.getTitle(), "Nesedi titulok stranky");
    }

    //  Univerzalny login
    public static void login(WebDriver driver) {
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }
}

