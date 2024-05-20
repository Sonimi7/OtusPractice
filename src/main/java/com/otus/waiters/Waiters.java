package com.otus.waiters;
import java.time.Duration;

import com.google.inject.Inject;
import com.otus.guice.ScenarioScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Waiters implements IWaiters {

  private WebDriver webDriver;
  private WebDriverWait webDriverWait;

  @Inject
  public Waiters(ScenarioScoped scenarioScoped) {
    this.webDriver = scenarioScoped.driver;
    this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
  }

  @Override
  public boolean waitForCondition(ExpectedCondition condition) {
    try {
      webDriverWait.until(condition);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  public boolean waitForElementVisible(WebElement element) {
    return waitForCondition(ExpectedConditions.visibilityOf(element));
  }

  public boolean waitForPresentsElementLocated(WebElement element) {
    return waitForCondition(ExpectedConditions.presenceOfElementLocated((By) element));
  }

  public boolean waitForElementNotVisible(WebElement element) {
    return waitForCondition(ExpectedConditions.invisibilityOf(element));
  }

}
