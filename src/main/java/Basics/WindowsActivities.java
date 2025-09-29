package Basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowsActivities {

    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver","C:/Driver/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://selectorshub.com/xpath-practice-page/");
        driver.navigate().to("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.navigate().back();
        driver.navigate().forward();
        driver.manage().window().fullscreen();
    }

}
