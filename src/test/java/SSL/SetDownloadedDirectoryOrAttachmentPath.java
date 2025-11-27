package SSL;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SetDownloadedDirectoryOrAttachmentPath {

    @Test
    public void setDownloadedDirectoryOrAttachmentPath() {

        //WAP to set downloaded directory/attachment path
       /* WebDriverManager.chromedriver().setup();
        Map<String,Object> prefs = new HashMap<String,Object>();
        prefs.put("download.default_directory","/directory/path");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("perfs",prefs);
        WebDriver driver = new ChromeDriver(chromeOptions);
        closeBrowser(driver);*/
    }



    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

}
