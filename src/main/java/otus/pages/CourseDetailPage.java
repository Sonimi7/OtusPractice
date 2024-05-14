package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CourseDetailPage extends AnyPageAbs<CourseDetailPage> {

  public CourseDetailPage(WebDriver driver) {
    super(driver);
  }

  public void assertTitleCardCourse(String courseName) {
    String factResult = driver.findElement(By.xpath("//h1")).getText();
    assertThat(courseName).contains(factResult);
  }
}
