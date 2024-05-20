package com.otus.components;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.inject.Inject;
import com.otus.actions.CommonActions;
import com.otus.annotations.Component;
import com.otus.guice.ScenarioScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class AnyComponentsAbs<T> extends CommonActions<T> {

  protected Actions actions;

  @Inject
  public AnyComponentsAbs(ScenarioScoped scenarioScoped) {
    super(scenarioScoped);
    actions = new Actions(driver);
  }

  public WebElement getComponentEntity() {
    return $(getComponentLocator());
  }

  private By getComponentLocator() {
    Component component = getClass().getAnnotation(Component.class);

    if (component != null) {
      String value = component.value();

      String searchStrategy = "";

      Pattern pattern = Pattern.compile("^(\\w+):.*?");
      Matcher matcher = pattern.matcher(value);
      if (matcher.find()) {
        searchStrategy = matcher.group(1).toLowerCase(Locale.ROOT);
      }

      switch (searchStrategy) {
        case "xpath":
          return By.xpath(value.replace(String.format("%s:", searchStrategy), ""));
        case "id":
          return By.id(value.replace(String.format("%s:", searchStrategy), ""));
      }
    }

    return null;
  }
}
