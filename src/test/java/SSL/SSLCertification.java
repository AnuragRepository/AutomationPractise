package SSL;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SSLCertification {
    @Test
    public void test()
    {
        //WAP to handle SSL certification
       // ChromeOptions chromeOptions = new ChromeOptions();
       // chromeOptions.setAcceptInsecureCerts(true);
        //WebDriverManager.chromedriver().setup();
       // WebDriver driver = new ChromeDriver(chromeOptions);
        //driver.manage().window().maximize();
        //driver.get("https://expired.badssl.com/");

        //WAP to add extension in browser
        //chromeOptions.addExtensions(new File("/Driver/chromedriver-win64/chromedriver.exe"));

        //WAP to set proxy in browser
        /*Proxy proxy = new Proxy();
        proxy.setHttpProxy("ipcode:4444 ask network team");
        chromeOptions.setCapability("proxy",proxy);*/

        //WAP to block automatic pop up windows

      /*  ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");
        driver.findElement(By.xpath("//*[@class='a-button-text']")).click();*/

        //WAP to set downloaded directory/attachment path
        Map<String,Object> prefs = new HashMap<String,Object>();
        prefs.put("download.default_directory","/directory/path");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("perfs",prefs);
        WebDriver driver = new ChromeDriver(chromeOptions);
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

}
