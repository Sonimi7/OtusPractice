package waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiters implements IWaiters{

    private WebDriver driver = null;
   // WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public Waiters(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public boolean waitForCondition(ExpectedCondition condition) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            webDriverWait.until(condition);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean waitForElementVisible(WebElement element) {
        return waitForCondition(ExpectedConditions.visibilityOf(element));
    }

    public boolean waitForPresentsElementLocated(WebElement element) {
        return waitForCondition(ExpectedConditions.presenceOfElementLocated((By) element));
    }

    public boolean waitForElementNotVisible(WebElement element) {
        return waitForCondition(ExpectedConditions.invisibilityOf(element));
    }

//    public boolean waitForNotVisibleSubMenu(WebElement element, String name) {
//        WebElement subMenuElement = driver.findElement(By.xpath(String.format("//span[text()='%s']", name)));
//        String classAttributeValue = subMenuElement.getAttribute("class");
//
//        webDriverWait.until(driver1 -> !driver1.findElement((By) subMenuElement).getAttribute("class").equals(classAttributeValue));
//        return true;
//    }
}
