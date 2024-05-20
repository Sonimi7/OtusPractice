package com.otus.components;
import java.util.List;

import com.google.inject.Inject;
import com.otus.annotations.Component;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.otus.guice.ScenarioScoped;

@Component("xpath://span[text()='%s']")
public class BlockWithItemsComponents extends AnyComponentsAbs<BlockWithItemsComponents> {

  public String expectedTileCategory = "";
  private By categoryCoursesSubMenuLocator = By.xpath("//nav/div[2]/following-sibling::div/div/div/div/div/a[contains(@href, '/categories')]");

  private By subMenuElementLocator = By.xpath("//nav/div[3]");
  private By menuHover = By.xpath("//nav/div[2]");

  @Inject
  public BlockWithItemsComponents(ScenarioScoped scenarioScoped) {
    super(scenarioScoped);
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
