package test;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class TC04 {

    public static void main(String[] args) {

        // Create an instance of the ChromeDriver
        WebDriver driver = driverFactory.getChromeDriver();

        // Step 1: Navigate to the specified URL
        driver.get("http://live.techpanda.org/");

        // Step 2: Click on the 'MOBILE' menu
        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        // Step 3: Click on 'Add To Compare' for 2 mobiles (Sony Xperia & Iphone)
        WebElement sonyAddToCompare = driver.findElement(By.xpath("//a[@title='Sony Xperia']//following::a[text()='Add to Compare']"));
        sonyAddToCompare.click();

        WebElement iphoneAddToCompare = driver.findElement(By.xpath("//a[@title='IPhone']//following::a[text()='Add to Compare']"));
        iphoneAddToCompare.click();

        // Step 4: Click on 'COMPARE' button
        WebElement compareButton = driver.findElement(By.xpath("//button[@title='Compare']"));
        compareButton.click();

        // Step 5: Verify the pop-up window and check that the products are reflected in it
        String mainWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String handle : allWindows) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                WebElement popupHeading = driver.findElement(By.xpath("//h1[text()='Compare Products']"));
                System.out.println("Popup window heading: " + popupHeading.getText());
                WebElement product1 = driver.findElement(By.xpath("//a[normalize-space()='Sony Xperia']"));
                WebElement product2 = driver.findElement(By.xpath("//a[normalize-space()='IPhone']"));
                String actualTitle = driver.getTitle();
                System.out.println("Title in the comparison window: ");
                System.out.println(actualTitle);
                System.out.println("Products in the comparison window: ");
                System.out.println(product1.getText());
                System.out.println(product2.getText());
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);


        driver.quit();
    }

}
