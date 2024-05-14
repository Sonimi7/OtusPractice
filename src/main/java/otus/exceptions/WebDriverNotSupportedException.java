package exceptions;

public class WebDriverNotSupportedException extends RuntimeException {

  public WebDriverNotSupportedException(String browserName) {
    super(String.format("Browser %s not supported", browserName));
  }
}
