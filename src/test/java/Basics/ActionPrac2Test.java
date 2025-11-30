package Basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ActionPrac2Test {

    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
        WebElement searchBox = driver.findElement(By.xpath("//div[@class='nav-search-field ']/input[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(searchBox).click().keyDown(Keys.SHIFT).sendKeys("anurag").keyUp(Keys.SHIFT).sendKeys("a").build().perform();
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

}
