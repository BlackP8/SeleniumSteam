package page_objects;

import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import wait_utility.WaitUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pavel Romanov 08.12.2022
 */
public class AboutPage {
    private WebElement playersOnline;
    private WebElement playingNow;
    private WebElement storeButton;
    private WaitUtil util;

    public AboutPage(String waitTime) {
        PageFactory.initElements(SingleDriver.getInstance(), this);
        util = new WaitUtil(waitTime);
    }

    public int compareAmountOfPlayers(String gamersOnline, String gamersInGame) {
        String playersOnline = util.setPresenceWait(gamersOnline).getText();
        String playingNow = util.setPresenceWait(gamersInGame).getText();

        Pattern p = Pattern.compile("\\d+");
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

    public boolean clickStoreBtn(String storeBtnPath, String mainPageIdentifier) {
        storeButton = SingleDriver.getInstance().findElement(By.xpath(storeBtnPath));
        storeButton.click();

        WebElement expectedEl = util.setPresenceWait(mainPageIdentifier);
        return expectedEl.isDisplayed();
    }
}
