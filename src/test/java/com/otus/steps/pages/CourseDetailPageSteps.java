package com.otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import com.otus.guice.ScenarioScoped;
import com.otus.pages.CourseDetailPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CourseDetailPageSteps {
  @Inject
  private CourseDetailPage courseDetailPage;
  @Inject
  private ScenarioScoped scenarioScoped;

  @Тогда("Откроется детальная страница курса")
  public void assertDetailCardCourse() {
    courseDetailPage.assertTitleCardCourse(scenarioScoped.getNameCourse());
  }
}
