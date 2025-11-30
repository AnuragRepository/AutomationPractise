package Basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class FramePrac2Test {

    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://jqueryui.com/droppable/");
        int frameNumbers = driver.findElements(By.tagName("iframe")).size();
        System.out.println("Total Frames available = "+frameNumbers);
        driver.switchTo().frame(0);
        WebElement draggedElement = driver.findElement(By.xpath("//*[@id='draggable']"));
        WebElement droppedElement = driver.findElement(By.xpath("//*[@id='droppable']"));
        boolean elementDisplayed = driver.findElement(By.xpath("//*[@id='draggable']")).isDisplayed();
        System.out.println("elementDisplayed = "+elementDisplayed);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggedElement,droppedElement).build().perform();
        driver.switchTo().defaultContent();
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }




}
