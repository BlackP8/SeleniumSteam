package page_objects;

import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import wait_utility.WaitUtil;

/**
 * @author Pavel Romanov 10.12.2022
 */
public class MarketPage {
    private WaitUtil util;
    private WebElement expectedEl;
    private static JavascriptExecutor executor;
    private static WebElement searchbBox;
    private static final String SCROLL_SCRIPT = "arguments[0].scrollIntoView();";
    private static final String COMMUNITY_BUTTON_PATH = "//*[@class='supernav_container']//a[contains(text(), 'COMMUNITY')]";
    private static final String MARKET_BUTTON_PATH = "//*[@class='supernav_content']//a[contains(text(),'Market')]";
    private static final String MARKET_PAGE_IDENTIFIER = "//*[@id = 'myMarketTabs']";
    private static final String SHOW_ADVANCED_BUTTON_PATH = "//*[@id='market_search_advanced_show']";
    private static final String SEARCH_FROM_IDENTIFIER = "//*[@id='market_advanced_search']";
    private static final String ALL_GAMES_MENU = "//*[@id='market_advancedsearch_appselect_options']";
    private static final String DOTA2_OPTION_PATH = "//*[@id='market_advancedsearch_appselect_options']//*[@alt='Dota 2']";
    private static final String HERO_OPTION_PATH = "//*[@id='market_advancedsearch_filters']//*[contains(@value, 'life_stealer')]";
    private static final String IMMORTAL_CHECKBOX_PATH = "//*[@id='market_advancedsearch_filters']//*[contains(@id, 'Immortal')]";
    private static final String SEARCHBOX_PATH = "//*[@id='advancedSearchBox']";


    public MarketPage(String waitTime) {
        executor = (JavascriptExecutor) SingleDriver.getDriver();
        util = new WaitUtil(waitTime);
    }

    public boolean clickMarketBtn() {
        Actions action = new Actions(SingleDriver.getDriver());
        WebElement target = SingleDriver.getDriver().findElement(By.xpath(COMMUNITY_BUTTON_PATH));
        action.moveToElement(target).perform();

        WebElement marketButton = util.setPresenceWait(MARKET_BUTTON_PATH);
        marketButton.click();

        expectedEl = util.setPresenceWait(MARKET_PAGE_IDENTIFIER);
        return expectedEl.isDisplayed();
    }

    public boolean clickAdvancedOptions() {
        WebElement showAdvancedBtn = util.setPresenceWait(SHOW_ADVANCED_BUTTON_PATH);
        executor.executeScript(SCROLL_SCRIPT, showAdvancedBtn);
        showAdvancedBtn.click();

        expectedEl = util.setPresenceWait(SEARCH_FROM_IDENTIFIER);
        return expectedEl.isDisplayed();
    }

    public void setSearchParameters(String searchBoxText) {
        WebElement gamesMenu = util.setPresenceWait(ALL_GAMES_MENU);
        gamesMenu.click();

        WebElement dotaOption = util.setPresenceWait(DOTA2_OPTION_PATH);
        executor.executeScript(SCROLL_SCRIPT, dotaOption);
        dotaOption.click();

        WebElement heroOption = util.setPresenceWait(HERO_OPTION_PATH);
        executor.executeScript(SCROLL_SCRIPT, heroOption);
        heroOption.click();

        WebElement immortalCheckBox = util.setPresenceWait(IMMORTAL_CHECKBOX_PATH);
        immortalCheckBox.click();

        searchbBox = util.setPresenceWait(SEARCHBOX_PATH);
        searchbBox.click();
        searchbBox.sendKeys(searchBoxText);
    }
}
