package Basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalendarPracTest {

    @Test
    public void test() {

        //WAP to select a given date and verify if it's selected
        String Date = "18";
        String Month = "10";
        String Year = "2025";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.findElement(By.xpath("//div[contains(@class,'react-date-picker__inputGroup')]")).click();
        driver.findElement(By.xpath("//button[@class='react-calendar__navigation__label']")).click();
        driver.findElement(By.xpath("//button[@class='react-calendar__navigation__label']")).click();
        driver.findElement(By.xpath("//button[text()='"+Year+"']")).click();
        driver.findElement(By.xpath("//*[@class='react-calendar__year-view__months']/button["+Month+"]")).click();
        driver.findElement(By.xpath("//abbr[text()='"+Date+"']")).click();
        String selectedDate = driver.findElement(By.xpath("//*[@class='react-date-picker__inputGroup']/input[1]")).getDomAttribute("value");
        System.out.println("Selected Date = "+selectedDate);
        Assert.assertEquals(selectedDate.split("-")[0],Year);
        Assert.assertEquals(selectedDate.split("-")[1],Month);
        Assert.assertEquals(selectedDate.split("-")[2],Date);
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }
}
