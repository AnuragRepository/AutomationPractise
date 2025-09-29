package Selenium4;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class Screenshot {
    public static void main(String[] args) throws IOException {
        System.setProperty("wedriver.chrome.drver","");
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

    }

}
