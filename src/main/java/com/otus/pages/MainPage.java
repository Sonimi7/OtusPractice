package com.otus.pages;

import com.google.inject.Inject;
import com.otus.guice.ScenarioScoped;

public class MainPage extends AnyPageAbs<MainPage> {

  @Inject
  public MainPage(ScenarioScoped scenarioScoped) {
    super(scenarioScoped);
  }


}
