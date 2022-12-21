package page_objects;

import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.convert_utility.ConvertUtil;
import utilities.wait_utility.WaitUtil;

/**
 * @author Pavel Romanov 08.12.2022
 */
public class AboutPage {
    private static final String MAIN_PAGE_IDENTIFIER = "//*[@id = 'home_maincap_v7']";
    private static final String COUNT_OF_PLAYERS = "//*[@class='online_stats']";
    private static final String STORE_BUTTON_PATH = "//*[@class='supernav_container']//a[contains(text(), 'STORE')]";

    public AboutPage(){ }

    public boolean compareAmountOfPlayers() {
        WebElement el = WaitUtil.setPresenceWait(COUNT_OF_PLAYERS);
        String[] parts = ConvertUtil.findPlayersCount(el.getText());
        double gamersOnline = Double.parseDouble(parts[0]);
        double gamersInGame = Double.parseDouble(parts[1]);

        return gamersOnline > gamersInGame;
    }

    public void clickStoreBtn() {
        WebElement storeButton = SingleDriver.getDriver().findElement(By.xpath(STORE_BUTTON_PATH));
        storeButton.click();
    }

    public boolean checkMainPage() {
        WebElement expectedEl = WaitUtil.setPresenceWait(MAIN_PAGE_IDENTIFIER);
        return expectedEl.isDisplayed();
    }
}
