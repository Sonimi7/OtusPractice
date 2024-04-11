package components;

import annotations.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Component("xpath://span[text()='%s']")
public class BlockWithItemsComponents extends AnyComponentsAbs<BlockWithItemsComponents> {

    private By subMenuElementLocator = By.xpath("//nav/div[3]");
    private By menuHover = By.xpath("//nav/div[2]");

    public BlockWithItemsComponents(WebDriver driver) {
        super(driver);
    }

    public BlockWithItemsComponents moveToElement1() {
        waiters.waitForElementVisible(driver.findElement(subMenuElementLocator));
        WebElement subMenuElement = driver.findElement(subMenuElementLocator);

        String classAttributeValue = subMenuElement.getAttribute("class");

        Actions actions = new Actions(driver);
        WebElement menuDrop = driver.findElement(menuHover);
        actions.moveToElement(menuDrop).perform();

        waiters.waitForCondition(driver -> !subMenuElement.getAttribute("class").equals(classAttributeValue));
        return this;
    }

}
