package page_objects;

import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wait_utility.WaitUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pavel Romanov 08.12.2022
 */
public class AboutPage {
    private WebElement storeButton;
    private WaitUtil util;
    private static final String REGEX = "\\d+";
    private static final String MAIN_PAGE_IDENTIFIER = "//*[@id = 'home_maincap_v7']";
    private static final String GAMERS_ONLINE_PATH = "//*[@class='online_stat'][1]";
    private static final String GAMERS_IN_GAME_PATH = "//*[@class='online_stat'][2]";
    private static final String STORE_BUTTON_PATH = "//*[@class='supernav_container']//a[contains(text(), 'STORE')]";

    public AboutPage(String waitTime) {
        util = new WaitUtil(waitTime);
    }

    public int compareAmountOfPlayers() {
        String playersOnline = util.setPresenceWait(GAMERS_ONLINE_PATH).getText();
        String playingNow = util.setPresenceWait(GAMERS_IN_GAME_PATH).getText();

        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(playersOnline);

        while(m.find()) {
            playersOnline = m.group();
            break;
        }

        m = p.matcher(playingNow);
        while(m.find()) {
            playingNow = m.group();
            break;
        }
        return Double.compare(Double.parseDouble(playersOnline), Double.parseDouble(playingNow));
    }

    public boolean clickStoreBtn() {
        storeButton = SingleDriver.getDriver().findElement(By.xpath(STORE_BUTTON_PATH));
        storeButton.click();

        WebElement expectedEl = util.setPresenceWait(MAIN_PAGE_IDENTIFIER);
        return expectedEl.isDisplayed();
    }
}
