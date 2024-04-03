package components;

import annotations.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CoursesPage;

@Component("xpath://span[text()='%s']")
public class BlockWithItemsComponents extends AnyComponentsAbs<BlockWithItemsComponents> {

    public BlockWithItemsComponents(WebDriver driver) {
        super(driver);
    }

    /** Клик на обучение в хедере
     */
    public CoursesPage clickItem(String name) {
        getComponentEntity().findElement(By.xpath(String.format("//span[text()='%s']", name))).click();

        return new CoursesPage(driver);
    }

}
