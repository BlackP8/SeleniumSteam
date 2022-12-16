package page_objects;

import driver.SingleDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import wait_utility.WaitUtil;

import java.util.Arrays;

/**
 * @author Pavel Romanov 13.12.2022
 */
public class GamePage {
    private WaitUtil util;
    private static JavascriptExecutor executor;
    private static final String SCROLL_SCRIPT = "arguments[0].scrollIntoView();";
    private static final String GAME_NAME = "//*[@id='appHubAppName']";
    private static final String GAME_RELEASE = "//*[@class='release_date']//*[@class='date']";
    private static final String GAME_PRICE = "//*[contains(@class,'purchase_game')]//*[contains(@class,'purchase_price')]";

    public GamePage(String waitTime) {
        util = new WaitUtil(waitTime);
        executor = (JavascriptExecutor) SingleDriver.getDriver();
    }

    public boolean getData(String[] previousData) {
        String[] data = new String[3];
        WebElement el = util.setPresenceWait(GAME_RELEASE);
        executor.executeScript(SCROLL_SCRIPT, el);
        data[0] = util.setPresenceWait(GAME_NAME).getText();
        data[1] = el.getText();
        el = util.setPresenceWait(GAME_PRICE);
        executor.executeScript(SCROLL_SCRIPT, el);
        data[2] = el.getText();

        return Arrays.equals(data, previousData);
    }
}
