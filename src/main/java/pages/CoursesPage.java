package pages;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CoursesPage extends AnyPageAbs<CoursesPage>{

    @Inject
    public CourseDetailPage courseDetailPage;

    public CoursesPage(WebDriver driver) {
        super(driver);
    }


    public CourseDetailPage clickCourseByName(String courseName) {
        driver.findElements(By.xpath("//section[2]/div[2]"))
                .stream()
                .filter(el -> el.getText().contains(courseName))
                .findFirst().get().click();

        return courseDetailPage;
    }
}
