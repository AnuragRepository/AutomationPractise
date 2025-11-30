package Basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FramesPracTest {

    static WebDriver driver;
    Actions actionObj;

    FramesPracTest()
    {
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver() ;
       driver.manage().window().maximize();
       actionObj = new Actions(driver);
    }

    @Test
    public void test()  {
        String xpathStringSelectable = "//div[@id='content']/h1";
        String xpathStringSortable = "//div[@id='content']/h1";
        String xpathStringResizable = "//div[@id='content']/h1";
        driver.get("https://jqueryui.com/droppable/");
        System.out.println("Total No. of frames in droppable page = " + driver.findElements(By.tagName("iframe")).size());
        //driver.switchTo().frame(0);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        System.out.println("Before drop = " + target.getDomAttribute("class"));
        Assert.assertEquals(target.getDomAttribute("class"), "ui-widget-header ui-droppable");

        //draganddrop
        actionsMethodDragAndDrop(driver, source, target);
        System.out.println("After drop = " + target.getDomAttribute("class"));
        Assert.assertEquals(target.getDomAttribute("class"), "ui-widget-header ui-droppable ui-state-highlight");
        driver.switchTo().defaultContent();
        driver.findElement(By.linkText("Selectable")).click();
        explicitWait(driver, xpathStringSelectable);
        System.out.println("Total No. of frames in selectable page = " +driver.findElements(By.tagName("iframe")).size());
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        WebElement item3 = driver.findElement(By.xpath("//ol[@id='selectable']/li[3]"));

        Assert.assertEquals(item3.getDomAttribute("class"), "ui-widget-content ui-selectee");
        //selectable
        actionsMethodSelectable(driver, item3);
        Assert.assertEquals(item3.getDomAttribute("class"), "ui-widget-content ui-selectee ui-selected");
        driver.switchTo().defaultContent();

        //sortable
        driver.findElement(By.linkText("Sortable")).click();
        explicitWait(driver, xpathStringSortable);
        driver.switchTo().frame(0);
        List<String> originalList= getItemList(driver);
        WebElement sourceSort = driver.findElement(By.xpath("//ul[@id='sortable']/li[1]"));
        WebElement targetSort = driver.findElement(By.xpath("//ul[@id='sortable']/li[3]"));
        actionsMethodDragAndDropSortList(driver, sourceSort, targetSort);
        List<String> reorderedList= getItemList(driver);
        Assert.assertNotEquals(originalList,reorderedList);
        driver.switchTo().defaultContent();

        //Resizable
        driver.findElement(By.xpath("//a[text()='Resizable']")).click();
        explicitWait(driver,xpathStringResizable);
        driver.switchTo().frame(0);
        WebElement resize = driver.findElement(By.xpath("//div[@id='resizable']/div[3]"));
        resizable(resize);
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

    public void resizable(WebElement resize)
    {
      //actionObj.clickAndHold(resize).moveByOffset(200,0).release().build().perform();
      actionObj.dragAndDropBy(resize,200,0).build().perform();
      //WebElement Parent = resize.findElement(By.xpath("/parent::div/parent::body"));
      WebElement Parent = driver.findElement(By.xpath("//div[@id='resizable']/div[3]/parent::div/parent::body"));

      System.out.println("Resized attribute = "+Parent.getDomAttribute("style"));
      Assert.assertEquals(Parent.getDomAttribute("style"),"cursor: auto;");
    }


    public static void actionsMethodDragAndDropSortList(WebDriver driver, WebElement sourceSort, WebElement targetSort) {
        Actions actionObj = new Actions(driver);

        //working
       actionObj.dragAndDropBy(sourceSort,0,60).release().build().perform();
       //actionObj.clickAndHold(sourceSort).moveToElement(targetSort).dragAndDropBy(sourceSort,0,15).release().build().perform();
       //actionObj.clickAndHold(sourceSort).moveToElement(targetSort).moveByOffset(0,15).release().build().perform();

    }

    public static List<String> getItemList(WebDriver driver) {

        List<WebElement> itemList = driver.findElements(By.xpath("//ul[@id='sortable']/li"));
        List<String> itemListText = new ArrayList<String>();
        for (int i = 0; i < itemList.size(); i++) {

            itemListText.add(itemList.get(i).getText());
        }
        return  itemListText;
    }

    public static void explicitWait(WebDriver driver, String xpathString) {
        WebDriverWait waitObj = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitObj.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathString)));
    }

    public static void actionsMethodSelectable(WebDriver driver, WebElement item3) {
        Actions actionObj = new Actions(driver);
        actionObj.moveToElement(item3).click().build().perform();
    }

    public static void actionsMethodDragAndDrop(WebDriver driver, WebElement source, WebElement target) {
        Actions actionObj = new Actions(driver);
        actionObj.dragAndDrop(source, target).build().perform();
    }






}

