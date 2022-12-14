package page_objects;

import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import wait_utility.WaitUtil;

import java.util.Arrays;

/**
 * @author Pavel Romanov 13.12.2022
 */
public class GamePage {
    private WaitUtil util;
    private static JavascriptExecutor executor;

    public GamePage(String waitTime) {
        PageFactory.initElements(SingleDriver.getInstance(), this);
        util = new WaitUtil(waitTime);
        executor = (JavascriptExecutor) SingleDriver.getInstance();
    }

    public boolean getData(String[] previousData, String gameName, String releaseDate, String gamePrice) {
//        boolean result = false;
        String[] data = new String[3];
        WebElement el = util.setPresenceWait(releaseDate);
        executor.executeScript("arguments[0].scrollIntoView();", el);
        data[0] = util.setPresenceWait(gameName).getText();
        data[1] = el.getText();
        el = util.setPresenceWait(gamePrice);
        executor.executeScript("arguments[0].scrollIntoView();", el);
        data[2] = el.getText();

//        if (Arrays.equals(data, previousData)) {
//            result = true;
//        }
        return Arrays.equals(data, previousData);
    }
}
