package com.otus.guice;

import org.openqa.selenium.WebDriver;
import com.otus.factory.DriverFactory;

@io.cucumber.guice.ScenarioScoped
public class ScenarioScoped {

  public WebDriver driver = new DriverFactory().create();

  public String nameCourse = "";

  public String getNameCourse() {
    return nameCourse;
  }

  public void setNameCourse(String nameCourse) {
    this.nameCourse = nameCourse;
  }
}
