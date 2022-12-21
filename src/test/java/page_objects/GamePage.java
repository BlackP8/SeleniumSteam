package page_objects;

import data_model.Game;
import driver.SingleDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utilities.wait_utility.WaitUtil;

/**
 * @author Pavel Romanov 13.12.2022
 */
public class GamePage {
    private static JavascriptExecutor executor;
    private static final String SCROLL_SCRIPT = "arguments[0].scrollIntoView();";
    private static final String GAME_NAME = "//*[@id='appHubAppName']";
    private static final String GAME_RELEASE = "//*[@class='release_date']//*[@class='date']";
    private static final String GAME_PRICE = "//*[contains(@class,'purchase_game')]//*[contains(@class,'purchase_price')]";

    public GamePage() {
        executor = (JavascriptExecutor) SingleDriver.getDriver();
    }

    public boolean getData(Game firstGame) {
        WebElement el = WaitUtil.setPresenceWait(GAME_RELEASE);
        executor.executeScript(SCROLL_SCRIPT, el);
        Game currentGame = new Game(WaitUtil.setPresenceWait(GAME_NAME).getText(), el.getText(),
                WaitUtil.setPresenceWait(GAME_PRICE).getText());

        return currentGame.equals(firstGame);
    }
}
