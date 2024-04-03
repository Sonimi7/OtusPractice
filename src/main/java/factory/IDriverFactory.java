package factory;

import exceptions.WebDriverNotSupportedException;
import org.openqa.selenium.WebDriver;

public interface IDriverFactory {
    WebDriver create() throws WebDriverNotSupportedException;
}
