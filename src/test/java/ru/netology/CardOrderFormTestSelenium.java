package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardOrderFormTestSelenium {

    private WebDriver driver;


    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "driver/mac/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("driver/mac/chromedriver");
        options.addArguments("--headless");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldSubmitRequestIfFieldsAreFilledCorrect() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("Татьяна");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79990000000");
        driver.findElement(By.cssSelector("[class='checkbox__text']")).click();
        driver.findElement(By.cssSelector("[type='button']")).click();

        String actualMassage = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualMassage.trim());
    }

    @Test
    void shouldNotSubmitRequestIfTextFieldIsEmpty() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79990000000");
        driver.findElement(By.cssSelector("[class='checkbox__text']")).click();
        driver.findElement(By.cssSelector("[type='button']")).click();

        String actualMassage = driver.findElement(By.cssSelector("[class='input__sub']")).getText();
        assertEquals("Поле обязательно для заполнения", actualMassage.trim());
    }

    @Test
    void shouldNotSubmitRequestIfTextFieldIsInvalid() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("jdhfh");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79990000000");
        driver.findElement(By.cssSelector("[class='checkbox__text']")).click();
        driver.findElement(By.cssSelector("[type='button']")).click();

        String actualMassage = driver.findElement(By.cssSelector("[class='input__sub']")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualMassage.trim());
    }

//    @Test
//    void shouldNotSubmitRequestIfTextFieldIsInvalid2() {
//        driver.get("http://localhost:9999");
//        driver.findElement(By.cssSelector("[type='text']")).sendKeys("ывалоыр");
//        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79990000000");
//        driver.findElement(By.cssSelector("[class='checkbox__text']")).click();
//        driver.findElement(By.cssSelector("[type='button']")).click();
//
//        String actualMassage = driver.findElement(By.cssSelector("span[class='input__sub']")).getText();
//        assertEquals("Укажите точно как в паспорте", actualMassage.trim());
//    }
//
//    @Test
//    void shouldNotSubmitRequestIfTelFieldIsEmpty() {
//        driver.get("http://localhost:9999");
//        driver.findElement(By.cssSelector("[type='text']")).sendKeys("Татьяна");
//        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("");
//        driver.findElement(By.cssSelector("[class='checkbox__text']")).click();
//        driver.findElement(By.cssSelector("[type='button']")).click();
//
//        String actualMassage = driver.findElement(By.cssSelector("[class='input__sub']")).getText();
//        assertEquals("Поле обязательно для заполнения", actualMassage.trim());
//    }
//
//    @Test
//    void shouldNotSubmitRequestIfiTelFieldIsInvalid() {
//        driver.get("http://localhost:9999");
//        driver.findElement(By.cssSelector("[type='text']")).sendKeys("Татьяна");
//        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("764758");
//        driver.findElement(By.cssSelector("[class='checkbox__text']")).click();
//        driver.findElement(By.cssSelector("[type='button']")).click();
//
//        String actualMassage = driver.findElement(By.cssSelector("[class='input__sub']")).getText();
//        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualMassage.trim());
//    }

}
