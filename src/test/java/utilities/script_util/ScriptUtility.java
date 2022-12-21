package utilities.script_util;

import driver.SingleDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * @author Pavel Romanov 21.12.2022
 */
public class ScriptUtility {
    private static final String SCROLL_SCRIPT = "arguments[0].scrollIntoView();";
    private static JavascriptExecutor executor = (JavascriptExecutor) SingleDriver.getDriver();

    public static void scrollToElement(WebElement element) {
        executor.executeScript(SCROLL_SCRIPT, element);
    }
}
