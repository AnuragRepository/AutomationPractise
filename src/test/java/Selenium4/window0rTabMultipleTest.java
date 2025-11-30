package Selenium4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class window0rTabMultipleTest {
    @Test
    public void test() {

        //WAP to enter text in Name label of 1st URL from grabbed 1st course from another 2nd  URL- VVI
        WebDriverManager.chromedriver().setup();
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
        String firstCourse = driver.findElement(By.xpath("//*[contains(@class,'grid sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-5 gap-6 md:gap-4 mb-12')]/div[1]/div[2]/h3")).getText();
        System.out.println("firstCourse ="+firstCourse);
        driver.switchTo().window(parentWindowID);
        //selenium 4 locator not working may be due to flex elements
        //WebElement nameLabel = driver.findElement(By.xpath("//*[text()='Name']"));
        //WebElement nameTextBox = driver.findElement(with(By.tagName("input")).below(By.xpath("//*[text()='Name']")));
        //WebElement nameTextBox = driver.findElement(with(By.tagName("input")).below(nameLabel));
        WebElement nameTextBox = driver.findElement(By.xpath("//*[@name='name']"));
        nameTextBox.sendKeys(firstCourse);
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

    }

