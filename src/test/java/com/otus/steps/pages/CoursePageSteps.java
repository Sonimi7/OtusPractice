package com.otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import com.otus.guice.ScenarioScoped;
import com.otus.pages.CoursesPage;

import java.io.IOException;

public class CoursePageSteps {
  @Inject
  private CoursesPage coursesPage;

  @Inject
  private ScenarioScoped scenarioScoped;

  @Пусть("Открываем каталог курсов")
  public void open() {
    coursesPage.open();
  }

  @Если("Кликнуть по плитке курса по {string} в каталоге курсов")
  public void clickCardCourseByName(String nameCourse) {
    coursesPage.clickCourseByName(nameCourse);

    scenarioScoped.setNameCourse(nameCourse);
  }

  @Тогда("Проверка названий самых ранних и самых поздних курсов")
  public void searchEarlyAndLaterCourse() throws IOException {
    coursesPage.assertTitleCardCourse(true);
  }
}
