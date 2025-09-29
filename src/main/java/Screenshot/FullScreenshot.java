package Screenshot;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FullScreenshot {
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver","C:/Driver/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");

       File src =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("C://Users//dell//automationscreenshot1.png"));
        FileUtils.copyFile(src,new File("C://Users//dell//IntelliJ//AUTOMATION//automationscreenshot2.png"));


    }

}
