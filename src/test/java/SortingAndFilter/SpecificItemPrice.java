package SortingAndFilter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpecificItemPrice {

    @Test
    public void test()
    {
        System.setProperty("webdriver.chrome.driver", "C:/Driver/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        List<String> items = new ArrayList<String>();
        String itemPriceTofind = "Almond";
        String Price ="";

        while(!Boolean.parseBoolean(driver.findElement(By.xpath("//*[@aria-label='Next']")).getDomAttribute("aria-disabled")))
        {
            items.addAll(driver.findElements(By.xpath("//tbody/tr/td[1]")).stream().map(s->s.getText()).collect(Collectors.toList()));
            WebElement Next = driver.findElement(By.xpath("//*[@aria-label='Next']"));
            Next.click();
        }
        items.addAll(driver.findElements(By.xpath("//tbody/tr/td[1]")).stream().map(s->s.getText()).collect(Collectors.toList()));
        for (int i = 0; i < items.size(); i++)
        {
            String itemText = items.get(i);
            if(itemText.equalsIgnoreCase(itemPriceTofind))
            {
                //Price =items.get(i).findElement(By.xpath("./following-sibling::td[1]")).getText();
               // Price = (WebElement(itemText)).findElement(By.xpath(".//following-sibling::td[1]")).getText());
                break;
            }
        }
        System.out.println("The Price of "+itemPriceTofind+" is "+Price);
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }
}
