package pages;

import annotations.UrlPrefix;
import com.google.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@UrlPrefix("/catalog/courses")
public class CoursesPage extends AnyPageAbs<CoursesPage>{

    @FindBy(xpath = "//section//div[not(@style)]/a[contains(@href, '/lessons/')][.//h6]")
    private List<WebElement> cardsCourses;
    @Inject
    public CourseDetailPage courseDetailPage;

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
        Assertions.assertTrue(valueCheckbox.equals("true"));
    }

//    public List<WebElement> getListCardCourses() {
//        List<WebElement> elementList = (List<WebElement>) cardsCourses.stream();
//        return elementList;
//    }
//    public String getTitleCourseByIndex(int index) {
//        return cardsCourses.get(--index).findElement(By.xpath(".//h6")).getText();
//    }
    private Document getDomPage(int index) throws IOException {
        List<String> urls = cardsCourses.stream()
                .map(element -> element.getAttribute("href"))
                .collect(Collectors.toList());
        String url = urls.get(--index);
        return Jsoup.connect(url).get();
    }

    public CoursesPage searchCoursesEarlierAndLater() {
        List<WebElement> list = driver.findElements(By.xpath("//section//div[not(@style)]/a[contains(@href, '/lessons/')]/h6/following-sibling::div/div/div"));

        List<LocalDate> dates = list.stream()
                .map((WebElement element) ->
                        { waiters.waitForCondition(ExpectedConditions.stalenessOf(element));
                            try { return LocalDate.parse(element.getText().replaceAll("\\s*·.*$", "").trim(),
                                    DateTimeFormatter.ofPattern("dd MMMM, yyyy", Locale.forLanguageTag("ru")));
                            } catch (StaleElementReferenceException e) {
                                int index = list.indexOf(element);
                                List<WebElement> refreshedElements = driver
                                        .findElements(By.xpath("//section//div[not(@style)]/a[contains(@href, '/lessons/')]/h6/following-sibling::div/div/div"));
                                list.set(index, refreshedElements.get(index));
                                return null;
                            }
                        })
                .filter(Objects::nonNull)
                .toList();

        LocalDate earlyDate = dates.stream()
                .reduce((LocalDate buff, LocalDate item) -> buff.isBefore(item) ? buff: item).get();

        List<WebElement> results = list.stream()
                .filter((WebElement element) ->
                        LocalDate.parse(element.getText().replaceAll("\\s*·.*$", "").trim(),
                        DateTimeFormatter.ofPattern("dd MMMM, yyyy", Locale.forLanguageTag("ru"))).isEqual(earlyDate))
                .toList();

        return (CoursesPage) results;
    }

}
