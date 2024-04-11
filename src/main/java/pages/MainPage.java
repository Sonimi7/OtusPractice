package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage extends AnyPageAbs<MainPage> {
    public String expectedTileCategory = "";
    private By categoryCoursesSubMenuLocator = By.xpath("//nav/div[2]/following-sibling::div/div/div/div/div/a[contains(@href, '/categories')]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String clickRandomCategoryCourses() {
        List<WebElement> listCategoryCourseSubMenu = driver.findElements(categoryCoursesSubMenuLocator);

        Faker faker = new Faker();
        WebElement randomCategoryCourse = faker.options().nextElement(listCategoryCourseSubMenu);
        expectedTileCategory = randomCategoryCourse.getText();

        try {
            randomCategoryCourse.click();
        } catch (StaleElementReferenceException ex) {
            randomCategoryCourse = driver.findElement(
                    By.xpath("//nav/div[2]/following-sibling::div/div/div/div/div/a[contains(text(), '" + expectedTileCategory + "')]"));
            randomCategoryCourse.click();
        }

        System.out.println("expectedTileCategory = " + expectedTileCategory);
        return expectedTileCategory;
    }

}
