package factory.impl;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeSettings implements IDriverSettings<ChromeOptions> {

  @Override
  public ChromeOptions getSettings() {
    ChromeOptions chromeOptions = new ChromeOptions();

    chromeOptions.addArguments("--no-first-run");
    chromeOptions.addArguments("--homepage=about:blank");
    chromeOptions.addArguments("--ignore-certificate-errors");
    chromeOptions.addArguments("start-maximized");

    return chromeOptions;
  }

}
