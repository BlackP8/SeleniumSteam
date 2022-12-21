package utilities.wait_utility;

import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.config_utility.ConfigUtil;

import java.util.List;

/**
 * @author Pavel Romanov 14.12.2022
 */
public class WaitUtil {
    private static WebDriverWait wait = new WebDriverWait(SingleDriver.getDriver(), Long.parseLong(ConfigUtil.getConfProperty("explicitWaitTime")));;

    public WaitUtil() { }

    public static WebElement setPresenceWait(String locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public static List<WebElement> setAllPresenceWait(String locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
    }
}
