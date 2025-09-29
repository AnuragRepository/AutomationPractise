package Basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FramePrac2 {

    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver","C:/Driver/chromedriver-win64/chromedriver.exe");
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

    }



}
