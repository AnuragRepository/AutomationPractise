package Selenium4;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebElementHeightWidth {

    public static void main(String[] args)
    {
        System.setProperty("wedriver.chrome.drver","");
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

    }


}
