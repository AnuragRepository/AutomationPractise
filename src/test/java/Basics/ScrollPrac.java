package Basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ScrollPrac {

    @Test
    public void test() {

        //scroll entire webpage
        //scroll inside specific element like in table
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        JavascriptExecutor js = (JavascriptExecutor)driver;

        //scroll entire webpage
        js.executeScript("window.scrollBy(0,500)");

            //scroll inside specific element like in table
        int sumAmount =0;
            js.executeScript("document.querySelector('.tableFixHead').scrollBy(0,5000)");
       List<WebElement> listAmount = driver.findElements(By.xpath("//*[@id='product']/tbody/tr/td[4]"));
       for(int i=0;i<listAmount.size();i++)
       {
           sumAmount= sumAmount + Integer.parseInt(listAmount.get(i).getText());
       }
        System.out.println("sumAmount = "+sumAmount);
        int expectedAmountDisplayFromScreen=  Integer.parseInt(driver.findElement(By.xpath("//*[@class='totalAmount']")).getText().split(": ")[1]);
        Assert.assertEquals(sumAmount,expectedAmountDisplayFromScreen);

        int sumAmount2=0;
       List<WebElement> listAmount2 = driver.findElements(By.xpath("//*[@name='courses']/tbody/tr/td[3]"));
       for(int i =0;i<listAmount2.size();i++)
       {
         sumAmount2 = sumAmount2 + Integer.parseInt(listAmount2.get(i).getText());
       }
        System.out.println("sumAmount2 = "+sumAmount2);
       Assert.assertEquals(sumAmount2,235);
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }


}
