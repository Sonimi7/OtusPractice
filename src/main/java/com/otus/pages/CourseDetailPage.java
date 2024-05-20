package com.otus.pages;

import com.google.inject.Inject;
import com.otus.guice.ScenarioScoped;
import org.openqa.selenium.By;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CourseDetailPage extends AnyPageAbs<CourseDetailPage> {

  @Inject
  public CourseDetailPage(ScenarioScoped scenarioScoped) {
    super(scenarioScoped);
  }

  public void assertTitleCardCourse(String courseName) {
    String factResult = driver.findElement(By.xpath("//h1")).getText();
    assertThat(courseName).contains(factResult);
  }
}
