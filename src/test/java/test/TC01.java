package test;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
public class TC01 {
    public static void main(String[] args) {


        WebDriver driver = driverFactory.getChromeDriver();


        driver.get("http://live.techpanda.org/");


        String expectedTitle = "Home page";
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Title verification successful: " + actualTitle);
        } else {
            System.out.println("Title verification failed: " + actualTitle);
        }


        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();


        WebElement sortByDropdown = driver.findElement(By.cssSelector("select[title='Sort By']"));
        Select select = new Select(sortByDropdown);
        select.selectByVisibleText("Name");


        WebElement productList = driver.findElement(By.className("products-grid"));
        if (isSorted(productList, "h2")) {
            System.out.println("Products are sorted by name");
        } else {
            System.out.println("Products are not sorted by name");
        }


        driver.quit();
    }
    private static boolean isSorted(WebElement element, String tag) {
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