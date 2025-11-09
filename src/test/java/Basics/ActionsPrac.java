package Basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ActionsPrac {

    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
        WebElement element = driver.findElement(By.xpath("//*[@id='nav-link-accountList']/a[1]"));
        WebElement elementMouseHoverSpace = driver.findElement(By.xpath("//*[@id='nav-al-container']"));
        WebElement textBox= driver.findElement(By.xpath("//div[@class='nav-search-field ']/input[1]"));
        Actions actionObj = new Actions(driver);
       // 1. mousehover element
        Assert.assertFalse(elementMouseHoverSpace.isDisplayed());
        actionObj.moveToElement(element).build().perform();
        Assert.assertTrue(elementMouseHoverSpace.isDisplayed());

        //2. type in uppercase i.e shift+character enter, both work but click mandatory for enter text via actions class
        actionObj.moveToElement(textBox).click().keyDown(Keys.SHIFT).sendKeys("anurag").build().perform();
        actionObj.click(textBox).keyDown(Keys.SHIFT).sendKeys(" anurag").build().perform();

        //3. right click on webelement
        actionObj.moveToElement(element).contextClick().build().perform();
        actionObj.contextClick(textBox).build().perform();

        //4.select the text on screen - use double click, if space between character it will select 2nd character
        actionObj.moveToElement(textBox).doubleClick().build().perform();
        actionObj.doubleClick(textBox).build().perform();
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }


}
