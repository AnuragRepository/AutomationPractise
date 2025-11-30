package SSL;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class AutomaticPopUPsDisableTest {

    static WebDriver driver;


    @Test(priority = 0)
    public void automaticPopUPNotDisable() {

        //Without pop up disable
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        initialize(chromeOptions);
        WebElement buttonInPopUp = driver.findElement(By.xpath("(//*[@class='a-button-text'])[1]"));
        Assert.assertTrue(buttonInPopUp.isDisplayed());
    }

        @Test(priority = 1)
        public void automaticPopUPDisable() {
        //WAP to block automatic pop up windows
        // With pop up disable
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

            initialize(chromeOptions);

        }
    public void initialize(ChromeOptions chromeOptions)
    {

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");
    }


}
