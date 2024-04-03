package factory;

import exceptions.WebDriverNotSupportedException;
import factory.impl.ChromeSettings;
import factory.impl.IDriverSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ActionListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.util.Locale;

public class DriverFactory implements IDriverFactory{

    private final String BROWSER_NAME = System.getProperty("browser.name");

    public WebDriver create() {
        switch (BROWSER_NAME.toLowerCase(Locale.ROOT)) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                IDriverSettings<ChromeOptions> browserSettings = new ChromeSettings();

                return new EventFiringDecorator<>(new ActionListener())
                        .decorate(new ChromeDriver(browserSettings.getSettings()));
            }
            default:
                try{
                    throw new WebDriverNotSupportedException(this.BROWSER_NAME);
                } catch (WebDriverNotSupportedException ex) {
                    ex.printStackTrace();
                    return null;
                }
        }
    }
}
