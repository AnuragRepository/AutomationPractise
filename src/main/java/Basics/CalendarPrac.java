package Basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class CalendarPrac {

    public static void main(String[] args)
    {
        //WAP to select a given date and verify if it's selected
        String Date = "18";
        String Month = "10";
        String Year = "2025";
        System.setProperty("webdriver.chrome.driver","C://Driver//chromedriver-win64//chromedriver.exe");
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
    }
}
