package Basics;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionPrac2 {

    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver","C:/Driver/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
        WebElement searchBox = driver.findElement(By.xpath("//div[@class='nav-search-field ']/input[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(searchBox).click().keyDown(Keys.SHIFT).sendKeys("anurag").keyUp(Keys.SHIFT).sendKeys("a").build().perform();

    }

}
