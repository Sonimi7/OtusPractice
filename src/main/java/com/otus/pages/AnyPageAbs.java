package com.otus.pages;

import com.google.inject.Inject;
import com.otus.actions.CommonActions;
import com.otus.annotations.PageValidation;
import com.otus.annotations.UrlPrefix;
import com.otus.guice.ScenarioScoped;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

public abstract class AnyPageAbs<T> extends CommonActions<T> {

  protected String markerLocator = "";

  @Inject
  public AnyPageAbs(ScenarioScoped scenarioScoped) {
    super(scenarioScoped);
    markerLocator = pageValidation();
  }

  private static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
    try {
      return clazz.cast(o);
    } catch (ClassCastException e) {
      return null;
    }
  }

  private String pageValidation() {
    if (getClass().isAnnotationPresent(PageValidation.class)) {
      PageValidation pageValidation = getClass().getAnnotation(PageValidation.class);
      String markerElementLocator = pageValidation.value();
      if (markerElementLocator.startsWith("template:")) {
        return markerElementLocator.replace("template:", "");
      }

      By locator = null;
      if (markerElementLocator.startsWith("/")) {
        locator = By.xpath(markerElementLocator);
      } else {
        locator = By.cssSelector(markerElementLocator);
      }
      waiters.waitForElementVisible($(locator));
    }

    return "";
  }

  protected String getBaseUrl() {
    return StringUtils.stripEnd(System.getProperty("webdriver.base.url", "https://otus.ru"), "/");
  }

  private String getUrlPrefix() {
    UrlPrefix urlAnnotation = getClass().getAnnotation(UrlPrefix.class);
    if (urlAnnotation != null) {
      return urlAnnotation.value();
    }

    return "";
  }

  public void open() {
    driver.get(getBaseUrl() + getUrlPrefix());
  }

//  public <T> T page(Class<T> clazz) {
//    try {
//      Constructor constructor = clazz.getConstructor(WebDriver.class);
//
//      return convertInstanceOfObject(constructor.newInstance(driver), clazz);
//
//    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
//             InvocationTargetException e) {
//      e.printStackTrace();
//    }
//
//    return null;
//  }
}
