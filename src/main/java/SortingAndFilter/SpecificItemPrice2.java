package SortingAndFilter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpecificItemPrice2 {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/Driver/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        List<WebElement> items = new ArrayList<WebElement>();
        String itemPriceTofind = "Almond";
        String Price = "";
        do {
            items = driver.findElements(By.xpath("//tbody/tr/td[1]"));
            for (int i = 0; i < items.size(); i++) {
                String itemText = items.get(i).getText();
                if (itemText.equalsIgnoreCase(itemPriceTofind)) {
                    //Price =items.get(i).findElement(By.xpath("./following-sibling::td[1]")).getText();
                    Price = items.get(i).findElement(By.xpath(".//following-sibling::td[1]")).getText();
                    System.out.println("The Price of " + itemPriceTofind + " is " + Price);
                    break;
                }
            }
            if (Price == "") {
                WebElement Next = driver.findElement(By.xpath("//*[@aria-label='Next']"));
                Next.click();
            }
        }
        while (Price == "");
    }
}
