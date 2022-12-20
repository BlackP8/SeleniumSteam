package page_objects;

import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.ConvertUtil;
import utilities.wait_utility.WaitUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pavel Romanov 08.12.2022
 */
public class AboutPage {
    private WebElement storeButton;
    private WaitUtil util;
    private static final String MAIN_PAGE_IDENTIFIER = "//*[@id = 'home_maincap_v7']";
    private static final String GAMERS_ONLINE_PATH = "//*[@class='online_stat'][1]";
    private static final String GAMERS_IN_GAME_PATH = "//*[@class='online_stat'][2]";
    private static final String STORE_BUTTON_PATH = "//*[@class='supernav_container']//a[contains(text(), 'STORE')]";

    public AboutPage(String waitTime) {
        util = new WaitUtil(waitTime);
    }

    public int compareAmountOfPlayers() {
        return Double.compare(Double.parseDouble(ConvertUtil.findNumber(util.setPresenceWait(GAMERS_ONLINE_PATH).getText())),
                Double.parseDouble(ConvertUtil.findNumber(util.setPresenceWait(GAMERS_IN_GAME_PATH).getText())));
    }

    public boolean clickStoreBtn() {
        storeButton = SingleDriver.getDriver().findElement(By.xpath(STORE_BUTTON_PATH));
        storeButton.click();

        WebElement expectedEl = util.setPresenceWait(MAIN_PAGE_IDENTIFIER);
        return expectedEl.isDisplayed();
    }
}
