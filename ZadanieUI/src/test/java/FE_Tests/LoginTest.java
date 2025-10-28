package FE_Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/*
Tento test pokriva pozitivne a negativne  prihlasovanie do eshopu, jedna sa o kriticky bod z hladiska funkcnosti
a bezpecnosti
*/
@Tag(("UITest"))
public class LoginTest {
    private String BASE_URL =("https://www.saucedemo.com/");
    WebDriver driver;

    @BeforeEach
    void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        // Overenie ze som na spravnej stranke
        assertEquals(BASE_URL, driver.getCurrentUrl(),"Nie si na spravnej URL");
        assertEquals("Swag Labs",driver.getTitle(),"Nesedi titulok stranky");
    }
    //Pozitivny test prihlasenia sa do systemu
    @Test
    public void login(){
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        WebElement header = driver.findElement(By.xpath("//span[@class='title']"));
        String actualText = header.getText();
        assertEquals("Products", actualText, "Nie si na stranke produktov, nebol si prihlaseny");

    }
    //Negativny test prihlasenia
    @Test
    public void negativeLogin(){
        driver.findElement(By.id("login-button")).click();
        String errorText = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        assertEquals("Epic sadface: Username is required", errorText, "Chybova hlaska nesedí");
    }
}
