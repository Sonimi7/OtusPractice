package com.otus.factory;

import com.otus.exceptions.WebDriverNotSupportedException;
import org.openqa.selenium.WebDriver;

public interface IDriverFactory {
  WebDriver create() throws WebDriverNotSupportedException;
}
