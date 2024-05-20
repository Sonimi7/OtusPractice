package com.otus.steps.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import com.otus.guice.ScenarioScoped;

public class Hooks {

  @Inject
  private ScenarioScoped scenarioScoped;

  @After
  public void after() {
    if(scenarioScoped.driver != null) {
      scenarioScoped.driver.close();
      scenarioScoped.driver.quit();
    }
  }

}
