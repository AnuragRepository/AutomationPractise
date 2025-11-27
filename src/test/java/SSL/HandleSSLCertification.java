package SSL;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class HandleSSLCertification {

    @Test
    public void handleSSLCertification() {
        //WAP to handle SSL certification
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://expired.badssl.com/");
        closeBrowser(driver);
    }


    public static void closeBrowser(WebDriver driver) {
        driver.quit();
    }
}
