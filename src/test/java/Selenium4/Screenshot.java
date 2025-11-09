package Selenium4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Screenshot {
    @Test
    public void test() throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        //selenium3
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("C:/Users/dell/IntelliJ/AUTOMATION/automationscreenshot3.png"));

        //selenium4
        WebElement nameLabel = driver.findElement(By.xpath("//*[text()='Name']"));
        File src1 = nameLabel.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src1,new File("C:/Users/dell/IntelliJ/AUTOMATION/automationscreenshotSelenium4.png"));
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

}
