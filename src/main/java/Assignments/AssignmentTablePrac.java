package Assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class AssignmentTablePrac {

    public static void main(String[] args)
    {
     /*   1. Number of rows in table 1
        2. Number of column in table 1
        3. Print 2nd row*/
        System.setProperty("webdriver.chrome.driver","C://Driver//chromedriver-win64//chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)");
        List<WebElement> row = driver.findElements(By.xpath("//*[@name='courses']/tbody/tr"));
        List<WebElement> column = driver.findElements(By.xpath("//*[@name='courses']/tbody/tr/th"));
        System.out.println("Number of rows in table = "+row.size());
        System.out.println("Number of column in table = "+column.size());
        for(int i=2;i<row.size();i++)
        {
           System.out.println("2nd row in table = "+row.get(i).getText());
                    if(i==2)
                    {
                        break;
                    }
        }
        Assert.assertEquals(row.size(),11);
        Assert.assertEquals(column.size(),3);
        //or directly print without for loop
        Assert.assertEquals(row.get(2).getText(),"Rahul Shetty Learn SQL in Practical + Database Testing from Scratch 25");
    }



}
