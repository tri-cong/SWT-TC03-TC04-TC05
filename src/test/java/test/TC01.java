package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import driver.driverFactory;

public class TC01 {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");
    }

    @Test
    public void runTC01Test() {
        String expectedTitle = "Home page";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title verification failed");

        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        WebElement sortByDropdown = driver.findElement(By.cssSelector("select[title='Sort By']"));
        Select select = new Select(sortByDropdown);
        select.selectByVisibleText("Name");

        WebElement productList = driver.findElement(By.className("products-grid"));
        Assert.assertTrue(isSorted(productList, "h2"), "Products are not sorted by name");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

    private boolean isSorted(WebElement element, String tag) {
        java.util.List<WebElement> elements = element.findElements(By.tagName(tag));
        String[] arr = new String[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            arr[i] = elements.get(i).getText();
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}
