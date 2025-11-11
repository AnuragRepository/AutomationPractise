package Screenshot;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class FullScreenshot {
    @Test
    public void test() throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");

        TakesScreenshot ts = (TakesScreenshot)driver;
        File file = ts.getScreenshotAs(OutputType.FILE);
        File src = new File(System.getProperty("user.dir")+"//ScreenshotFolder//AutomationScreenshot.png");
        FileUtils.copyFile(file,src);
        closeBrowser(driver);


     /*   TakesScreenshot ts = (TakesScreenshot)driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        File file = new File (System.getProperty("user.dir")+"//ScreenshotFolder//AutomationScreenshot.png");
        FileUtils.copyFile(src,file);
        closeBrowser(driver);*/
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

}
