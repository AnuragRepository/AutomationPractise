package Selenium4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class window0rTabMultiple {
    public static void main(String[] args)
    {
        //WAP to enter text in Name label of 1st URL from grabbed 1st course from another 2nd  URL- VVI
        System.setProperty("wedriver.chrome.drver","");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.switchTo().newWindow(WindowType.TAB);
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> it = windowHandles.iterator();
        String parentWindowID = it.next();
        String childWindowID = it.next();
        driver.switchTo().window(childWindowID);
        driver.get("https://rahulshettyacademy.com/");
        String firstCourse = driver.findElement(By.xpath("//*[@id='courses-block']/div[1]/div/div[2]/div[1]/h2/a")).getText();
        System.out.println("firstCourse ="+firstCourse);
        driver.switchTo().window(parentWindowID);
        //selenium 4 locator not working may be due to flex elements
        //WebElement nameLabel = driver.findElement(By.xpath("//*[text()='Name']"));
        //WebElement nameTextBox = driver.findElement(with(By.tagName("input")).below(By.xpath("//*[text()='Name']")));
        //WebElement nameTextBox = driver.findElement(with(By.tagName("input")).below(nameLabel));
        WebElement nameTextBox = driver.findElement(By.xpath("//*[@name='name']"));
        nameTextBox.sendKeys(firstCourse);











    }

}
