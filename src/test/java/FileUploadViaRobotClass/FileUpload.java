package FileUploadViaRobotClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class FileUpload {

    @Test
    public void fileUploadViaRobotClass() throws AWTException, InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://resume.naukri.com/");
        WebElement titleHeader = driver.findElement(By.linkText("RESUME WRITING"));
        By columnTitle =  By.xpath("//*[@title='Resume Quality Score']");
        Actions actions = new Actions(driver);
        actions.moveToElement(titleHeader).moveToElement(driver.findElement(columnTitle)).click().build().perform();
        WebElement browseButton = driver.findElement(By.xpath("//span[@class='browse']"));
        browseButton.click();
        Thread.sleep(3000);

        //Create Robot class object
        Robot robotObj = new Robot();

        String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\FileUploadViaRobotClass\\resume25.docx";

        //File to upload Location Pass
        StringSelection stringSelection = new StringSelection(filePath);

        //for copying file location to clipboard i.e Control+C
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,null);

        //Press Control Key
        robotObj.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(1000);

        //Press Paste Key
        robotObj.keyPress(KeyEvent.VK_V);
        Thread.sleep(1000);

        //Release Paste Key
        robotObj.keyRelease(KeyEvent.VK_V);
        Thread.sleep(1000);

        //Release Control Key
        robotObj.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(1000);

        //Press Enter
        robotObj.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(1000);

        //Release Enter
        robotObj.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);

        By messageXpath = By.xpath("//*[@class='leftSectionReport']/div/p");
        By errorTechnicalApplicationXpath = By.xpath("//p[contains(@class,'txtBrightRed')]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try{
            if(wait.until(ExpectedConditions.visibilityOfElementLocated(messageXpath)).isDisplayed())
            {
                WebElement message = driver.findElement(messageXpath);
                System.out.println("Success Message = \n"+message.getText());
                Assert.assertEquals(message.getText(),"Your resume quality is low. Improve it to leave a good first impression on recruiters.");
            }
        }
        catch (TimeoutException exception){
        WebElement errorMessage = driver.findElement(errorTechnicalApplicationXpath);
        System.out.println("Known Application Error due to file upload > 5 times = \n"+errorMessage.getText());

       }
        driver.quit();
    }

}
