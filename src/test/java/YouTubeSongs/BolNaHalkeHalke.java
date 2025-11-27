package YouTubeSongs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class BolNaHalkeHalke {
    @Test
    public void test()
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.youtube.com/watch?v=jXwg9l9D51A");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5000));

        WebElement replay = driver.findElement(By.xpath("//*[@class='ytp-left-controls']/button"));

        if(replay.getDomAttribute("data-title-no-tooltip").equalsIgnoreCase("Play")||replay.getDomAttribute("data-title-no-tooltip").equalsIgnoreCase("Pause"))
        {
            driver.findElement(By.xpath("//*[@data-tooltip-title='Play (k)']")).click();
            //something went wrong displaying after 1 mints and due to below wait its keep on waiting so commenting
            //wait.until(ExpectedConditions.domAttributeToBe(replay,"data-title-no-tooltip","Replay"));
        }
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

}
