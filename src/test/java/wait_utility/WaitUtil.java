package wait_utility;

import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author Pavel Romanov 14.12.2022
 */
public class WaitUtil {
    private static WebDriverWait wait;

    public WaitUtil(String waitTime) {
        wait = new WebDriverWait(SingleDriver.getInstance(), Long.parseLong(waitTime));
    }

    public WebElement setPresenceWait(String locator) {
         return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public List<WebElement> setAllPresenceWait(String locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
    }
}
