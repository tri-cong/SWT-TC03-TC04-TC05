package test;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TC02 {
    @Test
    public void runTC02Test() {
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");

        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        WebElement costElement = driver.findElement(By.xpath("//span[contains(text(),'$100.00')]"));
        String cost = costElement.getText();
        System.out.println("Cost of Sony Xperia mobile is: " + cost);

        WebElement sonyXperiaLink = driver.findElement(By.xpath("//a[@title='Sony Xperia']"));
        sonyXperiaLink.click();

        WebElement detailElement = driver.findElement(By.xpath("//span[@class='h1']"));
        String detailProName = detailElement.getText();
        System.out.println("Sony Xperia mobile from detail page is: " + detailProName);
        WebElement detailElementCost = driver.findElement(By.xpath("//span[@class='price']"));
        String detailcost = detailElementCost.getText();
        System.out.println("Cost of Sony Xperia mobile from detail page is: " + detailcost);

        if (cost.equals(detailcost)) {
            System.out.println("Product value in list and detail page are equal.");
        } else {
            System.out.println("Product value in list and detail page are not equal.");
        }

        driver.quit();
    }
}
