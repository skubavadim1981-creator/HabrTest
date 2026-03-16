package ru.course.at;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

public class HabrTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com/");
    }

    @AfterEach
    public void tearDown(){driver.quit();}

    @Test
    public void emailFieldTest(){

        WebElement logButton = driver.findElement(By.cssSelector(".tm-header-user-menu__login"));
        logButton.click();

        WebElement passLink = driver.findElement(By.cssSelector(".form__remind-password-link"));
        passLink.click();

        assertTrue(driver.findElement(By.cssSelector("input[name='email']")).isDisplayed());
    }

    @Test
    public void backToLogFormTest(){

        WebElement logButton = driver.findElement(By.cssSelector(".tm-header-user-menu__login"));
        logButton.click();

        WebElement passLink = driver.findElement(By.cssSelector(".form__remind-password-link"));
        passLink.click();

        WebElement returnButton = driver.findElement(By.xpath("//a[text()='Вернуться к форме входа']"));
        returnButton.click();

        assertTrue(driver.findElement(By.cssSelector(".shadow-box__title")).isDisplayed());
    }
}
