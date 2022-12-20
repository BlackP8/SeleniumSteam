package page_objects;

import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.wait_utility.WaitUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Romanov 10.12.2022
 */
public class MarketPage {
    private WaitUtil util;
    private WebElement expectedEl;
    private static JavascriptExecutor executor;
    private static WebElement searchbBox;
    private static List<WebElement> elements = new ArrayList<>();
    private static final String SCROLL_SCRIPT = "arguments[0].scrollIntoView();";
    private static final String COMMUNITY_BUTTON_PATH = "//*[@class='supernav_container']//a[contains(text(), 'COMMUNITY')]";
    private static final String MARKET_BUTTON_PATH = "//*[@class='supernav_content']//a[contains(text(),'Market')]";
    private static final String MARKET_PAGE_IDENTIFIER = "//*[@id = 'myMarketTabs']";
    private static final String SHOW_ADVANCED_BUTTON_PATH = "//*[@id='market_search_advanced_show']";
    private static final String SEARCH_FROM_IDENTIFIER = "//*[@id='market_advanced_search']";
    private static final String ALL_GAMES_MENU = "//*[@id='market_advancedsearch_appselect']";
    private static final String GAME_OPTION_PATH = "//*[@id='market_advancedsearch_appselect_options']//*[@alt='%s']";
    private static final String HERO_OPTION_PATH = "//*[@id='market_advancedsearch_filters']//*[contains(text(), '%s')]";
    private static final String RARITY_CHECKBOX_PATH = "//*[@id='market_advancedsearch_filters']//*[contains(@id, '%s')]";
    private static final String SEARCHBOX_PATH = "//*[@id='advancedSearchBox']";
    private static final String SEARCH_BUTTON_PATH = "//*[@id='advancedSearchSubmit']";
    private static final String SHOWED_RESULTS = "//*[@class='market_search_results_header']//a[contains(text(), '%s')]";
    private static final String GAME_RESULT = "//*[@class='market_search_results_header']//a";
    private static final String RESULT_ITEMS = "//*[@id='searchResultsRows']//*[contains(@id,'name')]";
    private static final String DELETE_BTN = "//*[@id='BG_bottom']//a[contains(text(),'Clear')]";
    private static final String RESULTS_COUNT= "//*[@id='searchResults_total']";

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

    public void setSearchParameters(String gameName, String heroName, String rarity, String searchBoxText) {
        WebElement gamesMenu = util.setPresenceWait(ALL_GAMES_MENU);
        gamesMenu.click();

        WebElement dotaOption = util.setPresenceWait(String.format(GAME_OPTION_PATH, gameName));
        executor.executeScript(SCROLL_SCRIPT, dotaOption);
        dotaOption.click();

        WebElement heroOption = util.setPresenceWait(String.format(HERO_OPTION_PATH, heroName));
        executor.executeScript(SCROLL_SCRIPT, heroOption);
        heroOption.click();

        WebElement immortalCheckBox = util.setPresenceWait(String.format(RARITY_CHECKBOX_PATH, rarity));
        immortalCheckBox.click();

        searchbBox = util.setPresenceWait(SEARCHBOX_PATH);
        searchbBox.click();
        searchbBox.sendKeys(searchBoxText);
    }

    public boolean searchBtnClick(String gameName, String heroName, String rarity, String searchBoxText) {
        boolean output = false;
        WebElement searchBtn = util.setPresenceWait(SEARCH_BUTTON_PATH);
        searchBtn.click();

        String result1 = util.setPresenceWait(GAME_RESULT).getText();
        String result2 = util.setPresenceWait(String.format(SHOWED_RESULTS, heroName)).getText();
        String result3 = util.setPresenceWait(String.format(SHOWED_RESULTS, rarity)).getText();
        String result4 = util.setPresenceWait(String.format(SHOWED_RESULTS, searchBoxText)).getText().replace("\"", "");

        if(gameName.equals(result1) && heroName.equals(result2) && rarity.equals(result3)
        && searchBoxText.equals(result4)) {
            output = true;
        }

        return output;
    }

    public boolean checkGolden(String goldenText) {
        boolean result = false;
        int count = 0;
        elements = util.setAllPresenceWait(RESULT_ITEMS);

        for (int i = 0; i < 5; i++) {
            if (elements.get(i).getText().toLowerCase().contains(goldenText)) {
                count++;
            }
        }

        if (count == 5) {
            result = true;
        }

        return result;
    }

    public boolean removeOptions() {
        boolean result = false;
        String count = util.setPresenceWait(RESULTS_COUNT).getText();
        WebElement deleteBTN = util.setPresenceWait(DELETE_BTN);
        deleteBTN.click();

        if (count != util.setPresenceWait(RESULTS_COUNT).getText()) {
            result = true;
        }

        return result;
    }
}
