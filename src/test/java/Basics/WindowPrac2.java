package Basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class WindowPrac2 {

    @Test
    public void test() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        WebElement link= driver.findElement(By.xpath("//*[@class='blinkingText']"));
        link.click();
        Set<String> windowHandles= driver.getWindowHandles();
        Iterator<String> it =  windowHandles.iterator();
        String parentWindowID= it.next();
        String childWindowID= it.next();
        driver.switchTo().window(childWindowID);
        String emailID = driver.findElement(By.xpath("//strong[a]")).getText();
        System.out.println(emailID);
        String emailIDText = driver.findElement(By.xpath("//div[@class='col-md-8']/p[2]")).getText();
        System.out.println(emailIDText);
        String email= emailIDText.split("at ")[1].split(" with")[0];
        System.out.print(email);
        driver.switchTo().window(parentWindowID);
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys(email);
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

}
