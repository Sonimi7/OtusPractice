package pages;

import annotations.UrlPrefix;
import com.google.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.function.BinaryOperator;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@UrlPrefix("/catalog/courses")
public class CoursesPage extends AnyPageAbs<CoursesPage> {

  @Inject
  public CourseDetailPage courseDetailPage;
  @FindBy(xpath = "//section//div[not(@style)]/a[contains(@href, '/lessons/')][.//h6]")
  private List<WebElement> cardsCourses;

  public CoursesPage(WebDriver driver) {
    super(driver);
  }


  public CourseDetailPage clickCourseByName(String courseName) {
    driver.findElements(By.xpath("//section//div[not(@style)]/a[contains(@href, '/lessons/')][.//h6]"))
        .stream()
        .filter(el -> el.getText().contains(courseName))
        .findFirst().get().click();

    return courseDetailPage;
  }

  public void checkStateCheckboxCategory(String expectedTileCategory) {
    String valueCheckbox = driver.findElement(By.xpath(String.format("//label[contains(text(), '%s')]/parent::div", expectedTileCategory.replaceAll("\\s+\\(\\d+\\)", ""))))
        .getAttribute("value");
    Assertions.assertEquals("true", valueCheckbox);
  }

  private List<WebElement> searchCoursesEarlierAndLater(boolean isEarly) {
    List<WebElement> list = driver.findElements(By.xpath("//section//div[not(@style)]/a[contains(@href, '/lessons/')]/h6/following-sibling::div/div/div"));

    list.stream()
        .forEach((WebElement element) ->
        {
          waiters.waitForCondition(ExpectedConditions.visibilityOf(element));
          int index = list.indexOf(element);
          List<WebElement> refreshedElements = driver
              .findElements(By.xpath("//section//div[not(@style)]/a[contains(@href, '/lessons/')]/h6/following-sibling::div/div/div"));
          list.set(index, refreshedElements.get(index));
        });

    List<LocalDate> dates = list.stream()
        .map((WebElement element) ->
            LocalDate.parse(element.getText().replaceAll("\\s*·.*$", "").trim(),
                DateTimeFormatter.ofPattern("dd MMMM, yyyy", Locale.forLanguageTag("ru")))
        )
        .toList();


    BinaryOperator<LocalDate> reduceOperator = null;
    if (isEarly) {
      reduceOperator = (LocalDate buff, LocalDate item) -> buff.isBefore(item) ? buff : item;
    } else {
      reduceOperator = (LocalDate buff, LocalDate item) -> buff.isAfter(item) ? buff : item;
    }

    LocalDate earlyDate = dates.stream()
        .reduce(reduceOperator).get();


    List<WebElement> resultsEarlyDate = list.stream()
        .filter((WebElement element) ->
            LocalDate.parse(element.getText().replaceAll("\\s*·.*$", "").trim(),
                DateTimeFormatter.ofPattern("dd MMMM, yyyy", Locale.forLanguageTag("ru"))).isEqual(earlyDate))
        .toList();

    return resultsEarlyDate;

  }

  public void assertTitleCardCourse(boolean isEarly) throws IOException {
    for (WebElement element : searchCoursesEarlierAndLater(isEarly)) {
      WebElement cardCourse = element.findElement(By.xpath("./../../..//h6"));

      String expectedHeader = cardCourse.getText();

      String path = cardCourse.findElement(By.xpath("./..")).getAttribute("href");
      if (path.startsWith("/")) {
        path = getBaseUrl() + path;
      }
      Document doc = Jsoup.connect(path).get();
      String actualTitle = doc.select("h1").get(0).text();
      assertThat(actualTitle).as("").isEqualTo(expectedHeader);
    }
  }


}
