package Basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FramesPrac {

    WebDriver driver;
    Actions actionObj;

    FramesPrac()
    {
       System.setProperty("webdriver.chrome.driver", "C:/Driver/chromedriver-win64/chromedriver.exe");
       driver = new ChromeDriver() ;
       driver.manage().window().maximize();
       actionObj = new Actions(driver);
    }

    public static void main(String[] args) {
        FramesPrac framesPrac = new FramesPrac();
        String xpathStringSelectable = "//div[@id='content']/h1";
        String xpathStringSortable = "//div[@id='content']/h1";
        String xpathStringResizable = "//div[@id='content']/h1";
        framesPrac.driver.get("https://jqueryui.com/droppable/");
        System.out.println("Total No. of frames in droppable page = " + framesPrac.driver.findElements(By.tagName("iframe")).size());
        //driver.switchTo().frame(0);
        framesPrac.driver.switchTo().frame(framesPrac.driver.findElement(By.xpath("//iframe[@class='demo-frame']")));

        WebElement source = framesPrac.driver.findElement(By.id("draggable"));
        WebElement target = framesPrac.driver.findElement(By.id("droppable"));
        System.out.println("Before drop = " + target.getDomAttribute("class"));
        Assert.assertEquals(target.getDomAttribute("class"), "ui-widget-header ui-droppable");

        //draganddrop
        actionsMethodDragAndDrop(framesPrac.driver, source, target);
        System.out.println("After drop = " + target.getDomAttribute("class"));
        Assert.assertEquals(target.getDomAttribute("class"), "ui-widget-header ui-droppable ui-state-highlight");
        framesPrac.driver.switchTo().defaultContent();
        framesPrac.driver.findElement(By.linkText("Selectable")).click();
        explicitWait(framesPrac.driver, xpathStringSelectable);
        System.out.println("Total No. of frames in selectable page = " + framesPrac.driver.findElements(By.tagName("iframe")).size());
        framesPrac.driver.switchTo().frame(framesPrac.driver.findElement(By.className("demo-frame")));
        WebElement item3 = framesPrac.driver.findElement(By.xpath("//ol[@id='selectable']/li[3]"));

        Assert.assertEquals(item3.getDomAttribute("class"), "ui-widget-content ui-selectee");
        //selectable
        actionsMethodSelectable(framesPrac.driver, item3);
        Assert.assertEquals(item3.getDomAttribute("class"), "ui-widget-content ui-selectee ui-selected");
        framesPrac.driver.switchTo().defaultContent();

        //sortable
        framesPrac.driver.findElement(By.linkText("Sortable")).click();
        explicitWait(framesPrac.driver, xpathStringSortable);
        framesPrac.driver.switchTo().frame(0);
        List<String> originalList= getItemList(framesPrac.driver);
        WebElement sourceSort = framesPrac.driver.findElement(By.xpath("//ul[@id='sortable']/li[1]"));
        WebElement targetSort = framesPrac.driver.findElement(By.xpath("//ul[@id='sortable']/li[3]"));
        actionsMethodDragAndDropSortList(framesPrac.driver, sourceSort, targetSort);
        List<String> reorderedList= getItemList(framesPrac.driver);
        Assert.assertNotEquals(originalList,reorderedList);
        framesPrac.driver.switchTo().defaultContent();

        //Resizable
        framesPrac.driver.findElement(By.xpath("//a[text()='Resizable']")).click();
        explicitWait(framesPrac.driver,xpathStringResizable);
        framesPrac.driver.switchTo().frame(0);
        WebElement resize = framesPrac.driver.findElement(By.xpath("//div[@id='resizable']/div[3]"));
        framesPrac.resizable(resize);


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

