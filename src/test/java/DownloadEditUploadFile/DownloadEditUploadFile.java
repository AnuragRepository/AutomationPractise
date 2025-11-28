package DownloadEditUploadFile;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

public class DownloadEditUploadFile {

    @Test
    public void DownloadEditUploadFileTest() throws InterruptedException, FilloException {

        //Fetch original fruit price in UI
        //Download the excel
        //Edit fruit price in downloaded excel
        //upload the modified excel
        //verify updated fruit price in UI
        //Delete downloaded excel in project for further test re-run

        String originalPriceValueInExcel = "";
        String updatedPriceValueInExcel = "";
        String fruitPriceToUpdate = "2000";
        String fruitName = "Orange";
        String fruitPriceInUI ="";
        WebDriver driver = null;
        String downloadPath = System.getProperty("user.dir");
        String url = "https://rahulshettyacademy.com/upload-download-test/index.html";

        driver = initialization(downloadPath,url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Fetch original banana price in UI
        fruitPriceInUI = getBananaPriceFromUI(driver,fruitName);

        //Download the excel and verify
        File file = excelDownloadAndVerify(wait,driver,downloadPath);

        //Edit banana price in download excel
        updatedPriceValueInExcel = editBananaPriceInDownloadedExcel(downloadPath,originalPriceValueInExcel,updatedPriceValueInExcel,fruitPriceToUpdate,fruitName);

        //upload the modified excel
        uploadEditedExcel(wait,driver,downloadPath);

        //verify updated banana price in UI
        fruitPriceInUI =  getBananaPriceFromUI(driver,fruitName);
        Assert.assertEquals(fruitPriceInUI,updatedPriceValueInExcel);
        Assert.assertEquals(fruitPriceInUI,fruitPriceToUpdate);

        //Delete downloaded excel in project for further test re-run,
        // otherwise test will consider old excel not latest downloaded excel due to name
        // change download w.r.t download(1), download(2) and so on

        deleteDownloadedExcel(file);

        closeBrowser(driver);

    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

    private void deleteDownloadedExcel(File file) {
        if (file.delete()) {

            System.out.println("Uploaded File deleted successfully from Project.");
        } else {
            System.out.println("File could not be deleted (file may not exist or is in use).");
        }

    }


    private void uploadEditedExcel(WebDriverWait wait, WebDriver driver, String downloadPath) throws InterruptedException {
        //upload the excel

        Thread.sleep(5000);
        WebElement chooseFile = driver.findElement(By.xpath("//input[@type='file']"));
        chooseFile.sendKeys(downloadPath+File.separator+"download.xlsx");
        WebElement uploadSuccessMessage = driver.findElement(By.xpath("//div[text()='Updated Excel Data Successfully.']"));
        wait.until(ExpectedConditions.visibilityOf(uploadSuccessMessage));
        System.out.println(uploadSuccessMessage.getText());
        Assert.assertEquals(uploadSuccessMessage.getText(),"Updated Excel Data Successfully.");
        wait.until(ExpectedConditions.invisibilityOf(uploadSuccessMessage));

    }

    private String editBananaPriceInDownloadedExcel(String downloadPath, String originalPriceValueInExcel, String updatedPriceValueInExcel, String fruitPriceToUpdate, String fruitName) throws FilloException {
        //Edit banana price in downloaded excel

        Fillo fillo = new Fillo();
        Connection connection = fillo.getConnection(downloadPath+File.separator+"download.xlsx");
        Recordset recordset = connection.executeQuery("select * from Sheet1");

        while (recordset.next())
        {
            String Element = recordset.getField("fruit_name");
            if(Element.equalsIgnoreCase(fruitName))
            {
                originalPriceValueInExcel = recordset.getField("price");
                if(!originalPriceValueInExcel.equalsIgnoreCase(fruitPriceToUpdate))
                {
                    connection.executeUpdate("update Sheet1 set price = '"+fruitPriceToUpdate+"' where fruit_name = '"+fruitName+"'");
                    //We have to create new Recordset object as old one point to existing original Price as
                    // Recordset in Fillo does NOT auto-refresh after an update.
                    Recordset updatedRecordset = connection.executeQuery("select price from Sheet1 where fruit_name = '"+fruitName+"'");
                    updatedRecordset.next();
                    updatedPriceValueInExcel =  updatedRecordset.getField("price");
                    System.out.println("Updated Banana Price Value in excel = "+updatedPriceValueInExcel);
                }
                else{
                    updatedPriceValueInExcel = originalPriceValueInExcel;
                    System.out.println("Existing and Requested fruit price are same in excel");
                }
            }
        }
        //otherwise delete excel will not work
        recordset.close();
        connection.close();
        return updatedPriceValueInExcel;
    }

    private File excelDownloadAndVerify(WebDriverWait wait,WebDriver driver, String downloadPath) throws InterruptedException {
        //Download the excel
        By downloadButtonXpath = By.xpath("//button[@id='downloadButton']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(downloadButtonXpath));
        WebElement downloadButton = driver.findElement(downloadButtonXpath);
        downloadButton.click();
        Thread.sleep(5000);
        //verify if excel is downloaded and placed in project path
        File file = new File(downloadPath+ File.separator +"download.xlsx");
        if(file.exists())
        {
            Assert.assertTrue(file.exists());
            System.out.println("File downloaded successfully and placed in project");
        }
        else
        {
            System.out.println("File not found");
            Assert.assertTrue(false);
        }
        return file;

    }

    private String getBananaPriceFromUI(WebDriver driver, String fruitName ) {
        //Fetch original banana price in UI
        WebElement fruitPrice = driver.findElement(By.xpath("//div[contains(@class, 'sc-hIPBNq')]/div/div[2] //div[text()='"+fruitName+"']/parent::div/following-sibling::div[2]"));
        String fruitPriceInUI = fruitPrice.getText();
        System.out.println("Current Banana Price Value in UI = "+fruitPriceInUI);
        return fruitPriceInUI;
    }

    private WebDriver initialization(String downloadPath,String url) {
        WebDriverManager.chromedriver().setup();

        //for setting the attachment default downloading path in project
        HashMap<String,Object> chromePrefs = new HashMap<String,Object>();
        chromePrefs.put("profile.default_content_settings.popups",0);
        chromePrefs.put("download.default_directory",downloadPath);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs",chromePrefs);
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

}
