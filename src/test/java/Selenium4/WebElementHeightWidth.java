package Selenium4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WebElementHeightWidth {

    @Test
    public void test()
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        WebElement nameTextBox = driver.findElement(By.xpath("(//*[@name='name'])[1]"));
        System.out.println(nameTextBox.getSize());
        System.out.println(nameTextBox.getRect().getHeight());
        System.out.println(nameTextBox.getRect().getDimension().getHeight());
        System.out.println(nameTextBox.getRect().getDimension().getWidth());
        System.out.println(nameTextBox.getRect().getWidth());
        System.out.println(nameTextBox.getRect().getX());
        System.out.println(nameTextBox.getRect().getY());
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

}
