package Basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class FluentWaitPrac1 {

    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/");
        WebElement dynamicLoadingLink = driver.findElement(By.linkText("Dynamic Loading"));
        dynamicLoadingLink.click();
        WebElement example1Link = driver.findElement(By.linkText("Example 1: Element on page that is hidden"));
        example1Link.click();
        WebElement button = driver.findElement(By.xpath("//button[text()='Start']"));
        button.click();

        Wait<WebDriver> waitobj = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
        WebElement element= waitobj.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {

                if(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed())
                {
                    return driver.findElement(By.xpath("//h4[text()='Hello World!']"));
                }
                else {
                    return null;
                }
            }
        });
        System.out.println(element.getText());
        Assert.assertEquals(element.getText(),"Hello World!");
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

}
