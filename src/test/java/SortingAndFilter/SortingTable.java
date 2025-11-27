package SortingAndFilter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortingTable {

    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        List<String> originalList = new ArrayList<String>();

        Select select = new Select(driver.findElement(By.xpath("//*[@id='page-menu']")));
        select.selectByValue("5");//code works for 5,10,20 record counts as well
        driver.findElement(By.xpath("//table/thead/tr/th[1]")).click();
        while (!Boolean.parseBoolean(driver.findElement(By.xpath("//*[@aria-label='Next']")).getDomAttribute("aria-disabled"))) {
            originalList.addAll(driver.findElements(By.xpath("//tbody/tr/td[1]")).stream().map(s -> s.getText()).collect(Collectors.toList()));
            WebElement Next = driver.findElement(By.xpath("//*[@aria-label='Next']"));
            Next.click();
        }
        originalList.addAll(driver.findElements(By.xpath("//tbody/tr/td[1]")).stream().map(s -> s.getText()).collect(Collectors.toList()));
        System.out.print("Original List elements = ");
        originalList.stream().forEach(s -> System.out.print(s + " "));
        System.out.println();
        List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());
        System.out.print("Sorted List elements = ");
        sortedList.stream().forEach(s -> System.out.print(s + " "));
        Assert.assertEquals(originalList, sortedList);
        closeBrowser(driver);
    }

    public static void closeBrowser(WebDriver driver) {
        driver.quit();
    }

}
