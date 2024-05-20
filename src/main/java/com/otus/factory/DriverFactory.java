package com.otus.factory;

import com.otus.factory.impl.ChromeSettings;
import com.otus.factory.impl.IDriverSettings;
import com.otus.exceptions.WebDriverNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.otus.listeners.ActionListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.util.Locale;

public class DriverFactory implements IDriverFactory {

  private final String BROWSER_NAME = System.getProperty("browser.name");

  public WebDriver create() {
    if ("chrome".equals(BROWSER_NAME.toLowerCase(Locale.ROOT))) {
      WebDriverManager.chromedriver().setup();
      IDriverSettings<ChromeOptions> browserSettings = new ChromeSettings();

      return new EventFiringDecorator<>(new ActionListener()).decorate(new ChromeDriver(browserSettings.getSettings()));
    }
    try {
      throw new WebDriverNotSupportedException(this.BROWSER_NAME);
    } catch (WebDriverNotSupportedException ex) {
      ex.printStackTrace();
      return null;
    }
  }
}
