package Assignments;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class Assignment3Test {

    @Test public void test() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/");
        WebElement multipleWindowLink = driver.findElement(By.linkText("Multiple Windows"));
        multipleWindowLink.click();
        WebElement clickHereLink = driver.findElement(By.linkText("Click Here"));
        clickHereLink.click();
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parentWindowID = it.next();
        String childWindowID = it.next();
        driver.switchTo().window(childWindowID);
        WebElement childText = driver.findElement(By.xpath("//h3[text()='New Window']"));
        System.out.println("childText = "+childText.getText());
        Assert.assertEquals(childText.getText(),"New Window");
        driver.switchTo().window(parentWindowID);
        WebElement parentText= driver.findElement(By.xpath("//h3[text()='Opening a new window']"));
        System.out.println("parentText = "+parentText.getText());
        Assert.assertEquals(parentText.getText(),"Opening a new window");
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }


}
