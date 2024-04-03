package waiters;

import org.openqa.selenium.support.ui.ExpectedCondition;

public interface IWaiters {
    boolean waitForCondition(ExpectedCondition condition);
}
