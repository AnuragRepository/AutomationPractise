package Basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class WindowPrac {

    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        WebElement link = driver.findElement(By.xpath("//div[@id='login']/preceding-sibling::a"));
        link.click();
        Set<String> windows =  driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parentWindowID = it.next();
        String childWindowID = it.next();
        driver.switchTo().window(childWindowID);
        WebElement lineChildWindow = driver.findElement(By.xpath("//div[@class='col-md-8']/p[2]"));
        String textChildWindow = lineChildWindow.getText().split(" with")[0].split("at ")[1];
        System.out.println("textChildWindow = "+textChildWindow);
        Assert.assertEquals(textChildWindow,"mentor@rahulshettyacademy.com");
        driver.switchTo().window(parentWindowID);
        WebElement username= driver.findElement(By.xpath("//*[@id='username']"));
        username.sendKeys(textChildWindow);
        /*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']")));
        System.out.println("username = "+username.getText());
        Assert.assertEquals(username.getText(),"mentor@rahulshettyacademy.com");
*/
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }
}
