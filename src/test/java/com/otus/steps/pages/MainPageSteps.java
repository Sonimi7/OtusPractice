package com.otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import com.otus.components.BlockWithItemsComponents;
import com.otus.guice.ScenarioScoped;
import com.otus.pages.CoursesPage;
import com.otus.pages.MainPage;

public class MainPageSteps {

  @Inject
  private MainPage mainPage;

  @Inject
  private CoursesPage coursesPage;

  @Inject
  private ScenarioScoped scenarioScoped;

  @Inject
  private BlockWithItemsComponents blockWithItemsComponents;

  @Пусть("Открываем главную страницу")
  public void openMainPage() {
    mainPage.open();
  }

  @Если("Кликнуть в рандомный курс в хедере")
  public void clickRandomCourseHeader() {
    blockWithItemsComponents.moveToElement1();
    blockWithItemsComponents.clickRandomCategoryCourses();
  }

  @Тогда("Переход в каталог курсов с установленным фильтром по теме курса")
  public void checkFilterState() {
    coursesPage.checkStateCheckboxCategory(blockWithItemsComponents.expectedTileCategory);
  }
}
